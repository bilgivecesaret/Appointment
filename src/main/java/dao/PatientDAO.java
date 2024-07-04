package dao;

import entity.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PatientDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
    EntityManager em = emf.createEntityManager();

    // save patient
    public void save(Patient patient) {
        em.getTransaction().begin();
        em.persist(patient);
        em.getTransaction().commit();
    }

    // edit patient
    public void edit(Patient patient) {
        em.getTransaction().begin();
        em.merge(patient);
        em.getTransaction().commit();
    }

    // find patient
    public Patient find(Integer id) {
        Patient c = new Patient();
        c = em.find(Patient.class, id);
        return c;
    }

    // delete patient
    public void delete(Integer id) {
        Patient c = new Patient();
        c = em.find(Patient.class, id);
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
    }

    // get all patients
    public List<Patient> findAllPatients() {
        List<Patient> listaPatients = new ArrayList<>();
        Query q = em.createQuery("SELECT c FROM Patient c");
        listaPatients = q.getResultList();
        return listaPatients;
    }   

    public void close() {
        em.close();
        emf.close();
    }
}
