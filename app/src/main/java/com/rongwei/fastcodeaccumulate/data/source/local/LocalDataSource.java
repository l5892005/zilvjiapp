package com.rongwei.fastcodeaccumulate.data.source.local;



/**
 * 本地数据
 */

public class LocalDataSource {
    private static LocalDataSource INSTANCE;

    private LocalDataSource() {
    }

    public static LocalDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (LocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSource();
                }
            }
        }
        return INSTANCE;
    }

}
