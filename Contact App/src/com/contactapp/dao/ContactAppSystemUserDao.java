package com.contactapp.dao;

import java.util.Collection;
import java.util.Set;

import com.contactapp.models.ContactPerson;
import com.contactapp.models.SystemUser;

public interface ContactAppSystemUserDao 
{
	boolean addContactToTheList(int id, ContactPerson contact);
	boolean editContact(int id, ContactPerson contact);
	boolean addFriendToTheList(int userId1, int userId2);
	
	Collection<ContactPerson> viewContactsList(int id);
	Collection<SystemUser> viewFriendsList(int id);
	Collection<SystemUser> viewBlockedUsersList(int userId);
	
	SystemUser searchUser(String nameEmail);
	
	boolean blockUser(int userId1, int userId2);
	boolean unblockUser(int userId1, int userId2);
	
	boolean sendRequestToFriend(int userId1, int userId2);
	boolean declineRequest(int userId1, int userId2);
	Set<SystemUser> viewFriendRequests(int userId);
}
