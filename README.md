# EasyAndroid 简介
此项目是开发中点点滴滴积累所得，故做成jar方便以后大家开发
## Jitpack 引入方法
#### First、在project下的settings.gradle.kts添加

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io") }
    }
}
```
**你可以选择下面四种的其中一种，在module下的build.gradle添加。**

```
//全部jar
implementation("com.github.Frankie9527:EasyAndroid:0.5.1")

//和ui 界面相关
implementation("com.github.Frankie9527.EasyAndroid:ui:0.5.1")

// 工具类
implementation("com.github.Frankie9527.EasyAndroid:tools:0.5.1")

// image loader
implementation("com.github.Frankie9527.EasyAndroid:loader:0.5.1")
```