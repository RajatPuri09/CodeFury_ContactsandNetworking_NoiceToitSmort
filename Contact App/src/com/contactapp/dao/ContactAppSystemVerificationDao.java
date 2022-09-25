package com.contactapp.dao;

import com.contactapp.models.SystemUser;

public interface ContactAppSystemVerificationDao 
{
	boolean verifyAdmin(String userName,String password);
	int verifyUser(String userName,String password) throws Exception;
	boolean registerUserToTheSystem(SystemUser user);
}
