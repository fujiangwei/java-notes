package com.notes.controller;

import com.notes.utils.ZipUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 文件描述 文件管理控制器
 **/
@RestController
public class FileManagerController {

    private static final String zipPath = "C:\\Users\\hspcadmin\\Desktop\\fstore.zip";

    /**
     * 下载生成的所有在线/离线用户信息表格
     * @param request
     * @param response
     * @return 压缩文件
     * @throws IOException
     */
    @GetMapping(value = "doDownload")
    public File downloadExcel (HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //提供下载文件前进行压缩，即服务端生成压缩文件
        File file = new File(zipPath);
        FileOutputStream fos = new FileOutputStream(file);
        //1.获取要下载的文件的绝对路径
        String realPath = zipPath;
        //2.获取要下载的文件名
        String fileName = realPath.substring(realPath.lastIndexOf(File.separator) + 1);
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/zip");
        //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
        response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(), StandardCharsets.UTF_8));
        ZipUtils.toZip("C:\\Users\\hspcadmin\\Desktop\\fstore", response.getOutputStream(), true);
        //获取文件输入流
        InputStream in = new FileInputStream(realPath);
        int len = 0;
        byte[] buffer = new byte[1024];
        OutputStream out = response.getOutputStream();
        while ((len = in.read(buffer)) > 0) {
            //将缓冲区的数据输出到客户端浏览器
            out.write(buffer,0,len);
        }
        in.close();
        out.close();
        fos.close();
        return file;
    }

    /**
     * 下载zip压缩包参考流程
     * @param request
     * @param response
     * @date  2020/6/2 13:49
     * @author  fujw27320
     * @modifier
     * @return  void
     */
    public void demoDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /** 1.创建临时文件夹  */
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        File temDir = new File(rootPath + "/" + UUID.randomUUID().toString().replaceAll("-", ""));
        if(!temDir.exists()){
            temDir.mkdirs();
        }
        /** 2.生成需要下载的文件，存放在临时文件夹内 */

        /** 3.设置response的header */
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=excel.zip");
        /** 4.调用工具类，下载zip压缩包 */
        ZipUtils.toZip(temDir.getPath(), response.getOutputStream(),false);
        /** 5.删除临时文件和文件夹 */
        File[] listFiles = temDir.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            listFiles[i].delete();
        }
        temDir.delete();
    }
}