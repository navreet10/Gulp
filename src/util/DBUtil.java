package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Gulp");
	
	public static EntityManagerFactory getEmfFactory() {
		return emf;
	}

	public static String getGrUrl(String hash) {
		return "https://www.gravatar.com/avatar/" + hash + "?s=";
	}

}
