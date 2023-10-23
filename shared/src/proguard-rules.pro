-keepattributes Annotation, InnerClasses
-dontnote kotlinx.serialization.**AnnotationsKt
-dontnote kotlinx.serialization.**SerializationKt

# Keep Serializers

-keep,includedescriptorclasses class com.company.package.**$$serializer { *; }
-keepclassmembers class com.company.package.** {
    *** Companion;
}
-keepclasseswithmembers class com.company.package.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# When kotlinx.serialization.json.JsonObjectSerializer occurs

-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}