/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bilgi
 */
@Entity
@Table(name = "appointment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a"),
    @NamedQuery(name = "Appointment.findById", query = "SELECT a FROM Appointment a WHERE a.id = :id"),
    @NamedQuery(name = "Appointment.findByAppointDate", query = "SELECT a FROM Appointment a WHERE a.appointDate = :appointDate"),
    @NamedQuery(name = "Appointment.findByAppointmentTime", query = "SELECT a FROM Appointment a WHERE a.appointmentTime = :appointmentTime"),
    @NamedQuery(name = "Appointment.findByPatientId", query = "SELECT a FROM Appointment a WHERE a.patientId = :patientId"),
    @NamedQuery(name = "Appointment.findByDoctorId", query = "SELECT a FROM Appointment a WHERE a.doctorId = :doctorId")})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "appoint_date")
    @Temporal(TemporalType.DATE)
    private Date appointDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "appointment _time")
    @Temporal(TemporalType.TIME)
    private Date appointmentTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "patient_id")
    private int patientId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doctor_id")
    private int doctorId;

    public Appointment() {
    }

    public Appointment(Integer id) {
        this.id = id;
    }

    public Appointment(Integer id, Date appointDate, Date appointmentTime, int patientId, int doctorId) {
        this.id = id;
        this.appointDate = appointDate;
        this.appointmentTime = appointmentTime;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAppointDate() {
        return appointDate;
    }

    public void setAppointDate(Date appointDate) {
        this.appointDate = appointDate;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Appointment[ id=" + id + " ]";
    }
    
}
