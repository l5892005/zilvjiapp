#include <jni.h>
#include <string>
#include <codecvt>

extern "C" {
    extern int main(int argc,const char * argv[]);
};
//int main(int argc,char * argv[])

extern "C"
JNIEXPORT void JNICALL
Java_com_rongwei_fastcodeaccumulate_module_main_main_MainHomeActivity_bsdiff(JNIEnv *env,
                                                                             jobject thiz,
                                                                             jstring old_path,
                                                                             jstring pach,
                                                                             jstring new_file) {

    const char *oldApk=  env->GetStringUTFChars(old_path,0);
    const char *pachFile=  env->GetStringUTFChars(pach,0);
    const char *newfile=  env->GetStringUTFChars(new_file,0);
    const char *argv[]={"",oldApk,newfile,pachFile};
    main(4,argv);

    env->ReleaseStringUTFChars(old_path,oldApk);
    env->ReleaseStringUTFChars(pach,pachFile);
    env->ReleaseStringUTFChars(new_file,newfile);


}