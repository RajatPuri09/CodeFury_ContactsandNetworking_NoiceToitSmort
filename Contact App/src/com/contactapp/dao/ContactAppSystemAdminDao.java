package com.contactapp.dao;

import java.util.Collection;

import com.contactapp.models.SystemUser;

public interface ContactAppSystemAdminDao 
{
	boolean loginSystemAdmin(String adminUsername, String adminPassword);
	boolean disableSystemUser(int id);
	boolean deleteSystemUser(int id);
	Collection<SystemUser> showSummary();
	Collection<SystemUser> viewUsersToDisable();
	Collection<SystemUser> viewUsersToDelete();
}
