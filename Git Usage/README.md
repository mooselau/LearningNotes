# Only for reviewing the usage of github

The Book on Git Guide: https://git-scm.com/book/en/v2 .

```shell
# make several commits into one
$ git reset HEAD^^^ (reset head to the 3rd last commit)
$ git add (then add all modifications again)
$ git commit (commit changes)

# way two:
$ git rebase -i HEAD~#{number of commits} 
# within the next page, pickup the last commit you want, and squash other commit if we need, then complete rebase with new commit, after that all needed commits should be in one commit.
$ git log (check the latest commit)

# show one commit changed files
$ git show --pretty=""  --name-only f7748e47a1a70f004068be4642d04166f47cc692
```
