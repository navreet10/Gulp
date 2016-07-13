package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import beans.SortedRestaurant;
import util.DBUtil;
import model.Rating;
import model.Restaurant;
import model.Subscriber;

public class LoginDao {

	public static Subscriber getUserByEmail(String userEmail) {
		EntityManager em = DBUtil.getEmfFactory().createEntityManager();
		TypedQuery<Subscriber> query= em.createQuery("SELECT b FROM Subscriber b where b.email = :email", Subscriber.class);
		query.setParameter("email", userEmail);
		Subscriber user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return user;
		
	}

	public static boolean isValidUser(Subscriber user, String pwd) {		
		return user.getPassword().equals(pwd);
	}

	public static List<Rating> getUserActivity(Subscriber user) {
		EntityManager em = DBUtil.getEmfFactory().createEntityManager();
		TypedQuery<Rating> query= em.createQuery("SELECT b FROM Rating b where b.subscriber.id = :id", Rating.class);
		query.setParameter("id", user.getId());
		List<Rating>  activity = null;
		try {
			activity = query.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return activity;
		
	}
	

	public static List<SortedRestaurant> getAllRestaurant() {
		
		List <SortedRestaurant> restaurants = new ArrayList<SortedRestaurant>();
		EntityManager em = DBUtil.getEmfFactory().createEntityManager();
        String qString = "select AVG (s.rating) as average, count(s.id),s.restaurant.id  "
        		+ "from Rating s "
        		+ "group by s.restaurant.id"
        		+ " order by average";
        int k =0;
        try{
            Query query = em.createQuery(qString,Rating.class);
            List<Object[]> results =  query.getResultList();
            
           for (Object[] o: results) {
        	   SortedRestaurant restaurant  = new SortedRestaurant();
        	   restaurant.setRating((float) o[0]);
        	   restaurant.setNumRating((int) o[1]);
        	   TypedQuery<Restaurant> q= em.createQuery("SELECT b FROM Restaurant b where b.id= :id", Restaurant.class);		
       			q.setParameter("id", (int) o[2]);
        	   Restaurant  res =  q.getSingleResult();
        	   restaurant.setRestaurant(res);
        	   restaurants.add(k,restaurant);
        	   k++;
           }
           TypedQuery<Restaurant> q1= em.createQuery("SELECT b FROM Restaurant b", Restaurant.class);
           List < Restaurant> rests = q1.getResultList();
           for (Restaurant r: rests) {
        	   boolean x = false;
        	   for (SortedRestaurant s: restaurants) {
        		   if (s.getRestaurant().getId() == r.getId()) {
        			   x=true;
        			   break;
        		   }
        	   }
        	   if (!x) {
        		   SortedRestaurant restaurant  = new SortedRestaurant();
        		   restaurant.setRating(0);
            	   restaurant.setNumRating(0);
            	   restaurant.setRestaurant(r);
        		   restaurants.add(k,restaurant);
            	   k++;
        	   }
           }
        }catch (Exception e){
        	e.printStackTrace();
        	 return null;
        }
        finally{
                em.close();
            }
        return restaurants;
		
	}

	public static Restaurant getRestaurant(String id) {
		EntityManager em = DBUtil.getEmfFactory().createEntityManager();
		TypedQuery<Restaurant> query= em.createQuery("SELECT b FROM Restaurant b where b.id = :id", Restaurant.class);
		query.setParameter("id", Long.parseLong(id));
		Restaurant  rest = null;
		try {
			rest = query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
		return rest;
	}

	public static List<Rating> getRestRating(String id) {
		EntityManager em = DBUtil.getEmfFactory().createEntityManager();
		TypedQuery<Rating> query= em.createQuery("SELECT b FROM Rating b where b.restaurant.id = :id", Rating.class);
		query.setParameter("id", Long.parseLong(id));
		List<Rating>  activity = null;
		try {
			activity = query.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return activity;
	}

	public static void inserReview(Rating rat) {
		EntityManager em = DBUtil.getEmfFactory().createEntityManager();
		try {
		em.getTransaction().begin();	
		em.persist(rat);
		em.getTransaction().commit();	
			
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
	}

}
