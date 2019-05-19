package com.cartell.subscription.schedules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserTasks {
	private static Logger logger = LoggerFactory.getLogger(UserTasks.class.getName());	
	
	@Scheduled(fixedRate=60*1000, initialDelay=60*1000)
	public void process() {
		logger.info("processEmails schedule running..............................");
	}
	
}
