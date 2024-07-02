
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Patient;
import model.JPA;

public class PatientDAO {
    EntityManager em = JPA.getEntityManagerFactory().createEntityManager();

    // save patient
    public void save(Patient patient) {
        em.getTransaction().begin();
        em.persist(patient);
        em.getTransaction().commit();
        //JPAUtil.shutdown();
    }

    // edit patient
    public void edit(Patient patient) {
        em.getTransaction().begin();
        em.merge(patient);
        em.getTransaction().commit();
        /// JPAUtil.shutdown();
    }

    // find patient
    public Patient find(Long id) {
        Patient c = new Patient();
        c = em.find(Patient.class, id);
        // JPAUtil.shutdown();
        return c;
    }

    // delete patient
    public void delete(Long id) {
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
}
