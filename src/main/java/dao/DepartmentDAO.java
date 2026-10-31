
package dao;

import entity.Department;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DepartmentDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
    EntityManager em = emf.createEntityManager();

    // save department
    public void save(Department department) {
        em.getTransaction().begin();
        em.persist(department);
        em.getTransaction().commit();
    }

    // edit department
    public void edit(Department department) {
        em.getTransaction().begin();
        em.merge(department);
        em.getTransaction().commit();
    }

    // find department
    public Department find(Integer id) {
        Department c = new Department();
        c = em.find(Department.class, id);
        return c;
    }

    // delete department
    public void delete(Integer id) {
        Department c = new Department();
        c = em.find(Department.class, id);
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
    }

    // get all departments
    public List<Department> findAllDepartments() {
        List<Department> listaDepartments = new ArrayList<>();
        Query q = em.createQuery("SELECT c FROM Department c");
        listaDepartments = q.getResultList();
        return listaDepartments;
    }
    
    public void close(){
        em.close();
        emf.close();
    }
}
