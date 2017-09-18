## Freshchat Android SDK

### [Integration Guide](https://support.freshchat.com/support/solutions/articles/229319)

##### Quick Steps
Project gradle file **build.gradle**
```
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

App Module gradle file **(app/build.gradle)** 
```
dependencies {
    compile 'com.github.freshdesk:freshchat-android:{latestVersion}'
}
```

