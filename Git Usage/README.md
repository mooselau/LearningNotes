# Only for reviewing the usage of github

The Book on Git Guide: https://git-scm.com/book/en/v2 .

```
// update README
$ git add README   

// commit the updates and comment this commit
$ git commit -m 'first commit'
```


```
// connect to remote github project
$ git remote add origin git@github.com:defnngj/hello-world.git   

// update github's project with local files
$ git push -u origin master  
```

----------------------------TroubleShooting----------------------------------


```
$ git remote addorigin git@github.com:defnngj/hello-world.git
// Error ： fatal: remote origin already exists.

// fix way:
$ git remote rm origin

// do it again: 
$ git remote add origin git@github.com:defnngj/hello-world.git 
```

```
$ git push origin master
// Error ：error:failed to push som refs to.......

// fix way:
$ git pull origin master //先把远程服务器github上面的文件拉先来，再push 上去。
```
