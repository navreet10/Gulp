package controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
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
 * Servlet implementation class SubmitReview
 */
@WebServlet("/SubmitReview")
public class SubmitReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Subscriber user = (Subscriber) session.getAttribute("user");
		String restid = (long) session.getAttribute("restid")+ "";
		Restaurant rest = LoginDao.getRestaurant(restid);
		try {
			String rating = request.getParameter("rating");
			String review = request.getParameter("review");
			
			
			Rating rat = new Rating();
			rat.setDaterate(new Date());
			rat.setRating(new BigDecimal(rating));
			rat.setRestaurant(rest);
			rat.setReview(review);
			rat.setSubscriber(user);
			LoginDao.inserReview(rat);
			
			List<Rating> ratings = LoginDao.getRestRating(restid);
			session.setAttribute("img", "rest"+restid);
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

		} catch (NullPointerException e) {
			request.setAttribute("message", "Something went wrong!!");
			List<Rating> ratings = LoginDao.getRestRating(restid);
			session.setAttribute("img", "rest"+restid);
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
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Something went wrong!!");
			List<Rating> ratings = LoginDao.getRestRating(restid);
			session.setAttribute("img", "rest"+restid);
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
	}

}
