package com.contactapp.controller;

import java.util.Collection;
import java.util.Date;

import com.contactapp.dao.impl.ContactAppSystemUserDaoImpl;
import com.contactapp.dao.impl.ContactAppSystemVerificationDaoImpl;
import com.contactapp.models.ContactPerson;
import com.contactapp.models.SystemUser;

@WebServlet("/SystemUser")
public class ContactAppSystemUserServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher=null;
	ContactAppSystemUserDaoImpl service;
	ContactAppSystemVerificationDaoImpl verify;
	HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactAppSystemUserServlet() {
        super();
        // TODO Auto-generated constructor stub
        verify=new ContactAppSystemVerificationDaoImpl();
        service=new ContactAppSystemUserDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String userAction=request.getParameter("userAction");
		session=request.getSession(false);

		
		if(request.getSession(false)==null)
		{
			dispatcher=request.getRequestDispatcher("index.html");
			dispatcher.forward(request, response);
		}
		else
		{
			if(userAction.equals("addNewContact"))
			{
				dispatcher=request.getRequestDispatcher("addNewContact.jsp");
				dispatcher.forward(request, response);
			}
			
			else if(userAction.equals("viewFriendRequests"))
			{
				Collection<SystemUser> friendRequests=service.viewFriendRequests((Integer)session.getAttribute("sessionId"));
				
				request.setAttribute("friendRequests", friendRequests);
				
				dispatcher=request.getRequestDispatcher("viewFriendRequest.jsp");
				dispatcher.forward(request, response);
			}
			
			else if(userAction.equals("searchUser"))
			{
				dispatcher=request.getRequestDispatcher("searchUser.jsp");
				dispatcher.forward(request, response);
			}
			
			else if(userAction.equals("viewBlockedList"))
			{
				
				Collection<SystemUser> blockedUsers=service.viewBlockedUsersList((Integer)session.getAttribute("sessionId"));
				
				request.setAttribute("blockedList", blockedUsers);
				
				dispatcher=request.getRequestDispatcher("viewBlockedList.jsp");
				dispatcher.forward(request, response);
			}
			
			else if(userAction.equals("viewFriendList"))
			{
				Collection<SystemUser> friendList=service.viewFriendsList((Integer)session.getAttribute("sessionId"));
				
				request.setAttribute("friendList", friendList);
				dispatcher=request.getRequestDispatcher("viewFriendList.jsp");
				dispatcher.forward(request, response);
			}
			
			else if(userAction.equals("viewContactList"))
			{
				Collection<ContactPerson> contactList=service.viewContactsList((Integer)session.getAttribute("sessionId"));
				
				request.setAttribute("contactList",contactList);
				dispatcher=request.getRequestDispatcher("viewContactList.jsp");
				dispatcher.forward(request, response);
			}
			
			else if(userAction.equals("backToHome"))
			{
				dispatcher=request.getRequestDispatcher("userHome.jsp");
				dispatcher.forward(request, response);
			}
			
			else if(userAction.equals("userLogout"))
			{
				session.invalidate();
				dispatcher=request.getRequestDispatcher("index.html");
				dispatcher.forward(request, response);
			}
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		session=request.getSession(false);
		
		String userAction=request.getParameter("userAction");
		
		if(request.getSession(false)==null)
		{
			dispatcher=request.getRequestDispatcher("index.html");
			dispatcher.forward(request, response);
		}
		else
		{	
			System.out.println(userAction);
			if(userAction.equals("addNewContact"))
			{
				String fullName=request.getParameter("fullName");
				String email=request.getParameter("email");
				long phoneNumber=Long.parseLong(request.getParameter("phoneNumber"));
				String gender=request.getParameter("gender");
				Date dateOfBirth=request.getParameter("dateOfBirth");
				System.out.println(dateOfBirth);
				String address=request.getParameter("address");
				String city=request.getParameter("city");
				String state=request.getParameter("state");
				String country=request.getParameter("country");
				String company=request.getParameter("company");
				byte[] image=request.getParameter("image").getBytes();
				
				service.addContactToTheList((Integer)session.getAttribute("sessionId"),
						new ContactPerson(fullName, email, phoneNumber, gender, dateOfBirth,
								address, city, state, country,
								company, image));
				
				request.setAttribute("result", "Operation successful");
				dispatcher=request.getRequestDispatcher("userResult.jsp");
				dispatcher.forward(request, response);
				
			}
			if(userAction.equals("blockUser"))
			{
				int blockId=Integer.parseInt(request.getParameter("userId"));
				
				service.blockUser((Integer)session.getAttribute("userId"),blockId);
				
				request.setAttribute("result","Operation successful");
				dispatcher=request.getRequestDispatcher("userResult.jsp");
				dispatcher.forward(request, response);
				
			}
			if(userAction.equals("unBlockUser"))
			{
				int blockId=Integer.parseInt(request.getParameter("userId"));
				
				service.unblockUser((Integer)session.getAttribute("userId"),blockId);
				
				request.setAttribute("result","Operation successful");
				dispatcher=request.getRequestDispatcher("userResult.jsp");
				dispatcher.forward(request, response);
				
			}
			if(userAction.equals("sendRequest"))
			{
				int friendId=Integer.parseInt(request.getParameter("userId"));
				
				service.sendRequestToFriend((Integer)session.getAttribute("sessionId"),friendId);
				request.setAttribute("result","Operation successful");
				dispatcher=request.getRequestDispatcher("userResult.jsp");
				dispatcher.forward(request, response);
			}
			if(userAction.equals("declineRequest"))
			{
				int friendId=Integer.parseInt(request.getParameter("userId"));
				
				service.declineRequest((Integer)session.getAttribute("sessionId"),friendId);
				request.setAttribute("result","Operation successful");
				dispatcher=request.getRequestDispatcher("userResult.jsp");
				dispatcher.forward(request, response);
			}
			if(userAction.equals("addFriend"))
			{
				int friendId=Integer.parseInt(request.getParameter("userId"));
				
				service.addFriendToTheList((Integer)session.getAttribute("sessionId"),friendId);
				
				request.setAttribute("result","Operation successful");
				dispatcher=request.getRequestDispatcher("userResult.jsp");
				dispatcher.forward(request, response);
				
			}
			if(userAction.equals("searchUser"))
			{
				String name=request.getParameter("userName");
				
				SystemUser user=(SystemUser) service.searchUser(name);
				

				request.setAttribute("userInfo", user);
				dispatcher=request.getRequestDispatcher("userInfo.jsp");
				dispatcher.forward(request, response);
			}
			
			
		}
		
		
		
		
		
		
		
	}

}
