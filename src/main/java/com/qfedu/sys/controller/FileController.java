package com.qfedu.sys.controller;

import com.qfedu.sys.utils.AppFileUtils;
import com.qfedu.sys.utils.DataGridView;
import com.qfedu.sys.utils.RandomUtils;
import com.qfedu.sys.utils.SysConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 *
 * 完成图片的上传和下载操作
 */
@RequestMapping("file")
@Controller
public class FileController {

    /**
     * 查看缩略图，其实就是下载图片，从本地 D://upload目录下
     * 参数：String path ,  Response
     * 返回值：ResponseEntity 他是springmvc提供的一个下载相关的对象
     */
    @RequestMapping("downloadShowFile")
    public ResponseEntity<Object> downloadShowFile(String path , HttpServletResponse response){
        try {
            //文件下载，下载图片，封装到ResponseEntry
            ResponseEntity<Object> responseEntity = AppFileUtils.downloadFile(response, path, "");
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 文件上传功能
     * 参数: MultipartFile  mf  是springmvc提供的用于上传的对象 (比如获取源文件名称)
     * 返回值: DataGridView  (图片路径保存在该对象中,用于做回显)
     *
     * 2.在springmvc.xml文件中配置 MultipartFile 初始哈
     */

    @PostMapping("uploadFile")
    @ResponseBody
    public DataGridView uploadFile(MultipartFile mf){
        try {
            //1.获取父级目录  D://upload
            String parentDir = AppFileUtils.PATH;
            //2.创建子目录(一天一个目录存放图片)
            String childDir = RandomUtils.getCurrentDateForString();  // 创建目录 2023-06-30
            //3.合并目录 file文件
            File file = new File(parentDir,childDir); //    D://upload/2023-06-30
            //4.如果这个文件不存在 (D:/upload/20230629) 那就创建一个
            if(!file.exists()){
                file.mkdirs();
            }
            //5.获取源文件名称
            String oldName = mf.getOriginalFilename(); //001.jpg
            //6.由于源文件名称不能作为当前文件名称,(怕和服务器以后图片重复) , 更改源文件名称
            String fileName = RandomUtils.createFileNameUseTime(oldName, SysConstant.FILE_UPLOAD_TEMP);  // 202306290207002207688.jpg_temp

            //7.(D:/upload/20230629)  路径和修改过的源文件名称组合成一个新的file对象
            File destFile = new File(file,fileName);
            //8.上传
            mf.transferTo(destFile);

            //9.因为还要做图片的回显,所以我们要图片的相对路径返回,让前台加载,显示图
            Map<String,Object> map = new HashMap<>();
            map.put("src",childDir+"/"+fileName);

            //10.将map放到DataGridView对象中
            return new DataGridView(map);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
