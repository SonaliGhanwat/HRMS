package com.nextech.hrms.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private int id;

	private boolean isActive;
	
	private String address;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_birth")
	private Date dateOfBirth;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_joining")
	private Date dateOfJoining;

	private String department;

	private String emailid;

	@Column(name="first_name")
	private String firstName;
	
	

	@Column(name="last_name")
	private String lastName;

	private String password;

	@Column(name="phone_number")
	private int phoneNumber;

	private int salary;

	private String userid;

	//bi-directional many-to-one association to Employeeattendance
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Employeeattendance> employeeattendances;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Employeedailytask> employeedailytasks;

	public List<Employeedailytask> getEmployeedailytasks() {
		return employeedailytasks;
	}

	public void setEmployeedailytasks(List<Employeedailytask> employeedailytasks) {
		this.employeedailytasks = employeedailytasks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Employee() {
	}
	
	public Employee(int id) {
		this.id=id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfJoining() {
		return this.dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getSalary() {
		return this.salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	
	/*public List<Employeeattendance> getEmployeeattendances() {
		return this.employeeattendances;
	}

	public void setEmployeeattendances(List<Employeeattendance> employeeattendances) {
		this.employeeattendances = employeeattendances;
	}

	public Employeeattendance addEmployeeattendance(Employeeattendance employeeattendance) {
		getEmployeeattendances().add(employeeattendance);
		employeeattendance.setEmployee(this);

		return employeeattendance;
	}

	public Employeeattendance removeEmployeeattendance(Employeeattendance employeeattendance) {
		getEmployeeattendances().remove(employeeattendance);
		employeeattendance.setEmployee(null);

		return employeeattendance;
	}*/


public List<Employeeattendance> getEmployeeattendances() {
		return employeeattendances;
	}

	public void setEmployeeattendances(List<Employeeattendance> employeeattendances) {
		this.employeeattendances = employeeattendances;
	}

@Override
	public String toString() {
		return "Employee id=" + this.id + "\n address=" + this.address + "\n dateOfBirth="
				+ this.dateOfBirth + "\n dateOfJoining=" + this.dateOfJoining
				+ "\n department=" + this.department + "\n emailid=" + this.emailid
				+ "\n firstName=" + this.firstName + "\n lastName=" + this.lastName
				+ "\n password=" + this.password + "\n phoneNumber=" + this.phoneNumber
				+ "\n salary=" + this.salary + "\n userid=" + this.userid;
				//+ "\n employeeattendances=" + this.employeeattendances ;
	}
}