package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.SortedRestaurant;
import dao.LoginDao;
import model.Rating;
import model.Subscriber;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Home")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() {
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String userEmail= request.getParameter("email");
			String pwd= request.getParameter("password");
			Subscriber user = LoginDao.getUserByEmail(userEmail);		
			
			if (user == null || !LoginDao.isValidUser(user,pwd)) {
				request.setAttribute("message", "Credentials are wrong!!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				List<Rating> activity = LoginDao.getUserActivity(user);
				List<SortedRestaurant> restaurants = LoginDao.getAllRestaurant();	
				session.setAttribute("user", user);
				session.setAttribute("gUrl", user.getUrl()+20);
				session.setAttribute("gBigUrl", user.getUrl()+200);				
				request.setAttribute("activity", activity);		
				request.setAttribute("restaurants", restaurants);	
				session.setAttribute("userName", user.getName());
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
			
		} catch (NullPointerException e) {
			request.setAttribute("message", "Credentials are wrong!!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	

}
