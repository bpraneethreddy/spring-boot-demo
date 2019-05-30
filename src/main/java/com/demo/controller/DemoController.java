package com.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.DemoService;

@RestController
public class DemoController {

	@Autowired 
	DemoService demoService;
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("title", "");
		model.put("message", "Hello World");
		return "welcome";
	}
	
	@RequestMapping("/login")
	public String ServiceUnavailable() {
		return "login";
	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.POST)
	public ResponseEntity<Object> getUsers(@RequestBody HashMap<String, Object> reqData) throws Exception {
		List<Map<String, Object>> userList=demoService.getUsers();
		return new ResponseEntity<Object>(userList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ResponseEntity<Object> saveUser(@RequestBody HashMap<String, Object> reqData) throws Exception {
		Integer id=demoService.saveUser(reqData);
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("id", id);
		data.put("success", true);
		data.put("message", "successfully inserted");
		return new ResponseEntity<Object>(data,HttpStatus.OK);
	}
}
