
package dao;

import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class AppointmentDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
    EntityManager em = emf.createEntityManager();
    
    // save appointment
    public void save(Appointment appointment) {
        em.getTransaction().begin();
        em.persist(appointment);
        em.getTransaction().commit();        
    }

    // edit appointment
    public void edit(Appointment appointment) {
        em.getTransaction().begin();
        em.merge(appointment);
        em.getTransaction().commit();
    }

    // find appointment
    public Appointment findAppoinment(Integer id) {
        Appointment c = new Appointment();
        c = em.find(Appointment.class, id);
        return c;
    }
    
    // find Doctor
    public Doctor findDoctor(Integer id) {
        Doctor c = new Doctor();
        c = em.find(Doctor.class, id);
        return c;
    }
    
    // find Patient
    public Patient findPatient(Integer id) {
        Patient c = new Patient();
        c = em.find(Patient.class, id);
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
        List<Appointment> listAppointments = new ArrayList<>();
        Query q = em.createQuery("SELECT c FROM Appointment c");
        listAppointments = q.getResultList();
        return listAppointments;
    }
    
    // Find all appointments for a specific patient
    public List<Appointment> findAllAppointmentsByPatient(Integer patientId) {
        TypedQuery<Appointment> query = em.createQuery(
                "SELECT a FROM Appointment a WHERE a.patientId = :patientId", Appointment.class);
        query.setParameter("patientId", patientId);
        return query.getResultList();
    }
    
    public void close(){
        em.close();
        emf.close();
    }
}
