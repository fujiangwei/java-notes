package com.notes.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParamNoteHandle {

    // 老的类名
    private static String oldDtoName = "";
    public static final Integer DICT_MARKETNO_ZJS = 7;
    public static void main(String[] args) throws Exception {

//        StringBuilder nameStr = new StringBuilder();
//        String[] s = "stock_type_name".split("_");
//        for (int i = 0; i < s.length; i++) {
//            s[i] = s[i].substring(0, 1).toUpperCase() + s[i].substring(1);
//            nameStr.append(s[i]);
//        }
//
//        String name = nameStr.toString();
//        System.out.println("1  " + name);
//        name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
//        System.out.println("2  " + name);

//        Integer dd = 7;
//        System.out.println(DICT_MARKETNO_ZJS.equals(dd));
//        System.out.println(!DICT_MARKETNO_ZJS.equals(dd));

//        System.out.println(Integer.parseInt("1") + "m");
//        System.out.println((Integer.parseInt("1") + "m"));
//        System.out.println(DictConstants.FLAG_NO.getValue().equals("0"));


//        String description = "marketGroupNoHA";
//        StringBuilder result = new StringBuilder();
//        String[] parts = StringUtils.splitByCharacterTypeCamelCase(description.replaceAll("<.+>", ""));
//        for (int i = 1; i < parts.length; i++) {
//            if (i > 1) {
//                result.append(StringUtils.capitalize(parts[i]));
//            } else {
//                result.append(StringUtils.uncapitalize(parts[i]));
//            }
//        }
//
//        String fundIdList = "-1";
//        StringBuilder fundIdListStr = new StringBuilder("-1");
//        for (String fundId : parts) {
//            fundIdList = fundIdList + "," + fundId;
//            fundIdListStr.append(",").append(fundId);
//        }
//
//        System.out.println(fundIdList);
//        System.out.println(fundIdListStr.toString());
//        System.out.println(fundIdList.equals(fundIdListStr.toString()));
//
//        System.out.println(result.toString());
//        System.out.println(StringUtils.capitalize(result.toString()));

        String filePath = "D:/hs/soft/sonar/sonar检查/Sources/IASPWeb/OPlusWeb/IS2WEB-services/src/main/java/" +
                "investment/basepublic/stock/model/";
        String fileName = "StockinfomodifyAddModel";
        String decode = new String(filePath.getBytes(), "utf-8");
        File file = new File(decode + fileName + ".java");
        handleFile(file);
    }

    static Pattern chnPattern = Pattern.compile("[\u4e00-\u9fa5]");
    public static String handleChn(String pathStr) throws Exception {
        String[] str = pathStr.split("/");
        for (int i = 0; i < str.length; i ++) {
            Matcher matcher = chnPattern.matcher(str[i]);
            if (matcher.find()) {
                pathStr = pathStr.replaceFirst(str[i], URLEncoder.encode(str[i],"UTF-8"));
            }
        }

        return pathStr;
    }

    public static String classNameGet(File fileIn) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileIn))) {
            // 行内容
            String lineContent;
            // DTO类名
            String dtoName = "";
            while ((lineContent = br.readLine()) != null) {
                lineContent = lineContent.trim();
                // 空行
                if (StringUtils.isEmpty(lineContent)) {
                    continue;
                }

                // 类名处理
                if (lineContent.contains("public") && lineContent.contains("class")) {
                    int classFlagIndex = lineContent.indexOf("class") + 1;
                    String containClassName = lineContent.substring(classFlagIndex + 5);
                    dtoName = containClassName.substring(0, containClassName.indexOf(" ")).trim();
                    return dtoName;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void handleFile(File fin) throws IOException {
        FileWriter fileWriter = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fin))) {
            // 行内容
            String lineContent;
            // 原先字段部分数据
            String oldFieldPart = "";
            // 包含字段值部分
            String fieldPart = "";
            // 字段尾注释
            String fieldNote = "";
            fileWriter = new FileWriter("C:\\Users\\hspcadmin\\Desktop\\POJO\\POJOOUT.java");
            // DTO类名
            String dtoName = "";
            StringBuilder toStringSB = new StringBuilder();
            toStringSB.append("@Override\n");
            toStringSB.append("public String toString() {\n");
            toStringSB.append("    return ");
            // 字段位置，用于控制是否需要加逗号隔开
            int fieldIndex = 0;
            while ((lineContent = br.readLine()) != null) {
                lineContent = lineContent.trim();
                // 空行
                if (StringUtils.isEmpty(lineContent)) {
                    continue;
                }

                if (lineContent.startsWith("@")) {
                    System.out.println("cur line contain anno >>>>>>>> " + lineContent);
                }

                // 类名处理
                if (lineContent.contains("public") && lineContent.contains("class")) {
                    int classFlagIndex = lineContent.indexOf("class") + 1;
                    String containClassName = lineContent.substring(classFlagIndex + 5);
                    dtoName = containClassName.substring(0, containClassName.indexOf(" ")).trim();
                    toStringSB.append("\"" + dtoName + "{\" +\n");
                    continue;
                }

                if (lineContent.contains("private") && lineContent.startsWith("private") && (!lineContent.contains("{") || !lineContent.contains("}"))) {
                    fieldIndex++;
                    // 被注释行
                    if (lineContent.startsWith("//")) {
                        System.out.println("cur line noted : " + lineContent);
                        fieldPart = lineContent.trim();
                        String[] split = lineContent.split(";");
                        if (split.length < 2) {
                            oldFieldPart = fieldPart;
                            // 无注释内容
                            if (lineContent.contains("=")) {
                                // 有赋值
                                fieldPart = fieldPart.split("=")[0].trim();
                                fieldNote = fieldPart.substring(fieldPart.lastIndexOf(" "), fieldPart.length()).trim();
                            } else {
                                // 没有赋值
                                fieldNote = fieldPart.substring(fieldPart.lastIndexOf(" "), fieldPart.length()).trim();
                            }
                        } else {
                            // 注释的字段有注释内容
                            fieldPart = lineContent.substring(0, lineContent.lastIndexOf("//")).trim();
                            fieldNote = lineContent.substring(lineContent.lastIndexOf("//") + 2).trim();
                            oldFieldPart = fieldPart;
                            if (fieldPart.contains("=")) {
                                fieldPart = fieldPart.split("=")[0].trim();
                            }
                            if (StringUtils.isEmpty(fieldNote)) {
                                fieldNote = fieldPart.substring(fieldPart.lastIndexOf(" "), fieldPart.length()).trim();
                            }
                        }
                    } else {
                        if (lineContent.contains("//")) {
                            fieldPart = lineContent.substring(0, lineContent.lastIndexOf("//")).trim();
                            fieldNote = lineContent.substring(lineContent.lastIndexOf("//") + 2).trim();
                            oldFieldPart = fieldPart;
                            if (fieldPart.contains("=")) {
                                fieldPart = fieldPart.split("=")[0].trim();
                            }
                            // 注释为空处理
                            if (StringUtils.isEmpty(fieldNote)) {
                                fieldNote = fieldPart.substring(fieldPart.lastIndexOf(" "), fieldPart.length()).trim().split(";")[0];
                            }
                        } else {
                            fieldPart = lineContent.trim();
                            oldFieldPart = fieldPart;
                            // 处理有赋值的字段
                            if (fieldPart.contains("=")) {
                                fieldPart = fieldPart.split("=")[0].trim();
                            }
                            fieldNote = fieldPart.substring(fieldPart.lastIndexOf(" "), fieldPart.length()).split(";")[0].trim();
                        }

                        // 当前字段值
                        String curToStringField;
                        if (lineContent.contains("=")) {
                            curToStringField = fieldPart.substring(fieldPart.lastIndexOf(" "), fieldPart.length()).trim();
                        } else {
                            curToStringField = fieldPart.substring(fieldPart.lastIndexOf(" "), fieldPart.length() - 1).trim();
                        }
                        // 字段加入toString方法中
                        if (fieldIndex == 1) {
                            toStringSB.append("        \"" + curToStringField + "=\" + " + curToStringField + " + \n");
                        } else {
                            toStringSB.append("        \"," + curToStringField + "=\" + " + curToStringField + " + \n");
                        }
                    }

                    // 字段注释
                    StringBuilder noteSB = new StringBuilder();
                    if (StringUtils.isNotEmpty(fieldNote)) {
                        noteSB.append("/**\n");
                        noteSB.append(" * " + fieldNote + "\n");
                        noteSB.append(" */\n");
                        noteSB.append(oldFieldPart + "\n");
                        fileWriter.write(noteSB.toString() + "\n");
                    } else {
                        fileWriter.write("\n");
                        fileWriter.write(oldFieldPart + "\n");
                    }
                }
            }
            toStringSB.append("        \"} \" + super.toString();\n}");
            // 生成toString方法
            fileWriter.write(toStringSB.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fileWriter) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void readFile2(File fin) throws IOException {
        // Construct BufferedReader from FileReader
        BufferedReader br = new BufferedReader(new FileReader(fin));

        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }

    // java8
    private static void readFile3(File fin) throws IOException {
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(fin.toPath(), charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    // 第三方库
    private static void readFile4(File fin) throws IOException {
        try {
            System.out.println("Reading files using Apache IO:");
            List<String> lines = FileUtils.readLines(fin, "UTF-8");
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}