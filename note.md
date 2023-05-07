### wixToolset: <a href = 'https://www.jianshu.com/p/a55ae1924eea'>build tool</a>

### other: <a href = 'https://blog.csdn.net/bugyinyin/article/details/126852405'> 教程 </a>

### Modifier: <a href = 'https://blog.csdn.net/lplj717/article/details/121925809?spm=1001.2014.3001.5502'>modifier 介绍</a>

### Button： <a href = 'https://blog.csdn.net/lplj717/article/details/121857963'> Button的使用</a>

### <font face="仿宋" color=red size=4>mutableStateOf</font> :

表明某个变量是有状态的，对变量进行监听，当状态改变时，触发重绘。

### <font face="仿宋" color=red size=4>remember</font>

记录变量的值，使得下次使用改变量时不进行初始化。
使用 `remember` 存储对象的可组合项会创建内部状态，使该可组合项有状态。
`remember` 会为函数提供存储空间，将 remember 计算的值储存，当 remember 的键改变的时候会进行重新计算值并储存。

### <font face="仿宋" color=red size=4>rememberSavable</font>

`rememberSavable` 可以在重组后保持状态,也可以在重新创建 activity 和进程后保持状态。

### <font face="仿宋" color=red size=4>useResource</font>

```userResource```
Open InputStream from a resource stored in resources for the application, calls the block callback giving it a
InputStream and closes stream once the processing is complete.
从磁盘打开读取InputStream, 调用回调，提供inputStream，并在处理完后关闭流

### <font face="仿宋" color=red size=4>点击事件</font>

`Modifier.combinedClickable()`：仅适用于鼠标的左键（主键）

1. `onClick`：只会响应鼠标点击事件，无法响应 `Enter`（回车）键的点击
   `Modifier.indication` 和 `Modifier.semantics ` 用于响应`Enter`
2. **`clickable`**：可以响应 `Enter` 触发点击事件

### <font face="仿宋" color=red size=4>AnimatedContent</font>

`AnimatedContent` 添加动画，监视 `targetState`中的对象，当变化时添加动画效果

### <font face="仿宋" color=red size=4> LocalWindowInfo.current </font>

当前窗口的信息。可以监听到 键盘按键

### <font face="仿宋" color=red size=4>拖拽事件</font>

1. **Modifier.onDrag**  `matcher`可以匹配到 PointerMatcher.mouse(PointerButton.Secondary) 鼠标按键
2. **Modifier.pointerInput** Modifier.pointerInput(Unit) { detectDragGestures( matcher = PointerMatcher.Primary) }

### <font face="仿宋" color=red size=4>SelectionContainer</font>

文本选择域
