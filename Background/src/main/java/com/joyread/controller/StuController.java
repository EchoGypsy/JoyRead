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



//����Ϊ��������
@Controller
public class StuController {
	
	//�õ��ӿڶ���
    @Autowired
    StuDao stuDao;

    /**
     * ѧ���û��ĵ�¼����
     * �ж�ѧ�������Ƿ���ȷ
     * �����ȷ�����ء�ok��������ȷ���ء�no�����ַ�����
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
