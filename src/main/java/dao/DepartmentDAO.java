
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Department;
import model.JPA;

public class DepartmentDAO {
    EntityManager em = JPA.getEntityManagerFactory().createEntityManager();

    // save department
    public void save(Department department) {
        em.getTransaction().begin();
        em.persist(department);
        em.getTransaction().commit();
        //JPAUtil.shutdown();
    }

    // edit department
    public void edit(Department department) {
        em.getTransaction().begin();
        em.merge(department);
        em.getTransaction().commit();
        /// JPAUtil.shutdown();
    }

    // find department
    public Department find(Long id) {
        Department c = new Department();
        c = em.find(Department.class, id);
        // JPAUtil.shutdown();
        return c;
    }

    // delete department
    public void delete(Long id) {
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
}
