package com.travel.cas.site.retakeP.action;

import javax.validation.constraints.NotNull;

import org.jasig.cas.util.UniqueTicketIdGenerator;
import org.jasig.cas.web.support.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.execution.RequestContext;

public class GenerateRetakePTicketAction {

    private static final String PREFIX_INPUT = "RPTI";
    private static final String PREFIX_VERIFY = "RPTV";
    private static final String PREFIX_RESET = "RPTR";
    
    private static final String PREFIX_SEND = "RPTS";

    /** Logger instance. */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @NotNull
    private UniqueTicketIdGenerator ticketIdGenerator;

    
    //验证用户存在票根
    public final String generateForInput(final RequestContext context) {
        final String retakePTicketInput = this.ticketIdGenerator.getNewTicketId(PREFIX_INPUT);
        logger.debug("Generated retakePTicketInput ticket {}", retakePTicketInput);
        WebUtils.putRetakePTicketInput(context, retakePTicketInput);
        return "generated";
    }
    
  //验证用户合法票根
    public final String generateForVerify(final RequestContext context) {
    	 final String retakePTicketVerify = this.ticketIdGenerator.getNewTicketId(PREFIX_VERIFY);
         logger.debug("Generated retakePTicketVerify ticket {}", retakePTicketVerify);
         WebUtils.putRetakePTicketVerify(context, retakePTicketVerify);
         return "generated";
    }
    
    
  //重置密码票根
    public final String generateForReset(final RequestContext context) {
    	 final String retakePTicketReset = this.ticketIdGenerator.getNewTicketId(PREFIX_RESET);
         logger.debug("Generated retakePTicketReset ticket {}", retakePTicketReset);
         WebUtils.putRetakePTicketReset(context, retakePTicketReset);
         return "generated";
    }
    
    
     //发送短信码票根
    public final String generateForSend(final RequestContext context) {
   	 final String retakePTicketSend = this.ticketIdGenerator.getNewTicketId(PREFIX_SEND);
        logger.debug("Generated retakePTicketYzm ticket {}", retakePTicketSend);
        WebUtils.putRetakePTicketSend(context, retakePTicketSend);
        return "generated";
   }

    public void setTicketIdGenerator(final UniqueTicketIdGenerator generator) {
        this.ticketIdGenerator = generator;
    }
}
