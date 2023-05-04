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
