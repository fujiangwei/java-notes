package com.notes.java.file;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;

/**
 * 文件描述 使用apache的FileUtils
 */
public class FileUtil {

    /**
     * 测试时请修改该路径
     */
    static String testPath = "C:\\Users\\admin\\Desktop\\file\\";

    public static void main(String[] args) {

        System.out.println("1111 "+ File.separator + " 2222 " + File.pathSeparator);

        String aaa = "X:\\YYYYMMDD";
        System.out.println(":" + File.separator);
        String[] split = aaa.split(":" + "\\\\");

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            // 根据名称获取文件
            File fileA = FileUtils.getFile(testPath + "a.txt");
            // 最终路径为入参拼接起来即testPath + "a.txt"
            File fileAA = FileUtils.getFile(testPath, "a.txt");
            // 获取某个目录下的文件，后面参数是拼接而成的
            File fileAAA = FileUtils.getFile(new File(testPath), "a.txt");

            // 获取临时目录路径
            // 每种操作系统有所不同，Windows一般是C:\DOCUME~1\用户~1\LOCALS~1\Temp，Solaris一般是:/var/tmp/，Linux和Mac OS X一般是:/tmp
            // Windows的java.io.tmpdir属性值可以看环境变量->用户变量中的TMP。
            File tempDirectory = FileUtils.getTempDirectory();
            String tempDirectoryPath = FileUtils.getTempDirectoryPath();
            File userDirectory = FileUtils.getUserDirectory();
            String userDirectoryPath = FileUtils.getUserDirectoryPath();
            System.out.println(tempDirectoryPath + "  -  " + tempDirectory);
            System.out.println(userDirectoryPath + "  -  " + userDirectory.getName());

            // 创建文件(实现与Unix上“ touch”实用程序相同的行为。它会创建一个大小为0的新文件，或者如果文件已经存在，则将其打开和关闭而不进行修改，而是更新文件的日期和时间。)
            File fileD = new File(testPath + "d.txt");
            FileUtils.touch(fileD);

            // 写文件
            File fileB = new File(testPath + "b.txt");
            if (!fileB.exists()) {
                fileB.createNewFile();
            }
            // 通过字节写入文件
            FileUtils.writeByteArrayToFile(fileB, "Hello, smile".getBytes());
            FileUtils.writeByteArrayToFile(fileB, "Hello, sunshine".getBytes(), true);
            // 通过行写入文件（覆盖）
            FileUtils.writeLines(fileB, Lists.newArrayList("春夏秋冬, 三餐四季，只愿有你"));
            // 通过行写入文件（追加）
            FileUtils.writeLines(fileB, Lists.newArrayList("春风很柔，夏风很烈，秋风清爽，东风凛冽，你就是春夏秋冬的风"), true);
            // 字符串写入，不换行追加
            FileUtils.writeStringToFile(fileB, "唯美有你", "UTF-8", true);
            FileUtils.writeStringToFile(fileB, "哪里有你", Charset.defaultCharset(), true);
            File fileC = new File(testPath + "c.txt");
            if (!fileC.exists()) {
                fileC.createNewFile();
            }
            //  流操作
            fileInputStream   = FileUtils.openInputStream(fileB);
//            fileOutputStream = FileUtils.openOutputStream(fileC);
            fileOutputStream = FileUtils.openOutputStream(fileC, true);
            // 字节数组
            byte[] buffer = new byte[1024];
            // 将文件内容写到文件中
            while (fileInputStream.read(buffer) != -1) {
                fileOutputStream.write(buffer);
            }

            // 读取文件
            // 按行读取文件内容
            List list = FileUtils.readLines(fileAAA, "UTF-8");
            System.out.println(list.size() > 0 ? list.get(0) : "" + list.size());
            List<String> lines = FileUtils.readLines(fileB, Charset.defaultCharset());
            System.out.println(lines.size() > 0 ? lines.get(0) : "" + lines.size());
            // 读取文件内容为字节
            byte[] bytes = FileUtils.readFileToByteArray(fileB);
            System.out.println(new String(bytes));
            // 读取文件为String
            String fileToString = FileUtils.readFileToString(fileB, "UTF-8");
            System.out.println(fileToString);
            String fileToString2 = FileUtils.readFileToString(fileB, Charset.defaultCharset());
            System.out.println(fileToString2);

            // 文件集合转换成文件数组
            File[] files = FileUtils.convertFileCollectionToFileArray(Lists.newArrayList(fileA, fileB, fileC));
            // recursive是否递归查询，这里查询.txt文件,也可以指定其他格式的,如果extensions为null则查询所有类型的文件
            Collection<File> listFiles = FileUtils.listFiles(new File(testPath), null, true);
            Collection<File> listFiles2 = FileUtils.listFiles(new File(testPath), new String[]{"txt"}, true);
            // 获取某个目录下的所有文件 TrueFileFilter.INSTANCE表示匹配所有的
            Collection<File> files1 = FileUtils.listFiles(new File(testPath), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
            // 获取某个目录下的所有文件和文件目录 TrueFileFilter.INSTANCE表示匹配所有的
            Collection<File> files2 = FileUtils.listFilesAndDirs(new File(testPath), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);

            // 文件内容比较
            boolean aa = FileUtils.contentEquals(fileA, fileAA);
            boolean ab = FileUtils.contentEquals(fileA, fileB);

            // 文件复制,将文件A的内容复制给文件D
            FileUtils.copyFile(fileA, fileD);
            // 把文件复制到某个目录下
            FileUtils.copyFileToDirectory(fileD, new File(testPath + "inner"));
            // 把某个目录拷贝到另外一个目录
            FileUtils.copyDirectoryToDirectory(new File(testPath + "inner"), new File(testPath + "other"));
            // 把文件或目录拷贝到指定目录
            FileUtils.copyToDirectory(fileB, new File(testPath + "inner"));
            // 文件目录复制
            FileUtils.copyDirectory(new File(testPath + "inner"), new File(testPath + "first"));
            // 删除目录
            FileUtils.deleteDirectory(new File(testPath + "first"));
            // 删除文件或目录
            FileUtils.deleteQuietly(fileD);
            FileUtils.deleteQuietly(new File(testPath + "first"));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fileInputStream) {
                    fileInputStream.close();
                }

                if (null != fileOutputStream) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
