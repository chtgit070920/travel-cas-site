package com.travel.cas.site.member.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.travel.common.utils.DateUtil;
import org.hibernate.validator.constraints.Email;

public class User implements  Serializable{

	private static final long serialVersionUID = -2900770196032442384L;
	
	public static final String state_active="1";//活动
	public static final String state_freeze="2";//冻结
	
	public static final String sex_male="1";//男
	public static final String sex_female="2";//女
	
	public static final Date birthday_defalut= DateUtil.string_date("1900-01-01","yyyy-MM-dd");//默认生日
	
	private String id;//主键
	
	@NotNull
	@Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$",message="valid.mobile")
	private String mobile;//手机号
	
	@Email(message = "valid.email")
	private String email;//邮箱
	
	@NotNull
	@Pattern(regexp="^[a-zA-Z0-9]{6,16}$",message="valid.password")
	private String password;//密码
	
	private String name;
	
	//==============================第三方登录相关==============================
	private String webqqopenid;
	private String websinaopenid;
	private String webwebchartopenid;
	private String mobileqqopenid;
	private String mobilesinaopenid;
	private String mobilewebchartopenid;
	
	//==============================证件相关==============================
	private String sfzh;
	
	//==============================其他信息==============================
	private String sex;
	private Date birthday;
	
	//==============================登录时间/ip==============================
	private Date lastlogintime;
	private String lastloginip;
	private Date curlogintime;
	private String curloginip;
	
	private Date cjtime;//创建时间
	private Date xgtime;//修改时间
	
	private String state;//状态
	
	//==============================not used filed==============================
	@NotNull
	@Pattern(regexp="^[a-zA-Z0-9]{6,16}$",message = "valid.password")
	@Transient
	private String confirmpassword;//确认密码
	@Transient
	private String agree;//同意条款
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public Date getCjtime() {
		return cjtime;
	}
	public void setCjtime(Date cjtime) {
		this.cjtime = cjtime;
	}
	public Date getXgtime() {
		return xgtime;
	}
	public void setXgtime(Date xgtime) {
		this.xgtime = xgtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAgree() {
		return agree;
	}
	public void setAgree(String agree) {
		this.agree = agree;
	}
	public String getWebqqopenid() {
		return webqqopenid;
	}
	public void setWebqqopenid(String webqqopenid) {
		this.webqqopenid = webqqopenid;
	}
	public String getWebsinaopenid() {
		return websinaopenid;
	}
	public void setWebsinaopenid(String websinaopenid) {
		this.websinaopenid = websinaopenid;
	}
	public String getWebwebchartopenid() {
		return webwebchartopenid;
	}
	public void setWebwebchartopenid(String webwebchartopenid) {
		this.webwebchartopenid = webwebchartopenid;
	}
	public String getMobileqqopenid() {
		return mobileqqopenid;
	}
	public void setMobileqqopenid(String mobileqqopenid) {
		this.mobileqqopenid = mobileqqopenid;
	}
	public String getMobilesinaopenid() {
		return mobilesinaopenid;
	}
	public void setMobilesinaopenid(String mobilesinaopenid) {
		this.mobilesinaopenid = mobilesinaopenid;
	}
	public String getMobilewebchartopenid() {
		return mobilewebchartopenid;
	}
	public void setMobilewebchartopenid(String mobilewebchartopenid) {
		this.mobilewebchartopenid = mobilewebchartopenid;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public String getLastloginip() {
		return lastloginip;
	}
	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}
	public Date getCurlogintime() {
		return curlogintime;
	}
	public void setCurlogintime(Date curlogintime) {
		this.curlogintime = curlogintime;
	}
	public String getCurloginip() {
		return curloginip;
	}
	public void setCurloginip(String curloginip) {
		this.curloginip = curloginip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
