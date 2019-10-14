package com.notes.java.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述
 **/
public class FileOper {

    /**
     * 创建文件
     * @param filePath
     * @return
     */
    public static boolean createFile(String filePath){
        boolean result = false;
        File file = new File(filePath);
        if(!file.exists()){
            try {
                result = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 创建文件夹
     * @param dirName
     * @return
     */
    public static boolean createDir(String dirName){
        boolean result = false;
        File file = new File(dirName);
        if(!file.exists()){
            result = file.mkdirs();
        }

        return result;
    }

    /**
     * 删除文件
     * @param filePath
     * @return
     */
    public static boolean delFile(String filePath){
        boolean result = false;
        File file = new File(filePath);
        if(file.exists() && file.isFile()){
            result = file.delete();
        }

        return result;
    }

    /**
     * 删除文件夹
     * @param filePath
     */
    public static void delDir(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            return;
        }

        if(file.isFile()){
            file.delete();
        } else if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File subFile : files) {
                // 递归删除
                delDir(filePath + File.separator + subFile.getName());
            }

            file.delete();
        }
    }

    /**
     * 以字节为单位读取文件
     * @param filePath
     * @return
     */
    public static String readFileByBytes(String filePath){
        File file = new File(filePath);
        if(!file.exists() || !file.isFile()){
            return null;
        }

        StringBuffer content = new StringBuffer();

        try {
            byte[] temp = new byte[1024];
            FileInputStream fileInputStream = new FileInputStream(file);
            while(fileInputStream.read(temp) != -1){
                content.append(new String(temp));
                temp = new byte[1024];
            }

            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    /**
     * 以字符读取文件
     * @param filePath
     * @return
     */
    public static String readFileByChars(String filePath){
        File file = new File(filePath);
        if(!file.exists() || !file.isFile()){
            return "";
        }

        StringBuffer content = new StringBuffer();
        try {
            char[] temp = new char[1024];
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            while(inputStreamReader.read(temp) != -1){
                content.append(new String(temp));
                temp = new char[1024];
            }

            fileInputStream.close();
            inputStreamReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    /**
     * 以行为单位读取文件
     * @param filePath
     * @return
     */
    public static List<String> readFileByLines(String filePath){
        List<String> content = new ArrayList<String>();

        File file = new File(filePath);
        if(!file.exists() || !file.isFile()){
            return content;
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            // 缓冲流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String lineContent = "";
            while (null != (lineContent = reader.readLine())) {
                content.add(lineContent);
                System.out.println(lineContent);
            }

            fileInputStream.close();
            inputStreamReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    /**
     * 写文件 FileOutputStream
     * @param filePath
     * @param content
     * @throws IOException
     */
    public static void writeFileByFileOutputStream(String filePath, String content) throws IOException{
        File file = new File(filePath);
        synchronized (file) {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(content.getBytes("UTF-8"));
            fos.close();
        }
    }

    /**
     * 写文件 BufferedOutputStream
     * @param filePath
     * @param content
     * @throws IOException
     */
    public static void writeFileByBufferedOutputStream(String filePath, String content) throws IOException{
        File file = new File(filePath);
        synchronized (file) {
            BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(filePath));
            fos.write(content.getBytes("UTF-8"));
            // 刷新缓冲数据
            fos.flush();
            fos.close();
        }
    }

    /**
     * 写文件 FileWriter
     * @param filePath
     * @param content
     * @throws IOException
     */
    public static void writeFileByFileWriter(String filePath, String content) throws IOException {
        File file = new File(filePath);
        synchronized (file) {
            // 参数true为追加内容，若无则是覆盖内容
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(content);
            fw.close();
        }
    }

    /**
     * 写文件 BufferedWriter
     * @param filePath
     * @param content
     * @throws IOException
     */
    public static void writeBufferedWriter(String filePath, String content) throws IOException {
        File file = new File(filePath);
        synchronized (file) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true));
            bw.write(content);
            bw.flush();
            bw.close();
        }
    }

    /**
     * 写文件 PrintWriter
     * @param filePath
     * @param content
     * @throws IOException
     */
    public static void writePrintWriter(String filePath, String content) throws IOException {
        File file = new File(filePath);
        synchronized (file) {
            PrintWriter pw = new PrintWriter(new FileWriter(filePath,true));
            // 换行
            pw.println(content);
            // 不换行
            pw.print(content);
            pw.close();
        }
    }
}
