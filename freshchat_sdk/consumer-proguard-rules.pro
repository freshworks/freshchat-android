# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/hrishikesh/Software/android-studio-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keep class com.freshchat.consumer.sdk.** { *; }

# Rules to keep the FCM dependency optional
-dontwarn com.google.firebase.messaging.RemoteMessage
-keep class com.google.firebase.messaging.RemoteMessage

# Rules to allow building with less than Oreo as target
-dontwarn android.app.NotificationChannel
-dontwarn android.app.NotificationManager

# Proguard config for GSON
# Ref : https://google-gson.googlecode.com/svn/trunk/examples/android-proguard-example/proguard.cfg
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# Gson specific classes
-keep class sun.misc.Unsafe { *; }

# Proguard for Picasso
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Keep Picasso class to verify the version of picasso used.
-keepnames class com.squareup.picasso.Picasso
-keepclassmembers class com.squareup.picasso.Picasso {
    public com.squareup.picasso.Picasso get();
}
#-keep class com.google.gson.reflect.TypeToken { *; }
#-keep class * extends com.google.gson.reflect.TypeToken
# Retain generic signatures of TypeToken and its subclasses with R8 version 3.0 and higher.
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken