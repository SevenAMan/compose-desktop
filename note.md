### wixToolset: <a href = 'https://www.jianshu.com/p/a55ae1924eea'>build tool</a>

### other: <a href = 'https://blog.csdn.net/bugyinyin/article/details/126852405'> 教程 </a>

### mutableStateOf :

表明某个变量是有状态的，对变量进行监听，当状态改变时，触发重绘。

### remember

记录变量的值，使得下次使用改变量时不进行初始化。
使用 ```remember``` 存储对象的可组合项会创建内部状态，使该可组合项有状态。
```remember``` 会为函数提供存储空间，将 remember 计算的值储存，当 remember 的键改变的时候会进行重新计算值并储存。

#### rememberSavable

```rememberSavable``` 可以在重组后保持状态,也可以在重新创建 activity 和进程后保持状态。

#### useResource

```userResource```
Open InputStream from a resource stored in resources for the application, calls the block callback giving it a
InputStream and closes stream once the processing is complete.
从磁盘打开读取InputStream, 调用回调，提供inputStream，并在处理完后关闭流
