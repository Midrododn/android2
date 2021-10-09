#include <jni.h>
#include <string>

extern "C" JNIEXPORT jint JNICALL
Java_com_example_candroid_MainActivity_new_1int(JNIEnv *env, jobject thiz){
    std::int8_t ret_int = 11;
    return ret_int;
}
