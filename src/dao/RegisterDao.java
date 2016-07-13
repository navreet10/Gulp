package dao;



import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import util.DBUtil;
import model.Subscriber;

public class RegisterDao {

	public static void addUser(Subscriber user) {
		
		EntityManager em = DBUtil.getEmfFactory().createEntityManager();
		try {
		em.getTransaction().begin();	
		em.persist(user);
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
