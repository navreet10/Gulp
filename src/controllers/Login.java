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
		doPost(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			Subscriber user = (Subscriber) session.getAttribute("user");
			if ( user != null) {
				List<Rating> activity = LoginDao.getUserActivity(user);
				List<SortedRestaurant> restaurants = LoginDao.getAllRestaurant();
				request.setAttribute("activity", activity);		
				request.setAttribute("restaurants", restaurants);	
				request.getRequestDispatcher("home.jsp").forward(request, response);
			} else {
				String userEmail= request.getParameter("email");
				String pwd= request.getParameter("password");
				int pages = 0;
				
				if (userEmail == null) {
					List<Rating> activity = LoginDao.getUserActivity(user);
					pages = activity.size()/ 3;
					if (activity.size()% 3 ==0) {
						pages += 1;
					}
					List<SortedRestaurant> restaurants = LoginDao.getAllRestaurant();	
					session.setAttribute("user", null);
					session.setAttribute("gUrl", "");
					session.setAttribute("gBigUrl", "");
					session.setAttribute("pages", pages);
					session.setAttribute("size", 3);
					request.setAttribute("activity", activity);		
					request.setAttribute("restaurants", restaurants);	
					session.setAttribute("userName", null);
					request.getRequestDispatcher("home.jsp").forward(request, response);
				} else {
					user = LoginDao.getUserByEmail(userEmail);		
					
					if (user == null || !LoginDao.isValidUser(user,pwd)) {
						request.setAttribute("message", "Credentials are wrong!!");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					} else {
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
				}
				
			}
			
			
		} catch (NullPointerException e) {
			request.setAttribute("message", "Credentials are wrong!!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} 
	}

	

}
