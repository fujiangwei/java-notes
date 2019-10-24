package com.notes.java.classloader;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class CGXmlUtil {

    public static final String fileName = "test.xml";

    public static XMLConfiguration cfg = null;

    static {
        try {
            cfg = new XMLConfiguration(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 配置文件 发生变化就重新加载
        cfg.setReloadingStrategy(new FileChangedReloadingStrategy());
    }

    public static String getStringValue(String key) {
        return cfg.getString(key);
    }

    public static int getIntValue(String key) {
        return cfg.getInt(key);
    }

    public static void main(String[] args) {
        String url = CGXmlUtil.getStringValue("database.url");
        System.out.println("url:" + url);
        int port = CGXmlUtil.getIntValue("database.port");
        System.out.println("port:"+ port);
    }
}
