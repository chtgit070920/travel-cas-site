package com.travel.cas.site.member.service;

import java.util.Date;

import com.travel.common.utils.IdUtil;
import org.jasig.cas.authentication.handler.PasswordEncoder;
import org.springframework.util.StringUtils;

import com.travel.cas.site.member.dao.UserDao;
import com.travel.cas.site.member.model.User;

public class UserService {

	//加密
	private PasswordEncoder passwordEncoder;
	private UserDao userDao;
	
	
	public Integer register(User user,String ip) {
		Date now=new Date();//时间为当前
		
		user.setId(IdUtil.uuid());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEmail(StringUtils.hasText(user.getEmail())?user.getEmail():null);
		user.setName(StringUtils.hasText(user.getName())?user.getName():user.getMobile());
		user.setSex(StringUtils.hasText(user.getSex())?user.getSex():User.sex_male);
		user.setBirthday((null!=user.getBirthday())?user.getBirthday():User.birthday_defalut);
		user.setCurlogintime(now);
		user.setCurloginip(ip);
		user.setLastlogintime(now);
		user.setLastloginip(ip);
		user.setCjtime(now);
		user.setXgtime(now);
		user.setState(User.state_active);
		
		return userDao.save(user);
		
		//throw new AccountAlreadyExistException();
	}

	public User getByLoginname(String loginname){
		return userDao.getByLoginname(loginname);
	}
	
	public Integer modifyPwd(String userid,String newpwd){
		return userDao.modifyPwd(userid,passwordEncoder.encode(newpwd));
	}
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
