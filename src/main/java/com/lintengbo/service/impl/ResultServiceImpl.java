package com.lintengbo.service.impl;

import com.lintengbo.service.ResultService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class ResultServiceImpl implements ResultService {
    @Override
    public void getSortedData(HttpServletRequest request, HttpServletResponse response) {
        File file = new File("SortedData.txt");
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream()//获取response的输出流对象
        ) {
            //设置ContentType字段告知浏览器返回内容类型
            response.setContentType("plain/text");
            //设置Header字段
            response.setHeader("Content-Disposition", "attachment;filename=" +
                    URLEncoder.encode("SortedData.txt", StandardCharsets.UTF_8));
            //下面这行代码必须加，否则前端获取不到Content-Disposition字段，即无法获取文件名
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            //将读取的文件流复制到response的输出流中
            IOUtils.copy(inputStream, outputStream);
            //刷新输出流
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
