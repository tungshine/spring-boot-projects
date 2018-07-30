package com.tungshine.mongo.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.tungshine.mongo.model.User;
import com.tungshine.mongo.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: TungShine
 * @Description:
 * @Date: Create in 1:08 2018/7/19
 * @Modified By:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private GridFS gridFS;

    @RequestMapping("/get/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @RequestMapping("/add")
    public String add(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam("age") Integer age) {
        User user = new User(id, name, age);
        userService.addUser(user);
        return "success";
    }

    @RequestMapping("upload")
    public void upload(@RequestParam("file") MultipartFile file) {
        try {
            ObjectId objectId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename());
            System.out.print(objectId);
            System.out.print(file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("download")
    public void download(@RequestParam("filename") Object filename, HttpServletResponse response) {
        GridFSDBFile gridFSDBFile = gridFS.findOne(new BasicDBObject("filename", filename));
        System.out.print(gridFSDBFile.getFilename());
        try {
            OutputStream sos = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + gridFSDBFile.getFilename() + "\"");
            gridFSDBFile.writeTo(sos);
            sos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
