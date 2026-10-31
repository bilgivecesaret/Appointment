package config;

import entity.Appointment;
import entity.Department;
import entity.Doctor;
import entity.Patient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppDataInitializer implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initializeSchema();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();

        try {
            Long departmentCount = (Long) em.createQuery("SELECT COUNT(d) FROM Department d").getSingleResult();
            if (departmentCount > 0) {
                return;
            }

            em.getTransaction().begin();

            Department internalMedicine = new Department();
            internalMedicine.setName("Dahiliye");
            em.persist(internalMedicine);

            Department cardiology = new Department();
            cardiology.setName("Kardiyoloji");
            em.persist(cardiology);

            Department ent = new Department();
            ent.setName("Kulak Burun Boğaz");
            em.persist(ent);

            Patient patient = new Patient();
            patient.setTc("12345678901");
            patient.setFullname("Ayşe Yılmaz");
            patient.setGender("Kadın");
            patient.setPhone("+905551234567");
            patient.setEmail("ayse@example.com");
            patient.setPassword("123456");
            em.persist(patient);

            Doctor doctor1 = new Doctor();
            doctor1.setFullname("Dr. Selin Demir");
            doctor1.setUsername("selin");
            doctor1.setPassword("123");
            doctor1.setDepartmentId(internalMedicine);
            em.persist(doctor1);

            Doctor doctor2 = new Doctor();
            doctor2.setFullname("Dr. Mehmet Yıldız");
            doctor2.setUsername("mehmet");
            doctor2.setPassword("123");
            doctor2.setDepartmentId(cardiology);
            em.persist(doctor2);

            Doctor doctor3 = new Doctor();
            doctor3.setFullname("Dr. Ece Karaca");
            doctor3.setUsername("ece");
            doctor3.setPassword("123");
            doctor3.setDepartmentId(ent);
            em.persist(doctor3);

            Appointment appointment = new Appointment();
            appointment.setAppointDate(createDate(2026, Calendar.NOVEMBER, 5));
            appointment.setAppointmentTime(createTime(9, 30));
            appointment.setDoctorId(doctor1);
            appointment.setPatientId(patient);
            em.persist(appointment);

            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
            emf.close();
        }
    }

    private void initializeSchema() {
        String jdbcUrl = "jdbc:h2:file:./appointment-db;MODE=MySQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "");
             Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS id_generator (gen_name VARCHAR(50) PRIMARY KEY, gen_val BIGINT NOT NULL)");
            statement.execute("INSERT INTO id_generator (gen_name, gen_val) SELECT 'department',0 WHERE NOT EXISTS (SELECT 1 FROM id_generator WHERE gen_name='department')");
            statement.execute("INSERT INTO id_generator (gen_name, gen_val) SELECT 'doctor',0 WHERE NOT EXISTS (SELECT 1 FROM id_generator WHERE gen_name='doctor')");
            statement.execute("INSERT INTO id_generator (gen_name, gen_val) SELECT 'patient',0 WHERE NOT EXISTS (SELECT 1 FROM id_generator WHERE gen_name='patient')");
            statement.execute("INSERT INTO id_generator (gen_name, gen_val) SELECT 'appointment',0 WHERE NOT EXISTS (SELECT 1 FROM id_generator WHERE gen_name='appointment')");
            statement.execute("CREATE TABLE IF NOT EXISTS department (id INT PRIMARY KEY, name VARCHAR(45) NOT NULL)");
            statement.execute("CREATE TABLE IF NOT EXISTS patient (id INT PRIMARY KEY, tc VARCHAR(11) NOT NULL, fullname VARCHAR(45) NOT NULL, gender VARCHAR(6) NOT NULL, phone VARCHAR(15) NOT NULL, email VARCHAR(45) NOT NULL, password VARCHAR(15) NOT NULL)");
            statement.execute("CREATE TABLE IF NOT EXISTS doctor (id INT PRIMARY KEY, fullname VARCHAR(45) NOT NULL, username VARCHAR(45) NOT NULL, password VARCHAR(15) NOT NULL, department_id INT NOT NULL, CONSTRAINT fk_doctor_department FOREIGN KEY (department_id) REFERENCES department(id))");
            statement.execute("CREATE TABLE IF NOT EXISTS appointment (id INT PRIMARY KEY, appoint_date DATE NOT NULL, appointment_time TIME NOT NULL, doctor_id INT NOT NULL, patient_id INT NOT NULL, CONSTRAINT fk_appointment_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id), CONSTRAINT fk_appointment_patient FOREIGN KEY (patient_id) REFERENCES patient(id))");
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not initialize database schema.", ex);
        }
    }

    private static Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private static Date createTime(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
