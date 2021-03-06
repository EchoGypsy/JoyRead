package com.joyread.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyread.dao.StuDao;
import com.joyread.domain.Stu;

import net.sf.json.JSONObject;



//声明为控制器类
@Controller
public class StuController {
	
	//拿到接口对象
    @Autowired
    StuDao stuDao;

    /**
     * 学生用户的登录函数
     * 判断学号密码是否正确
     * 如果正确，返回“ok”，不正确返回“no”（字符串）
     */
    @RequestMapping(value = "/stuLogin", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response){
    	Stu stu = new Stu();
    	try {
			String streamIn = IOUtils.toString(request.getInputStream());
			JSONObject object = JSONObject.fromObject(streamIn); 
			stu = stuDao.login(object.getString("sID"),object.getString("password"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	System.out.println(stu);
    	if(stu != null){
    		return "ok";
    	}else{
    		return "no";
    	}
    }
}
