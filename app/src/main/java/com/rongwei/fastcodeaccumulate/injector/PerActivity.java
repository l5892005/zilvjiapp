package com.rongwei.fastcodeaccumulate.injector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by MAO on 2018/6/2.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {

}
