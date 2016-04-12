/**
 * 增加新的验证
 * 所有的验证扩展请放置于此
 */  

 // 手机号码验证   
 jQuery.validator.addMethod("cellphone", function(value, element) {
   var length = value.length;
   return this.optional(element) || (length == 11 && /^(13|15|18)\d{9}$/.test(value));
 }, "请输入正确的手机号"); 
 
 
 // 密码   [！@#￥%^&*1234567890?a-z A-Z]
 jQuery.validator.addMethod("password", function(value, element) {
   var length = value.length;
   return this.optional(element) || (6<=length <=16 && /^[0-9a-zA-Z_`!！@#\$￥%^&\*\[\]\(\)\{\}\|\?\+\-\/,.<>\;:'‘“"]{6,20}$/.test(value));
 }, "密码需为6-16个字符,由字母、数字和符号组成"); 
 
 //用户名验证
 jQuery.validator.addMethod("username", function(value, element) {
	   var length = value.length;
	   
	   var cellphone=(length == 11 && /^(1\d{10})$/.test(value));
	   var email=/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/.test(value);
	   var account= /^[t]{1}[0-9]+$/.test(value);
	   //alert(cellphone);alert(email);alert(username);

	   return  this.optional(element) || cellphone||email||account;
	 }, "请输入正确的账号/手机号/邮箱"); 
 


 
 
 
 
//代码
 jQuery.validator.addMethod("code", function(value, element) {
   return this.optional(element) || /^[A-Z]{1}([0-9]{3})$/.test(value);
 }, "请输入正确的代码，例如A001"); 

//字母数字
 jQuery.validator.addMethod("alnum", function(value, element) {
   return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
 }, "请输入英文字母或数字");
 
 // 汉字
 jQuery.validator.addMethod("chinese", function(value, element) {
   var tel = /^[\u4e00-\u9fa5]+$/;
   return this.optional(element) || (tel.test(value));
 }, "请输入汉字");
 
 //字母数字汉字
 jQuery.validator.addMethod("zsh", function(value, element) {
   var tel = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;
   return this.optional(element) || (tel.test(value));
 }, "请输入字母或数字或汉字");
 
 

 
 // 电话验证   
 jQuery.validator.addMethod("telephone", function(value, element) {
   return this.optional(element) || ( /^(\d{3}-)(\d{8})$|^(\d{4}-)(\d{7})$|^(\d{4}-)(\d{8})$/.test(value));
 }, "请正确填写电话号码"); 
 
 // 身份证号码验证   
 jQuery.validator.addMethod("idcard", function(value, element) {
	 var isIdCardNo=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/;
   return this.optional(element) || (isIdCardNo.test(value));   
 }, "请正确输入身份证号码");
 
//邮政编码验证
 jQuery.validator.addMethod("zipcode", function(value, element) {
   var tel = /^[0-9]{6}$/;
   return this.optional(element) || (tel.test(value));
 }, "请输入合法的邮政编码");
 
 //只能输入数字  /^\d*$/
 jQuery.validator.addMethod("number", function(value, element) {
	   var num = /^\d*$/;
	   return this.optional(element) || (num.test(value));
	 }, "请输入数字");
	 
 //自定义身份证验证方法  
 jQuery.validator.addMethod("idcard2",function(value, element){ 
return validateIdCard(value);
},"请输入正确的身份证号"); 
///身份证验证
function validateIdCard(idCard){
	//15位和18位身份证号码的正则表达式
	var regIdCard=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
	//如果通过该验证，说明身份证格式正确，但准确性还需计算
	if(regIdCard.test(idCard)){
	if(idCard.length==18){
	var idCardWi=new Array( 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ); //将前17位加权因子保存在数组里
	var idCardY=new Array( 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
	var idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和
	for(var i=0;i<17;i++){
	 idCardWiSum+=idCard.substring(i,i+1)*idCardWi[i];
	}
	var idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
	var idCardLast=idCard.substring(17);//得到最后一位身份证号码
	//如果等于2，则说明校验码是10，身份证号码最后一位应该是X
	if(idCardMod==2){
	 if(idCardLast=="X"||idCardLast=="x"){
	    return true;// alert("恭喜通过验证啦！");
	 }else{
	     return false;//alert("身份证号码错误！");
	 }
	}else{
	 //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
	 if(idCardLast==idCardY[idCardMod]){
	     return true;//alert("恭喜通过验证啦！");
	 }else{
	     return false;//alert("身份证号码错误！");
	 }
	}
	} 
	}else{
	return false;// alert("身份证格式不正确!");
	}
}