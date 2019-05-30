package com.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.DemoDao;

@Service
public class DemoService {

	@Autowired 
	DemoDao demoDao;
	
	public List<Map<String, Object>> getUsers()throws Exception{
		List<Map<String, Object>> userList=demoDao.getUsers();
		return userList;
	}

	public Integer saveUser(HashMap<String, Object> reqData) throws Exception {
		Integer id=demoDao.saveUser(reqData.get("name")+"",reqData.get("contact")+"",reqData.get("email")+"");
		return id;
	}

	
}
