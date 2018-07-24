package com.tungshine.mongo.controller;

import com.tungshine.mongo.model.User;
import com.tungshine.mongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author: TungShine 
 * @ Description: 
 * @ Date: Create in 1:08 2018/7/19 
 * @ Modified By:
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/get/{id}")
	public User getUser(@PathVariable int id) {
		return userService.getUser(id);
	}

	@RequestMapping("/add")
	public String add(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam("age") Integer age) {
		User user = new User(id, name, age);
		userService.addUser(user);
		return "success";
	}
}
