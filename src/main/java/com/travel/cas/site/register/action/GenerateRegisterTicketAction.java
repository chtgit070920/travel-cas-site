package com.travel.cas.site.register.action;

import javax.validation.constraints.NotNull;

import org.jasig.cas.util.UniqueTicketIdGenerator;
import org.jasig.cas.web.support.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.execution.RequestContext;

public class GenerateRegisterTicketAction {

	/** 3.5.1 - Register tickets SHOULD begin with characters "RT-". */
    private static final String PREFIX = "RT";

    /** Logger instance. */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @NotNull
    private UniqueTicketIdGenerator ticketIdGenerator;

    public final String generate(final RequestContext context) {
        final String loginTicket = this.ticketIdGenerator.getNewTicketId(PREFIX);
        logger.debug("Generated register ticket {}", loginTicket);
        WebUtils.putRegisterTicket(context, loginTicket);
        return "generated";
    }

    public void setTicketIdGenerator(final UniqueTicketIdGenerator generator) {
        this.ticketIdGenerator = generator;
    }
}
