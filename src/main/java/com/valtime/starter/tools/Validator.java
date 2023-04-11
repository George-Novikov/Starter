package com.valtime.starter.tools;

import com.valtime.starter.entities.User;

public class Validator {
	public static boolean checkInput(User user) {
		boolean isEmpty = true;
		if (user.getUsername().length() > 0 && user.getPassword().length() > 0 && user.getEmail().length() > 0) {
        	isEmpty = false;
        }
		return isEmpty;
	}
}
