#include <jni.h>
//#include "sqlite3(xtrnl).h"
#include "db_met.h"
#include <string>

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
    std::string recive = hTest();
    int8_t ret_out = 123;
    return ret_out;
}

extern "C" JNIEXPORT jint JNICALL
Java_com_example_next_1try_DBFragment_rollSum(JNIEnv *env, jobject thiz, jint a, jint b) {
    int frst = a;
    int scnd = b;
    return sumTwo(frst, scnd);
}
extern "C" JNIEXPORT jint JNICALL
Java_com_example_next_1try_DBFragment_try_1gen_1db(JNIEnv *env, jobject thiz) {
    int db_status;
    db_status = create_db();
    return db_status;
}
extern "C" JNIEXPORT jint JNICALL
Java_com_example_next_1try_DBFragment_make_1txt(JNIEnv *env, jobject thiz) {
    int res = 0;

    res = create_txt();

    return res;
}