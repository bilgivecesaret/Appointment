
package dao;

import entity.Appointment;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AppointmentDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
    EntityManager em = emf.createEntityManager();
    
    // save appointment
    public void save(Appointment appointment) {
        em.getTransaction().begin();
        em.persist(appointment);
        em.getTransaction().commit();
        //JPAUtil.shutdown();
    }

    // edit appointment
    public void edit(Appointment appointment) {
        em.getTransaction().begin();
        em.merge(appointment);
        em.getTransaction().commit();
        /// JPAUtil.shutdown();
    }

    // find appointment
    public Appointment find(Integer id) {
        Appointment c = new Appointment();
        c = em.find(Appointment.class, id);
        // JPAUtil.shutdown();
        return c;
    }

    // delete appointment
    public void delete(Integer id) {
        Appointment c = new Appointment();
        c = em.find(Appointment.class, id);
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
    }

    // get all appointments
    public List<Appointment> findAllAppointments() {
        List<Appointment> listaAppointments = new ArrayList<>();
        Query q = em.createQuery("SELECT c FROM Appointment c");
        listaAppointments = q.getResultList();
        return listaAppointments;
    }
}
