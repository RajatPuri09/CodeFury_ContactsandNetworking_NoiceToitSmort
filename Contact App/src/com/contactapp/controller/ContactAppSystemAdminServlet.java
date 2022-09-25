package com.contactapp.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.contactapp.dao.impl.ContactAppSystemAdminDaoImpl;
import com.contactapp.dao.impl.ContactAppSystemVerificationDaoImpl;
import com.contactapp.models.SystemUser;

@WebServlet("/SystemAdmin")
public class ContactAppSystemAdminServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	ContactAppSystemAdminDaoImpl service;
	RequestDispatcher dispatcher;
	ContactAppSystemVerificationDaoImpl verify;
	HttpSession session;

    /**
     * Default constructor. 
     */
    public ContactAppSystemAdminServlet() {
        // TODO Auto-generated constructor stub
    	service=new ContactAppSystemAdminDaoImpl();
        verify=new ContactAppSystemVerificationDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		String adminAction=request.getParameter("adminAction");
		session=request.getSession(false);

		
		if(request.getSession(false)==null)
		{
			dispatcher=request.getRequestDispatcher("index.html");
			dispatcher.forward(request, response);
		}
		else
		{
			if(adminAction.equals("adminLogout"))
			{
				session.invalidate();
				dispatcher=request.getRequestDispatcher("index.html");
				dispatcher.forward(request, response);
			}
			
			else if(adminAction.equals("backToHome"))
			{
				dispatcher=request.getRequestDispatcher("adminHome.jsp");
				dispatcher.forward(request, response);
			}
			
			else if(adminAction.equals("showSummary"))
			{
				List<SystemUser> allUsers=(List<SystemUser>) service.showSummary();
				
				request.setAttribute("summary", allUsers);
				
				dispatcher=request.getRequestDispatcher("showSummary.jsp");
				dispatcher.forward(request, response);
			}
			
			else if(adminAction.equals("disableUser"))
			{
				Collection<SystemUser> userList=service.viewUsersToDisable();
				
				ArrayList<String> users=null;
				
				for(SystemUser use: userList)
				{
					users.add(use.getUsername());
				}
				
				request.setAttribute("disableList", users);
				
				dispatcher=request.getRequestDispatcher("disableUser.jsp");
				dispatcher.forward(request, response);
			}
			
			else if(adminAction.equals("deleteUser"))
			{
				Collection<SystemUser> userList=service.viewUsersToDelete();
				
				ArrayList<String> users=null;
				
				for(SystemUser use: userList)
				{
					users.add(use.getUsername());
				}
				
				request.setAttribute("deleteList", users);
				
				dispatcher=request.getRequestDispatcher("deleteUser.jsp");
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

		String adminAction=request.getParameter("adminAction");
		
		if(request.getSession(false)==null)
		{
			dispatcher=request.getRequestDispatcher("index.html");
			dispatcher.forward(request, response);
		}
		else
		{
				
			if(adminAction.equals("deleteUsers"))
			{
				String[] userList=request.getParameterValues("deleteList");
				
				int[] userIdList=null;
				
				for(int i=0;i<userList.length;i++)
				{
					userIdList[i]=Integer.parseInt(userList[i]);
				}
				
				
				for(int userId: userIdList)
				{
					service.deleteSystemUser(userId);
				}
				
				request.setAttribute("status", "operation successful");
				dispatcher=request.getRequestDispatcher("adminResult.jsp");
				dispatcher.forward(request, response);			
			}
			
			if(adminAction.equals("disableUsers"))
			{
				String[] userList=request.getParameterValues("disableList");
				
				int[] userIdList=null;
				
				for(int i=0;i<userList.length;i++)
				{
					userIdList[i]=Integer.parseInt(userList[i]);
				}
				
				
				for(int userId: userIdList)
				{
					service.disableSystemUser(userId);
				}
				
				request.setAttribute("status", "operation successful");
				dispatcher=request.getRequestDispatcher("adminResult.jsp");
				dispatcher.forward(request, response);
			}
			
			
		}
		
		
		
	}

}
