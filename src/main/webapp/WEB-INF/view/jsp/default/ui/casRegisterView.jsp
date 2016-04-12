<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License.  You may obtain a
    copy of the License at the following location:

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

--%>
<!DOCTYPE html>

<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html >
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="logo.title" />_<spring:message code="screen.welcome.instructions.register" /></title>
<spring:theme code="standard.custom.css.file" var="customCssFile" />
<link rel="stylesheet" href="<c:url value="${customCssFile}" />" />
<link rel="icon" href="<c:url value="/favicon.ico" />" type="image/x-icon" />
</head>
<body id="cas">

	<div class="header">
		<div id="title">
			<div class="wrap">
				<div id="logo">
					<img alt="<spring:message code="logo.title" />" src="<c:url value="/images/logo.png"/>">
					<span><spring:message code="screen.welcome.instructions.register" /></span>
				</div>
				<div id="words" align="right">
					<span><spring:message code="logo.title.description" /></span>
				</div>
			</div>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<div class="wrap">
				<div id="register">
					<form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true">
						<form:errors path="*" id="msg" cssClass="errors" element="span" htmlEscape="false" />
						<div class="row">
							<spring:message code="screen.welcome.label.mobile.accesskey.register" var="mobileAccessKey" />
							<label class="first" for="mobile"> <spring:message 
							code="screen.welcome.label.mobile.register" /></label><form:input
							cssClass="" cssErrorClass=""
							id="mobile"  tabindex="1"
							accesskey="${mobileAccessKey}" path="mobile" autocomplete="off"
							htmlEscape="true" />
						</div>

						<div class="row">
							<spring:message code="screen.welcome.label.password.accesskey.register" var="passwordAccessKey" />
							<label class="first" for="password"><spring:message 
							code="screen.welcome.label.password.register" /></label><form:password 
							cssClass="" cssErrorClass=""
							id="password"  tabindex="2" path="password"
							accesskey="${passwordAccessKey}" htmlEscape="true"
							autocomplete="off" />
						</div>

						<div class="row">
							<spring:message code="screen.welcome.label.confirmpassword.accesskey.register" var="confirmpasswordAccessKey" />
							<label class="first" for="confirmpassword"><spring:message 
							code="screen.welcome.label.confirmpassword.register" /></label><form:password
							 cssClass="" cssErrorClass=""
							 id="confirmpassword" tabindex="3"
							 path="confirmpassword" accesskey="${confirmpasswordAccessKey}"
							 htmlEscape="true" autocomplete="off" />
						</div>

						<div class="row">
							<spring:message code="screen.welcome.label.email.accesskey.register" var="emailAccessKey" />
							<label class="first" for="email"><spring:message 
								code="screen.welcome.label.email.register" /></label><form:input 
								cssClass="" cssErrorClass=""
								id="email"  tabindex="4" path="email"
								accesskey="${emailAccessKey}" htmlEscape="true"
								autocomplete="off" />
						</div>

						<div class="row">
							<spring:message code="screen.welcome.label.agree.accesskey.register"  var="agreeAccessKey" />
							<label class="first"></label><form:checkbox 
								id="agree" path="agree" value="true" 
								accesskey="${agreeAccessKey}" /><label for="agree"
								class="agree"> 我已阅读并接受<a href="javascript:void(0);" id="agree-show">“出行服务网站条款”</a> 
							</label>
						</div>

						<div class="row btn-row">
							<input type="hidden" name="rt" value="${registerTicket}" /> 
							<input type="hidden" name="execution" value="${flowExecutionKey}" /> 
							<input type="hidden" name="_eventId" value="submit" /> 
							<label class="first"></label><input class="btn-submit" name="submit" accesskey="l" 
									 value="<spring:message code="screen.welcome.button.register" />"
									 tabindex="6" type="submit" />
							
						</div>
					</form:form>
				</div>

				<div id="other">
					<div class="other-content">
						
						<div class="oauth">
							<p>其他帐号登录：</p>
							<span class="qq"><a href="javascript:void(0)">QQ</a></span>
							<span class="sina"><a href="javascript:void(0)">新浪</a></span>
							<span class="weixin"><a href="javascript:void(0)">微信</a></span>
						</div>
						<div class="already">
							已有帐号？马上<a href="${loginUrl}">登录</a>
						</div>
					</div>
				</div>


			</div>
			
			<!-- END #wrap -->
		</div>
		<!-- END #content -->
	</div>
	<!-- END #container -->



	<div class="footer">
		<div id="copyright" align="center">
			<div class="wrap">
				<p>
					<spring:message code="copyright" />
				</p>
			</div>
		</div>
	</div>
	
	
	<div class="service-content" >
		<div>
			<h2>
				&lt;&lt;出行服务网站服务协议&gt;&gt;
			</h2> 
			<h3>
				一、服务条款的确认
			</h3>
			<p>
				出行服务网站的所有权与运营权归郑州王道乐途出行服务有限公司（以下简称“王道乐途“）所有。本服务条款具有法律约束力。一旦您点选”注册“并通过注册程序，即表示您自愿接受本协议之所有条款，并已成为出行服务网站的注册会员。用户在享用出行服务网站会员服务的同时，同意接受出行服务网站会员服务提供的各类信息服务。</p>
			<h3>
				二、服务内容
			</h3>
			<p>1、出行服务网站服务的具体内容由王道乐途根据实际情况提供；</p>
			<p>2、王道乐途在出行服务网站上向其用户提供相关网络服务，与相关网络服务有关的设备（如个人电脑、手机、及其他与接入互联网或移动网有关的装置）及所需的费用（如为接入互联网而支付的电话费及上网费、为使用移动网而支付的手机费）均由用户自行负担。</p>
			<h3>
				三、会员帐号及密码
			</h3>
			<p>您注册会员成功后，将得到一个帐号和密码。您应妥善保管该帐号及密码，并对以该帐号进行的所有活动及事件负法律责任。因黑客行为或会员保管疏忽致使帐号、密码被他人非法使用的，王道乐途不承担任何责任。如您发现任何非法使用会员帐号或安全漏洞的情况，请立即与王道乐途联系。</p>
			<h3>
				四、用户权责
			</h3>
			<p>1、会员有权按照王道乐途规定的程序和要求使用王道乐途向用户提供的各项网络服务，如果用户对该服务有异议，可以与王道乐途联系以便得到及时解决。</p>
			<p>2、会员在申请使用出行服务网站网络服务时，必须向出行服务网站提供准确的个人资料，如个人资料有任何变动，必须及时更新。</p>
			<p>3、会员同意接受出行服务网站通过电子邮件、短信或其他方式向会员发送的预订确认信息、以及其他预订产品或服务相关的信息。会员同意接受出行服务网站通过电子邮件、短信或其他方式向会员发送的促销或其他商业信息，如会员不同意接受促销或其他商业信息，会员可以自行退订或电话联系出行服务网站进行退订。</p>
			<p>4、会员在出行服务网站的网页上发布信息或者利用出行服务网站的服务时必须符合国家的法律法规以及国际法的有关规定。</p>
			<p>5、
				对于会员通过出行服务网站消息平台（包括但不限于论坛、BBS、评论）上传到出行服务网站上可公开获取区域的任何内容，会员同意授予王道乐途在全世界范围内享有完全的、免费的、永久性的、不可撤销的、非独家的权利，以及再许可第三方的权利，以使用、复制、修改、改编、出版、翻译、据以创作衍生作品、传播、表演和展示此等内容（整体或部分），和/或将此等内容编入当前已知的或以后开发的其他任何形式的作品、媒体或技术中。</p>
			<p>6、用户承诺不会在网站的消息平台（包括但不限于论坛、BBS、评论）发布如下信息：
			<p>违反宪法或法律法规规定的；</p>
			<p>危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；</p>
			<p>损害国家荣誉和利益的，损害公共利益的；</p>
			<p>煽动民族仇恨、民族歧视，破坏民族团结的；</p>
			<p>破坏国家宗教政策，宣扬邪教和封建迷信的；</p>
			<p>散布谣言，扰乱社会秩序，破坏社会稳定的；</p>
			<p>散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；</p>
			<p>侮辱或者诽谤他人，侵害他人合法权益的；</p>
			<p>含有法律、行政法规禁止的其他内容的。</p>
			<p>7、用户单独为其发布在出行服务网站上的信息承担责任。会员若在出行服务网站散布和传播违法信息，网络会员服务的系统记录有可能作为会员违法之证据。</p>
			<p>8、用户不得利用本站的服务从事以下活动：</p>
			<p>未经允许，进入计算机信息网络或者使用计算机信息网络资源；</p>
			<p>未经允许，对计算机信息网络功能进行删除、修改或者增加；</p>
			<p>未经允许，对进入计算机信息网络中存储、处理或者传输的数据和应用程序进行删除、修改或者增加；</p>
			<p>故意制作、传播计算机病毒等破坏性程序；</p>
			<p>其他危害计算机信息网络安全的行为。</p>
			<p>9、用户不得使用任何装置、软件或例行程序等其他方式干预或试图干预出行服务网站的正常运作或正在出行服务网站上进行的任何交易、活动，或采取任何将导致不合理的庞大数据负载加诸去哪儿网络设备的行动。
			</p>
			<p>10、 用户承诺遵守本站的所有其他规定和程序。</p>
			<p>11、如果用户违反上述规定，王道乐途有权要求其改正或直接采取一切必要措施（包括但不限于更改或删除会员发布的信息、中断或终止会员使用网络的权利等），以减轻用户不当行为所造成的影响。
			
			<h3>
				五、服务条款的修改
			</h3>
			<p>王道乐途有权在必要时修改本服务条款而无需事先通知用户。王道乐途行使该修改权，无需对用户或第三方承担任何责任。用户如不同意修改，可以主动选择取消会员资格；如果用户继续使用出行服务网站服务，将被视为接受修改后的服务条款。</p>
			<p>
				六、服务内容的修改或中断
			</p>
			<p>鉴于网络服务的特殊性，王道乐途保留随时修改或中断其部分或全部网络服务的权利，并无需通知用户或为此对用户及任何第三方负责。</p>
			<h3>
				七、会员隐私保护
			</h3>
			<p>王道乐途尊重用户的隐私权，不会公开、编辑或泄露任何有关用户的个人资料以及用户在使用网络服务时存储在出行服务网站的非公开内容，但以下情况除外：</p>
			<p>事先获得用户的明确授权；遵守法律规定或出行服务网站合法服务程序；</p>
			<p>按照相关政府主管部门的合理要求；</p>
			<p>维护社会公众利益；</p>
			<p>维护王道乐途的合法权益；</p>
			<p>符合其他合法要求。</p>
			<h3>
				八、中断或终止服务
			</h3>
			<p>如发生下列任何一种情形，王道乐途有权随时中断或终止向用户提供本协议项下的网络服务，而无需对用户或任何第三方承担任何责任：</p>
			<p>用户向王道乐途提供的个人资料不真实。</p>
			<p>用户违反本协议的规则或不履行其所承担的义务。</p>
			<p>除此之外，用户可随时根据需要通知王道乐途终止向该会员提供服务，用户服务终止后，用户使用服务的权利同时终止。自用户服务终止之时起，出行服务网站不再对该用户承担任何责任。
			
			<h3>
				九、知识产权
			</h3>
			<p>1、
				王道乐途在网络服务中提供的任何文本、图片、图形、音频和视频资料均受版权、商标权以及其他相关法律法规的保护。未经王道乐途事先同意，任何人不能擅自复制、传播这些内容，或用于其他任何商业目的，所有这些资料或资料的任何部分仅可作为个人或非商业用途而保存在某台计算机内。</p>
			<p>2、王道乐途为提供网络服务而使用的任何软件（包括但不限于软件中的任何文字、图形、音频、视频资料及其辅助资料）的一切权利属于该软件的著作权人，未经该著作权人同意，任何人不得对该软件进行反向工程、反向编译或反汇编。</p>
			<p>3、如有著作权人发现用户在出行服务网站发表的内容侵犯其著作
				权，并依《互联网著作权行政保护办法》、《信息网络传播保护条例》的规定向王道乐途发出书面通知并提供相关内容的著作权权属证明的，王道乐途有权在不事先通知用户的情况下自行移除相关内容，并依法保留相关数据。</p>
			<p>4、若用户对第九条第3项指向内容依法享有发表权，可以向王道乐途及第九条第3项指向之著作权人一并发出说明被移除内容不侵犯其著作权的反通知，反通知应为书面形式，并包含如下内容：</p>
			<p>明确的身份证明、住址、联系方式；</p>
			<p>被移除内容的合法性证明； 被移除内容在互联网上的位置；反通知内容的真实性声明。</p>
			<p>符合规定的反通知发出后，王道乐途有权恢复被移除内容。
			<h3>
				十、免责声明
			</h3>
			<p>
				1、王道乐途对任何因用户不正当或非法使用服务、在网上进行交易、或用户传送信息变动而产生的直接、间接、偶然、特殊及后续的损害不承担责任。
			</p>
			<p>2、王道乐途对任何他人的威胁性的、诽谤性的、淫秽的、令人反感的或非法的内容或行为或对他人权利的侵犯（包括知识产权）不承担责任；并对任何第三方通过服务发送或在服务中包含的任何内容不承担责任。
			</p>
			<p>3、用户明确同意其使用出行服务网站服务所存在的风险以及使用出行服务网站服务产生的一切后果由其自己承担。</p>
			<p>4、对于因不可抗力或出行服务网站不能控制的原因造成的网络服务中断或其它缺陷，出行服务网站不承担任何责任，但将尽力减少因此而给用户造成的损失和影响。
			</p>
			<p>5、王道乐途不对所提供之网络服务做任何类型之担保，包括但不限于：</p>
			<p>网络服务一定能满足用户要求；</p>
			<p>网络服务不会中断；</p>
			<p>网络服务的及时性、安全性、准确性。</p>
			<p>但是王道乐途对不违反规定的特定目的担保不作限制。</p>
			<h3>
				十一、赔偿
			</h3>
			<p>用户同意保障和维护王道乐途全体成员的利益，因因用户对本服务之使用而导致王道乐途遭受任何来自第三方之纠纷、诉讼及索赔要求，用户同意向王道乐途及其关联企业、职员赔偿相应损失（包括合理的律师费），并尽力使之免受损害。</p>
			<h3>
				十二、通告
			</h3>
			<p>所有发给用户的通告都可以通过重要页面的公告、电子邮件以及常规信件的形式传送。</p>
			<h3>
				十三、法律
			</h3>
			<p>王道乐途服务条款之效力、解释、执行均适用中华人民共和国法律。如发生争议，应提交至有关辖权之人民法院。</p>
		</div>
	</div>

	<script type="text/javascript" src="<c:url value="/js/jquery-1.10.2.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.10.3.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/ba-debug.min.js"/>"></script>
	<spring:theme code="cas.javascript.file" var="casJavascriptFile" />
	<script type="text/javascript" src="<c:url value="${casJavascriptFile}" />"></script>
	
	<script type="text/javascript" src="<c:url value="/layer/layer.js"/>"></script>
	
	<script type="text/javascript" src="<c:url value="/js/jquery-validate-1.14.0.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/self.validate.expands.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/self.validate.messages.js"/>"></script>
	<link rel="stylesheet" href="<c:url value="/js/self.validate.css"/>" />

	<script type="text/javascript">
	$(document).ready(function() {
		
		//1、服务协议
		$("#agree-show").click(function(){
			layer.open({
				type : 1,
				title:false,
				skin : '', //加上边框
				shadeClose:true,
				area : [ '700px', '400px' ], //宽高
				content: $('.service-content'), //捕获的元素
			});
		});
		
		

		//1、验证
		$("#fm1").validate({
			rules:{
				mobile:{
					required: true,
					cellphone:true
				},
				password:{
					required: true,
					password:true
				},
	   			confirmpassword: {
	    			required: true,
	    			equalTo: "#password"
	  			},
	  			email:{
	  				email: true
	  			},
	  			agree:{
	  				required: true
	  			}
			},
			messages:{
				mobile:{
					required:"请输入手机号码"
				},
				password:{
					required:"请输入密码"
				},
	  			confirmpassword: {
	    			required: "请输入确认密码",
	    			equalTo: "两次输入密码不一致"
	   			},
	  			email:{
	  				
	  			},
	  			agree:{
	  				required: "请勾选"
	  			}
			},
			errorPlacement: function(error, element) {  
			    error.appendTo(element.parent());  
			}
		});
		

	});
	
	</script>
		
</body>
</html>


