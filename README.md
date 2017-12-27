#### Android Studio升级3.0，自带Gradle打包

升级到android studio 3.0之后，多渠道打包方式有点变化，对出现的错误记录。
###### 环境如下:
```
Android Studio 3.0.1
JDK 1.8
Gradle 4.1
MAC OS 10.13
```

###### 记录要点
```
1. 签名方式（SigningEnabled）
2. 渠道配置（productFlavors）
```
1.主要在签名方式和渠道配置和以前不一样，虽然android studio 2.2就支持V2（Full APK Signature）,一直使用的是V1（Jar Signature），
开启V1，V2直接在Gradle中配置，把v1SigningEnabled，v2SigningEnabled设置为true，如图

![image](http://p1als56vv.bkt.clouddn.com/QQ20171227170412.png)

2.渠道配置和友盟多渠道方式差不多，在AndroidManifest.xml中配置mata-data
![image](http://p1als56vv.bkt.clouddn.com/QQ20171227171321.png)
在app build.gradle如果按照友盟以前那样配置会出现以下错误
```
Error:All flavors must now belong to a named flavor dimension. The flavor 'xiaomi' is not assigned to a flavor dimension. Learn more at https://d.android.com/r/tools/flavorDimensions-missing-error-message.html
```
正确配置如图
![image](http://p1als56vv.bkt.clouddn.com/QQ20171227172517.png)

在MainActivity中获取渠道号

![image](http://p1als56vv.bkt.clouddn.com/QQ20171227174231.png)


在Terminal中输入命令生成不同渠道APK

```
所有渠道
./gradlew assembleRelease
只生成小米渠道
./gradlew assemblexiaomiRelease
```
运行APP就能看到渠道号
![image](http://p1als56vv.bkt.clouddn.com/QQ20171227174722.png)

验证签名很简单，在Sdk中就有打包检查工具

使用方法：

```
进入 Sdk->build-tools->25.0.3

验证 apksigner verify --verbose 《apk的位置》

```
结果显示有一个签名者，使用了V1，V2签名


![image](http://p1als56vv.bkt.clouddn.com/QQ20171227181403.png)

如果想获取更多签名信息可以查看 [Android-GetAPKInfo](https://github.com/bihe0832/Android-GetAPKInfo)
