package javaself;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) throws Exception {
        OptionalDemo demo = new OptionalDemo();
        demo.entrypoint();
    }

    private void entrypoint() throws Exception {
        Long num = null;
        Optional<Long> opt = Optional.of(num);
        Long ret = opt.orElseThrow(() -> new RuntimeException(""));
        System.out.println(ret);
    }
}
