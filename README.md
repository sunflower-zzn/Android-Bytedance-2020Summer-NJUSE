# Android-Bytedance-2020Summer-NJUSE
2020NJUSE暑校，安卓开发实训-字节跳动

> 《Android 应⽤开发》课程是字节跳动公司与南京⼤学软件学院合作推出的暑期实训课程。课程共 10天，形式为授课 + 练习作业 + ⼤项⽬。课程内容涵盖了 Android 基础知识、⽤⼾界⾯开发、存储、⽹络、多媒体、⾳视频拍摄和播放、以及业界最新的技术动态 Kotlin、JetPack、Flutter 等。通过这次实训，学⽣将参与⼀系列Android软件的开发。从最基础的hello world出发，到复杂的app开发，最终同学们可以完成⼀个简易版的抖⾳app。

记录课程作业以及大作业

# 第一次作业

- 创建一个Android app工程，包含一个activity（环境可以参考demo）
- 使用5种以上的View并实现一些简单交互 ImageView, Button, TextView, RadioButton, CheckBox, EditText, ProgressBar, SeekBar, Switch 等等不限
- 将一些交互结果输出log
- 打包生成apk

# 第二次作业

- Logcat在屏幕旋转的时候 #onStop() #onDestroy()会展示出来，但UI界面我们看不到，在今天课程基础上想办法补全它，让其跟logcat的展示一样

- 一个抖音笔试题：统计页面所有view的个数   ViewGroup中的API ： getChildCount()  getChildAt()

- 实现一个类似抖音消息页面，如右图-> ,并且点击每个recycleview的item,能够跳转到一个新的界面，并且在新的页面显示出他是第几个item

# 第三次作业

- 引⼊入 Lottie 库实现简单的图标动画
- 使⽤用属性动画，练习 AnimatorSet 和 scale/fade 等基本动画样式
- 使⽤用 ViewPager 和 Fragment 做⼀一个简单版的好友列列表界⾯

# 第四次作业

- 使用自定义View和线程控制作一个时钟App

# 第五次作业

- 使用Retrofit完成注册逻辑（调用wanAndroid的接口）

# 第六次作业

- 使用数据库功能实现一个简单的todolistAPP

# 第七次作业

- 一个简单的视频播放器

  - 简易版

    ❏播放、暂停功能；

    ❏播放进度条展示（包括时间显示）、点击/滑动跳转到指定位置。

    ❏横竖屏切换、横屏时展示全屏模式。

  - Pro版

    ❏将app注册为播放器类型(Action为ACTION_VIEW，Data为Uri，Type为其MIME类型)，点击打开系统视频文件时，可以选择使用自制播放器；

    ❏手机相册中，录制视频列表展示，点击调起播放器。