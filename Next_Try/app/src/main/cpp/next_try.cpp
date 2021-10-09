#include <jni.h>

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("next_try");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("next_try")
//      }
//    }
extern "C" JNIEXPORT jint JNICALL
Java_com_example_next_1try_DBFragment_ret_1int(JNIEnv *env, jobject thiz) {
    int8_t ret_out = 123;
    return ret_out;
}