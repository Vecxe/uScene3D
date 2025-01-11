#include <jni.h>
#include <string>
#include "HdriToCubemap.hpp"
#include <android/log.h>

#define LOG_TAG "HdriToCubemap"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)

extern "C" JNIEXPORT void JNICALL
Java_com_example_MainActivity_convertHdriToCubemap(JNIEnv *env, jobject /* this */, jstring inputPath, jstring outputPath) {
    
    const char *inputPathCStr = env->GetStringUTFChars(inputPath, nullptr);
    const char *outputPathCStr = env->GetStringUTFChars(outputPath, nullptr);

    try {
      
        HdriToCubemap<unsigned char> hdri2cubemap(inputPathCStr, 512, true);
        hdri2cubemap.writeCubemap(outputPathCStr);
        LOGD("Convert Sucess;-;!");
    } catch (const std::exception &e) {
        LOGD("Error: %s", e.what());
    }

    env->ReleaseStringUTFChars(inputPath, inputPathCStr);
    env->ReleaseStringUTFChars(outputPath, outputPathCStr);
}