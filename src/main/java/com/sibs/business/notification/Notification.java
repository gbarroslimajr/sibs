package com.sibs.business.notification;


import com.sibs.domain.model.User;

public interface Notification {

	void notify(User user, String message);
	
}
