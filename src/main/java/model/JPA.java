
package model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPA {
    private static final String PERSISTENCE_UNIT_NAME = "cybersoft_AppointmentSystem_war_1.0PU";
	private static EntityManagerFactory factory;

	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory==null) {
			factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return factory;				
	}
	
	public static void shutdown() {
		if (factory!=null) {
			factory.close();
		}		
	}
}
