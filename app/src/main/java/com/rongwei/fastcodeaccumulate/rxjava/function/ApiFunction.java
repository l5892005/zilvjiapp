package com.rongwei.fastcodeaccumulate.rxjava.function;


import com.rongwei.fastcodeaccumulate.data.bean.BaseResultWrapper;

import io.reactivex.functions.Function;

/**
 * @author linzheng
 */
public class ApiFunction<T> implements Function<BaseResultWrapper<T>, T> {
    @Override
    public T apply(BaseResultWrapper<T> wrapper) throws Exception {
        return wrapper.getData();
    }
}
