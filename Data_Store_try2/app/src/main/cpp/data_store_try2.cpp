#include <jni.h>
#include <string>
#include <iostream>
#include <fstream>
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
    std::string full_pth(input);
    full_pth += "/cpptest.txt";
    S1 = env ->NewStringUTF(input);
    std::ofstream nFile(full_pth);
    nFile << "New File line\n";
    nFile.close();
    return S1;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_data_1store_1try2_DB_1SQLite_1try2_c_1readtxt(JNIEnv *env, jobject thiz,
                                                               jstring pth) {
    const char *input = env ->GetStringUTFChars(pth, 0);
    std::string full_pth(input);
    full_pth += "/cpptest.txt";
    std::ifstream fTxt(full_pth);
    std::string f_line;

    while (getline(fTxt, f_line)){}
    fTxt.close();

    const char *output = f_line.c_str();
    jstring out;
    out = env ->NewStringUTF(output);

    return out;
}