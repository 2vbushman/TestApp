-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification

-keep class * extends android.app.Activity
-keep class * extends android.app.Application
-keep class * extends android.app.Service
-keep class * extends android.content.BroadcastReceiver
-keep class * extends android.content.ContentProvider
-keep class * extends android.app.backup.BackupAgent
-keep class * extends android.preference.Preference
-keep class * extends android.support.v4.app.Fragment
-keep class * extends android.support.v4.app.DialogFragment
-keep class * extends com.actionbarsherlock.app.SherlockListFragment
-keep class * extends com.actionbarsherlock.app.SherlockFragment
-keep class * extends com.actionbarsherlock.app.SherlockFragmentActivity
-keep class * extends android.app.Fragment
-keep class com.android.vending.licensing.ILicensingService

-keep class android.support.v7.internal.** { *; }
-keep interface android.support.v7.internal.** { *; }
-keep class android.support.v7.** { *; }

-keep class ru.taximaster.testapp.** { *; }

-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class retrofit2.** { *; }
-keep class android.location.** { *; }
-keepnames class com.google.android.maps.** {*;}
-keep public class com.google.android.maps.** {*;}

-keepattributes Signature
-keepattributes Exceptions


-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-dontwarn android.support.**
-dontwarn com.google.ads.**
-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn android.support.v7.**
-dontwarn retrofit2.**
-dontwarn com.google.android.maps.**


-assumenosideeffects class ru.taximaster.testapp.utils.DebugUtil {
     public static void print(java.lang.Object);
     public static void foreach(java.lang.Iterable);
}