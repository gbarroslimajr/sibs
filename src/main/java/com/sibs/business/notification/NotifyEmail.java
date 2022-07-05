package com.sibs.business.notification;


import com.sibs.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotifyEmail implements Notification {

	@Override
	public void notify(User user, String message) {
		StringBuilder builder = new StringBuilder();
		builder.append("To: ").append(user.getEmail()).append("\n");
		builder.append("Subject: ").append("Your Order has been completed").append("\n");
		builder.append("Dear ").append(user.getName()).append(message).append("\n");
		builder.append("Email sent! ").append("\n");
		log.info(builder.toString());
	}
}
