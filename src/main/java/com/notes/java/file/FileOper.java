package com.notes.java.file;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
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
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
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
            fos.write(content.getBytes(StandardCharsets.UTF_8));
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
            fos.write(content.getBytes(StandardCharsets.UTF_8));
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

    /**
     * 移动指定文件夹内的全部文件
     * @param from
     * @param to
     * @throws Exception
     */
    public static void fileMove(String from, String to) throws Exception {//
        try {
            File dir = new File(from);
            // 将文件或文件夹放入文件集
            File[] files = dir.listFiles();
            // 判断文件集是否为空
            if (files == null)
                return;
            // 创建目标目录
            File moveDir = new File(to);
            // 判断目标目录是否存在
            if (!moveDir.exists()) {
                // 不存在则创建
                moveDir.mkdirs();
            }
            // 遍历文件集
            for (int i = 0; i < files.length; i++) {
                // 如果是文件夹或目录,则递归调用fileMove方法，直到获得目录下的文件
                if (files[i].isDirectory()) {
                    // 递归移动文件
                    fileMove(files[i].getPath(), to + "\\" + files[i].getName());
                    // 删除文件所在原目录
                    files[i].delete();
                }
                // 将文件目录放入移动后的目录
                File moveFile = new File(moveDir.getPath() + "\\"
                        + files[i].getName());
                // 目标文件夹下存在的话，删除
                if (moveFile.exists()) {
                    moveFile.delete();
                }
                // 移动文件
                files[i].renameTo(moveFile);
                System.out.println(files[i] + " 移动成功");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 复制目录下的文件（不包括该目录）到指定目录，会连同子目录一起复制过去。
     * @param toPath
     * @param fromPath
     */
    public static void copyFileFromDir(String toPath, String fromPath) {
        File file = new File(fromPath);
        // true:创建文件 false创建目录
        createFile(toPath, false);
        // 如果是目录
        if (file.isDirectory()) {
            copyFileToDir(toPath, listFile(file));
        }
    }

    /**
     * 复制目录到指定目录,将目录以及目录下的文件和子目录全部复制到目标目录
     * @param toPath
     * @param fromPath
     */
    public static void copyDir(String toPath, String fromPath) {
        // 创建文件
        File targetFile = new File(toPath);
        // 创建目录
        createFile(targetFile, false);
        // 创建文件
        File file = new File(fromPath);
        // 如果传入是目录
        if (targetFile.isDirectory() && file.isDirectory()) {
            // 复制文件到指定目录
            copyFileToDir(targetFile.getAbsolutePath() + "/" + file.getName(),
                    listFile(file));
        }
    }

    /**
     * 复制一组文件到指定目录。targetDir是目标目录，filePath是需要复制的文件路径
     * @param toDir
     * @param filePath
     */
    public static void copyFileToDir(String toDir, String[] filePath) {
        // 目录路径为空
        if (toDir == null || "".equals(toDir)) {
            System.out.println("参数错误，目标路径不能为空");
            return;
        }
        File targetFile = new File(toDir);
        // 如果指定目录不存在
        if (!targetFile.exists()) {
            // 新建目录
            targetFile.mkdir();
        } else {
            // 如果不是目录
            if (!targetFile.isDirectory()) {
                System.out.println("参数错误，目标路径指向的不是一个目录！");
                return;
            }
        }
        // 遍历需要复制的文件路径
        for (int i = 0; i < filePath.length; i++) {
            // 创建文件
            File file = new File(filePath[i]);
            // 判断是否是目录
            if (file.isDirectory()) {
                // 递归调用方法获得目录下的文件
                copyFileToDir(toDir + "/" + file.getName(), listFile(file));
                System.out.println("复制文件 " + file);
            } else {
                // 复制文件到指定目录
                copyFileToDir(toDir, file, "");
            }
        }
    }

    /**
     * 复制文件到指定目录
     * @param toDir
     * @param file
     * @param newName
     */
    public static void copyFileToDir(String toDir, File file, String newName) {
        String newFile = "";
        if (newName != null && !"".equals(newName)) {
            newFile = toDir + "/" + newName;
        } else {
            newFile = toDir + "/" + file.getName();
        }
        File tFile = new File(newFile);
        // 调用方法复制文件
        copyFile(tFile, file);
    }

    /**
     * 复制文件
     * @param toFile
     * @param fromFile
     */
    public static void copyFile(File toFile, File fromFile) {
        // 判断目标目录中文件是否存在
        if (toFile.exists()) {
            System.out.println("文件" + toFile.getAbsolutePath() + "已经存在，跳过该文件！");
            return;
        } else {
            // 创建文件
            createFile(toFile, true);
        }
        System.out.println("复制文件" + fromFile.getAbsolutePath() + "到"
                + toFile.getAbsolutePath());
        try {
            // 创建文件输入流
            InputStream is = new FileInputStream(fromFile);
            // 文件输出流
            FileOutputStream fos = new FileOutputStream(toFile);
            // 字节数组
            byte[] buffer = new byte[1024];
            // 将文件内容写到文件中
            while (is.read(buffer) != -1) {
                fos.write(buffer);
            }
            // 输入流关闭
            is.close();
            // 输出流关闭
            fos.close();
            // 捕获文件不存在异常
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // 捕获异常
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件绝对路径
     * @param dir
     * @return
     */
    public static String[] listFile(File dir) {
        // 声获字符串赋值为路传入文件的路径
        String absolutePath = dir.getAbsolutePath();
        // 文件名数组
        String[] paths = dir.list();
        // 声明字符串数组，长度为传入文件的个数
        String[] files = new String[paths.length];
        // 遍历显示文件绝对路径
        for (int i = 0; i < paths.length; i++) {
            files[i] = absolutePath + "/" + paths[i];
        }
        return files;
    }

    /**
     * 创建文件或目录
     * @param path
     * @param isFile
     */
    public static void createFile(String path, boolean isFile) {
        // 调用方法创建新文件或目录
        createFile(new File(path), isFile);
    }

    /**
     * 创建文件
     * @param file
     * @param isFile
     */
    public static void createFile(File file, boolean isFile) {
        // 如果文件不存在
        if (!file.exists()) {
            // 如果文件父目录不存在
            if (!file.getParentFile().exists()) {
                createFile(file.getParentFile(), false);
            } else {
                // 存在文件父目录
                if (isFile) {
                    try {
                        // 创建文件
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // 创建目录
                    file.mkdir();
                }
            }
        }
    }

    public static void main(String[] args) {
        // 目录路径
        String fromPath = "C:/createFile";
        // 源路径
        String toPath = "E:/createFile";
        System.out.println("1.移动文件：从路径 " + fromPath + " 移动到路径 " + toPath);
        try {
            // 调用方法实现文件的移动
            fileMove(fromPath, toPath);
        } catch (Exception e) {
            System.out.println("移动文件出现问题" + e.getMessage());
        }
        System.out.println("2.复制目录 " + toPath + " 下的文件（不包括该目录）到指定目录" + fromPath
                + " ，会连同子目录一起复制过去。");
        // 调用方法实现目录复制
        copyFileFromDir(fromPath, toPath);
        System.out.println("3.复制目录 " + fromPath + "到指定目录 " + toPath
                + " ,将目录以及目录下的文件和子目录全部复制到目标目录");
        // 调用方法实现目录以用目录下的文件和子目录全部复制
        copyDir(toPath, fromPath);
    }
}
