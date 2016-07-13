package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import model.Rating;
import model.Restaurant;
import model.Subscriber;

/**
 * Servlet implementation class ViewPost
 */
@WebServlet("/ViewRest")
public class ViewRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRest() {
        super();
        // TODO Auto-generated constructor stub
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
			String id= request.getParameter("idRest");
			Restaurant rest = LoginDao.getRestaurant(id);		
			
			if (rest == null ) {
				request.setAttribute("message", "Credentials are wrong!!");
				request.getRequestDispatcher("rateView.jsp").forward(request, response);
			} else {
				
				List<Rating> ratings = LoginDao.getRestRating(id);
				HttpSession session = request.getSession();
				Subscriber user = (Subscriber) session.getAttribute("user");
				session.setAttribute("img", "rest"+id);
				session.setAttribute("address", rest.getAddress());
				session.setAttribute("zip", rest.getZip());
				session.setAttribute("description", rest.getDescription());
				session.setAttribute("restName", rest.getName());
				session.setAttribute("restid", rest.getId());
				session.setAttribute("user", user);
				session.setAttribute("gUrl", user.getUrl()+20);
				session.setAttribute("gBigUrl", user.getUrl()+200);				
				request.setAttribute("restDetails", ratings);		
				session.setAttribute("userName", user.getName());
				request.getRequestDispatcher("rateView.jsp").forward(request, response);
			}
			
		} catch (NullPointerException e) {
			request.setAttribute("message", "Credentials are wrong!!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
