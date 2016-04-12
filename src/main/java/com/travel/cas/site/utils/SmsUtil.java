package com.travel.cas.site.utils;

import com.travel.cas.site.config.Global;
import com.travel.common.utils.DateUtil;
import com.travel.common.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//短信发送
public class SmsUtil {

	private static Logger logger = LoggerFactory.getLogger(SmsUtil.class);


	//找回密码，验证码发送
	public static boolean sendYzm(String mobile, String yzm) {
		//1、短信模板-->短信实例
		Map<String, String> params_ms = new HashMap<String, String>();
		params_ms.put("yzm", yzm);
		//System.out.println(params_ms);

		String content = Global.getConfig("sms.template_yzm");
		String reg = "\\$\\{\\w*\\}";//
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			String group = matcher.group();//
			String key = group.substring(2, group.length() - 1);
			if (!params_ms.containsKey(key)) throw new RuntimeException("message template need param 《" + key + "》 !");
			content = content.replace(group, params_ms.get(key));
		}
		logger.info("message#content: {}", content);

		//3、发送短信
		Map<String, String> params = new HashMap<String, String>();
		params.put("mobiles", mobile);
		params.put("content", content);
		params.put("sendtime", DateUtil.date_string(new Date(), "yyyyMMddHHmmss"));
		params.put("sign", Global.getConfig("sms.sign"));

		String ret = HttpClientUtil.req(Global.getConfig("sms_wdlt.gateway"), params);
		logger.info("send state:{} for message ({})", ret, content);

		if (StringUtils.isBlank(ret)) {
			ret = StringUtils.EMPTY;
		}

		if (ret.startsWith(Global.getConfig("sms_wdlt.publish_ok")))//
			return true;
		else
			return false;

	}


	public static void main(String args[]) throws SecurityException, NoSuchMethodException {
		sendYzm("13721421878", "123456");
		//place("13721421878","123456789",100d,new Date(),new Date());
		//boolean result=cancle("13721421878",new Date(),new Date());
		//System.out.println(result);

//		Method method = PublishUtil.class.getMethod("doSomething",
//				Integer.class, String.class);
//		for (int i = 0; i < method.getParameterTypes().length; i++) {
//			Param param = (Param) method.getParameterAnnotations()[i][0];
//			System.out.printf("Parameter name #%d: %s\n", i, param.value());
//		}

		//System.out.println(DateUtil.date_string(new Date(),"yyyyMMddHHmmss"));

	}
}
