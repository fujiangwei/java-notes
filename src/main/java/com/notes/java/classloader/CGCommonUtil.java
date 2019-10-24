package com.notes.java.classloader;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CGCommonUtil {

    /**
     * 一个文件对应一个Configuration
     */
    public static Map<String, Configuration> configMap = new ConcurrentHashMap<String, Configuration>();

    /**
     * 文件后缀
     */
    private static final String SUFFIX_PROPERTIES = ".properties";
    private static final String SUFFIX_XML = ".xml";

    public static String getStringValue(String fileName, String key) {
        if (!configMap.containsKey(key)) {
            CGCommonUtil.initConfig(fileName);
        }
        if (fileName.endsWith(SUFFIX_PROPERTIES)) {
            PropertiesConfiguration cfg = (PropertiesConfiguration) configMap.get(fileName);
            return cfg.getString(key);
        } else if (fileName.endsWith(SUFFIX_XML)) {
            XMLConfiguration cfg = (XMLConfiguration) configMap.get(fileName);
            return cfg.getString(key);
        }
        return null;
    }

    public static Configuration getConfig(String fileName) {
        if (!configMap.containsKey(fileName)) {
            CGCommonUtil.initConfig(fileName);
        }

        Configuration cfg = configMap.get(fileName);
        if (null == cfg) {
            throw new IllegalArgumentException("cfg is null");
        }

        return cfg;
    }

    private static void initConfig(String fileName) {
        Configuration cfg = null;
        try {
            if (fileName.endsWith(SUFFIX_XML)) {
                cfg = new XMLConfiguration(fileName);
            } else if (fileName.endsWith(SUFFIX_PROPERTIES)) {
                cfg = new PropertiesConfiguration(fileName);
            }
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        if (null != cfg) {
            configMap.put(fileName, cfg);
        } else {
            System.out.println("cfg is null");
        }
    }

    /**
     * 读String
     * @param key
     * @return
     */
    public static String getStringValue(Configuration cfg, String key) {
        return cfg.getString(key);
    }

    /**
     * 读int
     * @param key
     * @return
     */
    public static int getIntValue(Configuration cfg, String key) {
        return cfg.getInt(key);
    }

    /**
     * 读boolean
     * @param key
     * @return
     */
    public static boolean getBooleanValue(Configuration cfg, String key) {
        return cfg.getBoolean(key);
    }
    /**
     * 读List
     */
    public static List<?> getListValue(Configuration cfg, String key) {
        return cfg.getList(key);
    }
    /**
     * 读数组
     */
    public static String[] getArrayValue(Configuration cfg, String key) {
        return cfg.getStringArray(key);
    }

    public static void main(String[] args) {
        String name= CGCommonUtil.getStringValue("test.properties", "name");
        System.out.println(name);
        String url = CGCommonUtil.getStringValue("test.xml", "database.url");
        System.out.println(url);

        Configuration config = getConfig("test.properties");
        String name1 = getStringValue(config, "name");
        System.out.println(name1);
        Configuration config1 = getConfig("test.xml");
        String stringValue = CGCommonUtil.getStringValue(config1, "database.url");
        System.out.println(stringValue);

        Configuration config2 = getConfig("mult.xml");
        List<?> listValue = getListValue(config2, "start-criteria.criteria");
        System.out.println(listValue.size());
    }
}