package com.travel.cas.site.member.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.travel.cas.site.config.Global;

public class Retake implements  Serializable{
	
	private static final long serialVersionUID = 1L;

	public static class Input implements  Serializable{
		private static final long serialVersionUID = 1L;
		private String username;//帐号名
		private String validcode;//图片验证码
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getValidcode() {
			return validcode;
		}
		public void setValidcode(String validcode) {
			this.validcode = validcode;
		}
	}
	
	public static class Verify implements  Serializable{
		private static final long serialVersionUID = 1L;
		
		private String type;//验证方式
		private String mobile;//手机
		private String email;//email
		private String msgcode;//短信码
		
		private  Map<String,String> typeItems;//验证方式
		
		private String send;//点击发送验证码？
		

		public Verify(){}
		
		public String getType() {
			if(StringUtils.isEmpty(this.type)){//默认取第一个配置
				String config=Global.getConfig("retakeP.verify.method");
				String[] splits = config.split(Global.UNIT_SEP);
				for(String split:splits){//取第一个
					return StringUtils.substringBefore(split, Global.INNER_SEP);
				}
			}
			return type;
		}
		public void setType(String type) {
			this.type = type;
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
		public String getMsgcode() {
			return msgcode;
		}
		public void setMsgcode(String msgcode) {
			this.msgcode = msgcode;
		}
		public Map<String, String> getTypeItems() {
			if(null==typeItems){
				typeItems=new HashMap<String,String>();
				String config=Global.getConfig("retakeP.verify.method");
				String[] splits = config.split(Global.UNIT_SEP);
				for(String split:splits){
					typeItems.put(StringUtils.substringBefore(split, Global.INNER_SEP), 
							StringUtils.substringAfter(split, Global.INNER_SEP));
				}
			}
			return typeItems;
		}
		public void setTypeItems(Map<String, String> typeItems) {
			this.typeItems = typeItems;
		}
		public String getSend() {
			return send;
		}
		public void setSend(String send) {
			this.send = send;
		}
		
	}
	
	public static class Reset implements  Serializable{
		private static final long serialVersionUID = 1L;
		private String newpwd;//新密码
		private String confirmpwd;//确认密码
		public String getNewpwd() {
			return newpwd;
		}
		public void setNewpwd(String newpwd) {
			this.newpwd = newpwd;
		}
		public String getConfirmpwd() {
			return confirmpwd;
		}
		public void setConfirmpwd(String confirmpwd) {
			this.confirmpwd = confirmpwd;
		}
	}
	
}
