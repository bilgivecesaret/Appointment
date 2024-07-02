package dao;

import entity.Doctor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
public class DoctorDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
    EntityManager em = emf.createEntityManager();

    // save doctor
    public void save(Doctor doctor) {
        em.getTransaction().begin();
        em.persist(doctor);
        em.getTransaction().commit();
        //JPAUtil.shutdown();
    }

    // edit doctor
    public void edit(Doctor doctor) {
        em.getTransaction().begin();
        em.merge(doctor);
        em.getTransaction().commit();
        /// JPAUtil.shutdown();
    }

    // find doctor
    public Doctor find(Integer id) {
        Doctor c = new Doctor();
        c = em.find(Doctor.class, id);
        // JPAUtil.shutdown();
        return c;
    }

    // delete doctor
    public void delete(Integer id) {
        Doctor c = new Doctor();
        c = em.find(Doctor.class, id);
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
    }

    // get all doctors
    public List<Doctor> findAllDoctors() {
        List<Doctor> listaDoctors = new ArrayList<>();
        Query q = em.createQuery("SELECT c FROM Doctor c");
        listaDoctors = q.getResultList();
        return listaDoctors;
    }
}
