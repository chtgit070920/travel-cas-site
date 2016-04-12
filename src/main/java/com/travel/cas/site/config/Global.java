package com.travel.cas.site.config;

import java.util.HashMap;
import java.util.Map;

import com.travel.common.utils.PropertiesLoader;
import org.apache.commons.lang3.StringUtils;


public class Global {
	
	//分割符
	public static final String INNER_SEP="^";
	public static final String UNIT_SEP=",";
	
	
	//当前对象实例
	private static Global global = new Global();
	
	
	//获取当前对象实例
	public static Global getInstance() {
		return global;
	}
	
	//保存全局属性值
	private static Map<String, String> map = new HashMap<String, String>();
	
	//加载属性文件
	private static PropertiesLoader loader = new PropertiesLoader("messages/project.properties");
	
	//获取值
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}
	 
}
