package com.contactapp.controller;

import java.util.Date;

import com.contactapp.dao.impl.ContactAppSystemVerificationDaoImpl;
import com.contactapp.models.SystemUser;

@WebServlet("/SystemNavigation")
public class ContactAppSystemFlowServlet {
	private static final long serialVersionUID = 1L;
	ContactAppSystemVerificationDaoImpl verify;
	HttpSession session;
	
	RequestDispatcher dispatcher=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactAppSystemFlowServlet() {
        super();
        // TODO Auto-generated constructor stub
    	verify=new 	ContactAppSystemVerificationDaoImpl();
;

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		String input=request.getParameter("submit");
			
		if(input.equals("adminLogin"))
		{
			dispatcher=request.getRequestDispatcher("adminLogin.jsp");
			dispatcher.forward(request,response);
		}
		else if(input.equals("userSignup"))
		{
			dispatcher=request.getRequestDispatcher("userSignUp.jsp");
			dispatcher.forward(request, response);
		}
		else if(input.equals("userLogin"))
		{
			dispatcher=request.getRequestDispatcher("userLogin.jsp");
			dispatcher.forward(request, response);

		} else if(input.equals("userLogout"))
		{
			dispatcher=request.getRequestDispatcher("index.html");
			dispatcher.forward(request, response);

		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String login=request.getParameter("submit");
		
		if(login.equals("userLogin"))
		{
			String userName=request.getParameter("userName");
			String password=request.getParameter("password");
			System.out.println(userName);
			int flag=0;
			try {
				flag = verify.verifyUser(userName, password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(flag!=0)
			{
				session=request.getSession(true);
				session.setAttribute("sessionId", flag);
				
				session.setAttribute("userName", userName);
				dispatcher=request.getRequestDispatcher("userHome.jsp");
				dispatcher.forward(request, response);
			}
			else
			{
				dispatcher=request.getRequestDispatcher("index.html");
				dispatcher.forward(request, response);
			}
		}
		else if(login.equals("userSignUp"))
		{
			String userName=request.getParameter("userName");
			String fullName=request.getParameter("fullName");
			String email=request.getParameter("email");
			String gender=request.getParameter("gender");
			long phoneNumber=Long.parseLong(request.getParameter("phoneNumber"));
			Date dateOfBirth=request.getParameter("dateOfBirth");
			System.out.println(dateOfBirth);
			String address=request.getParameter("address");
			String city=request.getParameter("city");
			String state=request.getParameter("state"); 
			String country=request.getParameter("country");
			String company=request.getParameter("company");
			byte[] image=request.getParameter("image").getBytes();


			String password=request.getParameter("password");
			
			System.out.println(userName);
			verify.registerUserToTheSystem(new SystemUser( fullName, 
					 email,  phoneNumber,  gender, 
					 dateOfBirth,  address, city, 
					 state,  country,  company, image, userName,password));
			
			dispatcher=request.getRequestDispatcher("userLogin.jsp");
			dispatcher.forward(request, response);
		}
		
		if(login.equals("adminLogin"))
		{
			String adminUserName=request.getParameter("adminUserName");
			String password=request.getParameter("password");
			System.out.println(adminUserName);
			System.out.println(password);
			if(verify.verifyAdmin(adminUserName, password))
			{
				session=request.getSession(true);
				session.setAttribute("sessionId", adminUserName);
						
				session.setAttribute("userName", adminUserName);
				dispatcher=request.getRequestDispatcher("adminHome.jsp");
				dispatcher.forward(request, response);
			}
			else
			{
				dispatcher=request.getRequestDispatcher("index.html");
				dispatcher.forward(request, response);
			}
			
		}
				
		
		
		
		
		
	}

}
