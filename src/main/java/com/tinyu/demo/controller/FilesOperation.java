package com.tinyu.demo.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "文件相关")
@RequestMapping(value="/files")
public class FilesOperation {
	private static final long serialVersionUID = 1L;
	// 定义允许上传的文件扩展名
	private String Ext_Name = "gif,jpg,jpeg,png,bmp,swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2";
	/**
	 *   上传文件
	 * @return
	 */
	@PostMapping(value = "/upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ResponseBody
    @ApiOperation(value = "文件上传", notes = "文件上传")
	public String upload(@ApiParam(value = "上传的文件", required = true) MultipartFile file) {
		if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String filePath = "C:\\Users\\Administrator\\Desktop\\temp\\";
        //如果目录不存在 则创建目录
        File saveFileDir = new File(filePath);
        if (!saveFileDir.exists()) {
            // 创建WEB-INF/upload目录
            saveFileDir.mkdirs();
        }
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            return e.toString();
        }
	} 
	
	
	@GetMapping("/download")
	@ApiOperation(value = "文件下载", notes = "文件下载")
	public String download(HttpServletResponse response) {
		String fileName = "download.xls";// 文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File("C:\\Users\\Administrator\\Desktop\\test.xls");
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
	}
	
}
