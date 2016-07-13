package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.RegisterDao;
import model.Subscriber;
import util.DBUtil;
import util.MD5Util;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Registration() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String userEmail= request.getParameter("email");
			String pwd= request.getParameter("password");
			String userName= request.getParameter("name");
			String zip= request.getParameter("zip");
			String hash = MD5Util.md5Hex(userEmail);
			String url = DBUtil.getGrUrl(hash);

			
			Subscriber user = new Subscriber();
			user.setEmail(userEmail);
			user.setName(userName);
			user.setPassword(pwd);
			user.setUrl(url);
			user.setZip(zip);
			
			RegisterDao.addUser(user);			
			
			HttpSession session = request.getSession();
			session.setAttribute("gUrl", user.getUrl()+20);
			session.setAttribute("gBigUrl", user.getUrl()+200);
			session.setAttribute("user", user);
			request.setAttribute("userName", user.getName());
			request.getRequestDispatcher("home.jsp").forward(request, response);
			
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			request.setAttribute("message", "Something went wrong. Please try again.");
			request.getRequestDispatcher("registration.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Something went wrong. Please try again.");
			request.getRequestDispatcher("registration.jsp").forward(request, response);
		} 
	}

}
