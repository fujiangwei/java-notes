package com.notes.java.classloader;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

import java.util.List;

public class CGPropetiesUtil {

    public static final String fileName = "test.properties";

    public static PropertiesConfiguration cfg = null;

    static {
        try {
            cfg = new PropertiesConfiguration(fileName);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        // 当文件的内容发生改变时，配置对象也会刷新
        cfg.setReloadingStrategy(new FileChangedReloadingStrategy());
    }

    /**
     * 读String
     * @param key
     * @return
     */
    public static String getStringValue(String key) {
        return cfg.getString(key);
    }

    /**
     * 读int
      * @param key
     * @return
     */
    public static int getIntValue(String key) {
        return cfg.getInt(key);
    }

    /**
     * 读boolean
      * @param key
     * @return
     */
    public static boolean getBooleanValue(String key) {
      return cfg.getBoolean(key);
    }
    /**
     * 读List
     */
    public static List<?> getListValue(String key) {
        return cfg.getList(key);
    }
    /**
     * 读数组
     */
    public static String[] getArrayValue(String key) {
        return cfg.getStringArray(key);
    }

    public static void main(String[] args) {
        String name = CGPropetiesUtil.getStringValue("name");
        System.out.println("String:" + name);
        int port = CGPropetiesUtil.getIntValue("port");
        System.out.println("int:" + port);
        boolean flag = CGPropetiesUtil.getBooleanValue("flag");
        System.out.println("boolean:" + flag);
        List<String> users = (List<String>) CGPropetiesUtil.getListValue("interest");
        for (String user : users) {
            System.out.println("interest:" + user);
        }
    }
}