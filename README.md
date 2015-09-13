# DrawerLayout提几点：

1、为了模拟QQ的右侧菜单需要点击才能出现，所以在初始化DrawerLayout的时候，使用了mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,Gravity.RIGHT);意思是只有编程才能将其弹出。

然后在弹出以后，需要让手势可以滑动回去，所以在OpenRightMenu中又编写了：

mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,Gravity.RIGHT); UNLOCK了一下。

最后在onDrawerClosed回调中，继续设置mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,Gravity.RIGHT)；

2、动画效果

动画用了nineoldandroids，关于动画各种偏移量、缩放比例的计算请参考Android 高仿 QQ5.0 侧滑菜单效果 自定义控件来袭 基本是一致的，唯一的不同的地方，给Content设置了ViewHelper.setTranslationX(mContent, mMenu.getMeasuredWidth() * (1 - scale));让Content在菜单的右侧，默认情况下Menu在菜单之上，所以我们根据菜单划出的距离给Content设置X方向的偏移量。

好了，其实看到可以这么做，基本上任何的侧滑菜单效果都能写出来了。有兴趣的话，可以拿DrawerLayout实现这篇博客的所有效果：Android 实现形态各异的双向侧滑菜单 自定义控件来袭 。 

3、setDrawerListener

通过代码也能看出来，可以使用setDrawerListener监听菜单的打开与关闭等等。这里对于当前操作是哪个菜单的判断是通过TAG判断的，我觉得使用gravity应该也能判断出来~~



好了，没撒了，由于DrawerLayout默认只能从边界划出菜单，但是QQ划出菜单的手势区域比较大，大家有兴趣，可以重写Activity的onTouchEvent，在里面判断，如果是左右滑动手势神马的，弹出菜单，应该不麻烦~~~
