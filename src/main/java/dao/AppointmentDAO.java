
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Appointment;
import model.JPA;

public class AppointmentDAO {
    EntityManager em = JPA.getEntityManagerFactory().createEntityManager();

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
    public Appointment find(Long id) {
        Appointment c = new Appointment();
        c = em.find(Appointment.class, id);
        // JPAUtil.shutdown();
        return c;
    }

    // delete appointment
    public void delete(Long id) {
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
