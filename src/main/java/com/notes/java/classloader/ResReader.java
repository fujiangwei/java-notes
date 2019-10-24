package com.notes.java.classloader;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.*;
import java.net.URL;
import java.util.Iterator;

public class ResReader {
    /**
     * jar下的配置文件
     */
    private static final String subMoudlePropertiesFile = "test.properties";
    /**
     * 内部配置文件
     */
    private static final String innerPropertiesFile = "test.properties";

    public static void main(String[] args) throws InterruptedException {
        loadJarFileByConfiguration();
        Thread.sleep(1000);
        loadLocalFile();
        Thread.sleep(1000);
        loadJarFileByResource();
        Thread.sleep(1000);
        loadJarFileByFile();
    }

    /**
     * 通过apache configuration包读取配置文件
     */
    private static void loadJarFileByConfiguration() {
        try {
            Configuration configuration = new PropertiesConfiguration(subMoudlePropertiesFile);
            Iterator<String> keys = configuration.getKeys();
            while (keys.hasNext()) {
                String next = keys.next();
                System.out.println("key:" + next + "\tvalue:" + configuration.getString(next));
            }
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取当前工程中的配置文件
     */
    private static void loadLocalFile() {
        String path = ResReader.class.getClassLoader().getResource(innerPropertiesFile).getPath();
        System.out.println(path);

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null) {
                System.out.println("strLine:" + strLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过类加载器去的getResource方法去读取
     */
    private static void loadJarFileByResource() {
        URL resource = ResReader.class.getClassLoader().getResource(subMoudlePropertiesFile);
        String path = resource.toString();
        System.out.println(path);
        try {
            // 使用openStream(）
            InputStream is = resource.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String s = "";
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过File类去加载jar包的资源文件
     */
    private static void loadJarFileByFile() {
        URL resource = ResReader.class.getClassLoader().getResource(subMoudlePropertiesFile);
        String path = resource.toString();
        System.out.println(path);
        try {
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String s = "";
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}