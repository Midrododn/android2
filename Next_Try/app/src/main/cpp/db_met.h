//
// Created by android on 09.10.21.
//

#ifndef NEXT_TRY_DB_MET_H
#define NEXT_TRY_DB_MET_H
#endif //NEXT_TRY_DB_MET_H

#include "sqlite3.h"
#include <string>
#include <iostream>
#include <fstream>

int sumTwo (int a, int  b=0){
    return a + b;
}

std::string hTest(){
    std::string txt = "text";
    return txt;
}

int create_db (){
    char db_name[] = "test.db";
    sqlite3 *db;
    char *zErrMSG = 0;
    int rc;

    rc = sqlite3_open("test.db", &db);

    if(rc){
        return 0;
    } else{
        sqlite3_close(db);
        return 1;
    }
}

int create_txt(){
    int res = 0;
    std::ofstream MyFile("file.txt");
    if (MyFile.is_open()){
        res = 1;
    }
    MyFile << "Write to file.";
    MyFile.close();

    return res;
}










