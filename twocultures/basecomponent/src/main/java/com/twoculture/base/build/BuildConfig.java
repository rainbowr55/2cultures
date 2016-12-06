package com.twoculture.base.build;

import java.lang.reflect.Field;

/**
 * Created by liangcaihong on 2016/12/6.
 */

public class BuildConfig {

    private static final BuildConfig instance = new BuildConfig();
    /**
     * 应用包名
     */
    private static final String PACKAGE_NAME = "com.twoculture.twoculture";
    /**
     * BuildConfig 文件名
     */
    private static final String BUILD_CONFIG_NAME = ".BuildConfig";

    private BuildConfig() {
    }

    /**
     * 配置-debug
     */
    public boolean debug = true;

    /**
     * 获取实例
     *
     * @return
     */
    public synchronized static BuildConfig getInstance() {
        return getInstance(PACKAGE_NAME);
    }

    /**
     * 获取实例
     *
     * @param packageName 自定义包名
     * @return
     */
    public synchronized static BuildConfig getInstance(String packageName) {
        try {
            Class clazz = null;
            clazz = Class.forName(packageName + BUILD_CONFIG_NAME);
            if (clazz != null) {
                try {
                    Field fDebug = clazz.getField("CDEBUG");
                    instance.debug = (boolean) fDebug.get(clazz);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
