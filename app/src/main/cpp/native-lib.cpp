#include <jni.h>
#include <string>

using namespace std;

const int ENV_PRODUCTION = 2;

//staging development
const string DEV_END_POINT = "http://0.0.0.0:2172/";
const string DEV_SALT_KEY = "SECRET";
const string DEV_PREFERENCE_NAME = "app-dev.xml";
const string DEV_TOKEN = "app-dev.xml";

//staging production
const string PROD_END_POINT = "https://api.xxx.xxxx";
const string PROD_SALT_KEY = "SECRET";
const string PROD_PREFERENCE_NAME = "app.xml";
const string PROD_TOKEN = "app.xml";


extern "C" jstring JNICALL
Java_com_example_tasks_ext_constant_ConstKeys_getEndPoint(JNIEnv *env, jobject thiz,
                                                         jint envtype) {
    int type = (int) envtype;
    string s;

    if (type == ENV_PRODUCTION) {
        s = PROD_END_POINT;
    } else {
        s = DEV_END_POINT;
    }

    return env->NewStringUTF(s.c_str());
}

extern "C" jstring JNICALL
Java_com_example_tasks_ext_constant_ConstKeys_getSaltKey(JNIEnv *env, jobject thiz,
                                                        jint envtype) {
    int type = (int) envtype;
    string s;

    if (type == ENV_PRODUCTION) {
        s = PROD_SALT_KEY;
    } else {
        s = DEV_SALT_KEY;
    }

    return env->NewStringUTF(s.c_str());
}

extern "C" jstring JNICALL
Java_com_example_tasks_ext_constant_ConstKeys_getPreferenceName(
        JNIEnv *env, jobject thiz, jint envtype) {
    int type = (int) envtype;
    string s;

    if (type == ENV_PRODUCTION) {
        s = PROD_PREFERENCE_NAME;
    } else {
        s = DEV_PREFERENCE_NAME;
    }

    return env->NewStringUTF(s.c_str());
}

extern "C" jstring JNICALL
Java_com_example_tasks_ext_constant_ConstKeys_getToken(
        JNIEnv *env, jobject thiz, jint envtype) {
    int type = (int) envtype;
    string s;

    if (type == ENV_PRODUCTION) {
        s = PROD_TOKEN;
    } else {
        s = DEV_TOKEN;
    }

    return env->NewStringUTF(s.c_str());
}
