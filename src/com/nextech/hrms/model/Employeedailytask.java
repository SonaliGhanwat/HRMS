package com.nextech.hrms.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the employeedailytask database table.
 * 
 */
@Entity
@NamedQuery(name="Employeedailytask.findAll", query="SELECT e FROM Employeedailytask e")
public class Employeedailytask implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private int id;

	private boolean isActive;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="task_name")
	private String taskName;

	@Column(name="estimation_time")
	private Time estimationTime;

	private Time starttime;
	
	private Time endtime;

	@Column(name="taken_time")
	private long takenTime;
	
	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employeeid")
	private Employee employee;

	public Employeedailytask() {
	}
	public Employeedailytask(int id) {
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

	public Time getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Time endtime) {
		this.endtime = endtime;
	}

	public Time getEstimationTime() {
		return this.estimationTime;
	}

	public void setEstimationTime(Time estimationTime) {
		this.estimationTime = estimationTime;
	}

	public Time getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Time starttime) {
		this.starttime = starttime;
	}

	public long getTakenTime() {
		return this.takenTime;
	}

	public void setTakenTime(long diffHours) {
		this.takenTime = diffHours;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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
		return "Employeedailytaskid=" + id + "\n date=" + date + "\n taskName="
				+ taskName + "\n estimationTime=" + estimationTime
				+ "\n starttime=" + starttime + "\n endtime=" + endtime
				+ "\n takenTime=" + takenTime + "\n createdDate=" + createdDate
				+ "\n updatedDate=" + updatedDate + "\n employee=" + employee;
				
	}

	
}