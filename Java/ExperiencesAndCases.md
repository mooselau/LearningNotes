# The impressive experiences and cases
Cases or experiences that I met during work.

## The "CallerRunsPolicy"
(In Chinese)曾经和Tony大神一起做过一个case，客户有上百万篇文档需要处理，然而在处理了几十万篇之后，速度骤然变慢了（产品的正常速率是10ms - 200ms/篇，3w多篇/h，然后发生问题后速率降为大约几千篇到一万篇/h）。我们通过查看Audit Report，确定变慢的组件就是文档处理部件（Content Processing Service, a.k.a. CPS）。
为了找出原因，我们在一个时刻，抓取了多个jStack来查看信息，通过对比不同时刻的jStack，我们发现，正常应该做任务的Thread处于空闲状态（阻塞在poll任务），可是负责分发任务的Dispatcher却在正常工作。这并不符合逻辑。因为如果Dispatcher/Submitter是正常工作的，那么Worker就不应该poll不到任务。
此时的客户系统CPU使用并不高，Memory使用情况也没有很高，也就是说Worker线程因为不明原因没有进行正常工作，或者说Dispatcher并没有真正地在分发任务/Request。
带着这些疑虑跟猜测，我们仔细回顾了代码跟逻辑。最终，在Dsipatcher/Submiter的分发逻辑中，发现了如下一段配置：
‘’‘java
executor.setRejectedExecutorHandler(new ThreadPoolExecutor.CallerRunsPolicy());
’‘’
这段配置的作用发生在线程池满载之后，当前的Dispatcher该怎么做。由于之前设置的是“CallerRunsPolicy”，这个Dispatcher就会自身执行这个Request，这样做的结果就是Dispatcher变成了Worker，本身的Dispatch工作将停滞下来，所以其他的Worker线程也就不再工作了/无法工作。

## The "Retry" Mechanism
(In Chinese)CPS这个组件在很久之前的设计中是进行一次性处理的组件。换言之，如果Request处理失败，将直接按照fail request逻辑进行处理。然而，为了提高处理效率，Request被包装成了Batch Reqeust，同时Batch Request内部还有至多5个Sub Batch Request，再在其中，才是单个的真正的需要处理的Request。在这种情况下，如果由于一个Request的失败，将整个25个Request组成的Batch Request当作失败来处理的话，是非常糟糕的处理方式。
后来，经过讨论跟设计，我们增加了“Retry”的逻辑。具体来说，如果当前的Batch Request失败了，那么这个Batch Request会被放置于一个Failure Request Queue当中，同时另起一个Retry Working Daemon来处理这个queue。它会对每一个Batch Request进行分解和处理，最后打包的时候，这个Batch Request将是Partially Processed，同时每一个Request将有自己的processing status。再将这个Batch Response发回给Server/Caller进行整理。
这样做的好处是减少了“误伤”，虽然会增加处理时间跟逻辑，但是总的来说并没有十分影响正常的处理速率。



