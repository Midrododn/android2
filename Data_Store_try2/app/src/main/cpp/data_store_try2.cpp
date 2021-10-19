#include <jni.h>
#include <string>

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("data_store_try2");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("data_store_try2")
//      }
//    }
extern "C" JNIEXPORT jint JNICALL
Java_com_example_data_1store_1try2_DB_1SQLite_1try2_c_1retstr(JNIEnv *env, jobject thiz) {
    int ret_int = 5;
    
    return ret_int;
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_data_1store_1try2_DB_1SQLite_1try2_c_1strfun(JNIEnv *env, jobject thiz,
 jstring txt) {
    jstring S1;
    char msg[60] = "Test text";
    const char *input = env ->GetStringUTFChars(txt, 0);
    S1 = env ->NewStringUTF(input);

    return S1;
}
