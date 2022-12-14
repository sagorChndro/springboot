package com.sagor.secheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class NotificationSecheduler {
	private static final Logger logger = LogManager.getLogger(NotificationSecheduler.class.getName());

	@Scheduled(fixedRate = 2000)
	public void testScheduling() {
		logger.info("Scheduling called");
	}
}
