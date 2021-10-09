#include <jni.h>
#include <string>
#include <stdio.h>
#include "sqlite3.h"
#include <string>
#include <cstring>
#include <iostream>
#include <stdlib.h>
#include <unistd.h>
#include <dirent.h>


extern "C" JNIEXPORT jint JNICALL
Java_com_example_candroid_MainActivity_new_1int(JNIEnv *env, jobject thiz){
    std::int8_t ret_int = 11;
    return ret_int;
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_candroid_MainActivity_prnt_1dir(
            JNIEnv* env,
            jobject /* this */) {
        char location[100];
        getcwd(location, 100);
        std::string hello = "\nDir : ";
        std::string tmp;
        hello.append(location);

        DIR *d;
        struct dirent *dir;
        d = opendir(".");

        if (d){
            while ((dir = readdir(d)) != NULL){
                tmp = "\n";
                tmp.append(dir ->d_name);
                hello.append(tmp);
            }
            closedir(d);
        }
        return env->NewStringUTF(hello.c_str());
}