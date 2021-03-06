package com.nextech.hrms.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Formula;

import java.sql.Time;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@NamedQuery(name="Employeeattendance.findAll", query="SELECT e FROM Employeeattendance e")
public class Employeeattendance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "Id")
	private int id;

	private boolean isActive;
	
	@Temporal(TemporalType.DATE)
	private Date date;

	private Time intime;

	private Time outtime;

	private String status;
	
	private long totaltime; 
	
	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employeeid")
	private Employee employee;

	public Employeeattendance() {
	}
	public Employeeattendance(int id) {
		this.id=id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getIntime() {
		return this.intime;
	}

	public void setIntime(Time intime) {
		this.intime = intime;
	}

	public Time getOuttime() {
		return this.outtime;
	}

	public void setOuttime(Time outtime) {
		this.outtime = outtime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getTotaltime() {
		return this.totaltime;
	}

	public void setTotaltime(long totaltime) {
		this.totaltime = totaltime;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Employeeattendanceid=" + id + "\n date=" + date + "\n intime="
				+ intime + "\n outtime=" + outtime + "\n status=" + status
				+ "\n totaltime=" + totaltime + "\n createdDate=" + createdDate
				+ "\n updatedDate=" + updatedDate + "\n employee=" + employee;
				
	}

}