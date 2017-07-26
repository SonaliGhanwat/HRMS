package com.nextech.hrms.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nextech.hrms.main.EmployeeMain;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.util.HibernateUtil;

public class EmployeeLeaveDao {
	public static Scanner sc = new Scanner(System.in);
	public static String data ="";
	public void addEmployeeLeaveUser(Employeeleave employeeleave) throws ClassNotFoundException {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;  
		Employeeleave employeeleave1 = getEmployeeByEmployeeIdAndDate(employeeleave.getEmployee(), (Date) employeeleave.getLeavedate());
		if(employeeleave1==null){
		
		try{
		 tx=session.beginTransaction();  
		   Employee employee = new Employee();
			//employee.setId(employeeAttendance.getEmployee().getId());
			employee.setId(employeeleave.getEmployee().getId());
			employeeleave.setEmployee(employee);
			employeeleave.setSubject(employeeleave.getSubject());
			employeeleave.setLeavedate(employeeleave.getLeavedate());
			employeeleave.setCreatedDate(employeeleave.getCreatedDate());
			employeeleave.setUpdatedDate(employeeleave.getUpdatedDate());
			employeeleave.setIsActive(true);
			session.save(employeeleave);
			session.getTransaction().commit();
		    session.close();  
		    //tx.commit();
			
			}
			catch(Exception e){
				e.printStackTrace();
				 
			}
		System.out.println("Insert Successfully");
		}else{
			System.out.println("id and Date already avilable");
		}
		 
	}
	
	public void deleteEmployeeLeave() throws ClassNotFoundException {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;  
		try{
		  tx=session.beginTransaction();  
		  Employeeleave employeeleave1 = new Employeeleave();
		  System.out.println("Enter EmployeeleaveData Id to delete.");
		  integerValidation();
    	  employeeleave1.setId(Integer.parseInt(data));
    	  Employeeleave employeeleave = (Employeeleave)session.get(Employeeleave.class,employeeleave1.getId());
    	if(employeeleave !=null){
    		employeeleave.setIsActive(false);
    	session.update(employeeleave);
		  session.getTransaction().commit();
		  session.close();  
    	}else{
			System.out.println("please enter valid id");
		}
		  
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Deleted Successfully");
	}
		
	public void updateEmployeeLeaveUser() throws ClassNotFoundException {
		 Session session=HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;  
			try{
				tx=session.beginTransaction(); 
				 Employeeleave employeeleave1 = new Employeeleave();
				System.out.println("Enter EmployeeleaveData Id to update data.");
				integerValidation();
		    	employeeleave1.setId(Integer.parseInt(data));
			    Employeeleave employeeleave = (Employeeleave)session.get(Employeeleave.class,employeeleave1.getId());
			    if(employeeleave !=null){
			    	Employee employee = new Employee();
			    	System.out.println("Enter Employee Id.");
			    	integerValidation();
					employee.setId(Integer.parseInt(data));
					employeeleave.setEmployee(employee);
			    	System.out.println("Enter Subjet");
			    	stringValidation();
				    employeeleave.setSubject(data);
				    System.out.println("Enter leaveDate");
				    dateValidation();
				    employeeleave.setLeavedate(Date.valueOf(data));
				    System.out.println("Enter After LeaveJoiningdate");
				    dateValidation();
				    employeeleave.setAfterleavejoiningdate(Date.valueOf(data));
				    session.update(employeeleave);
				    session.getTransaction().commit();
				    session.close();  
				    }else{
						System.out.println("please enter valid id");
					}
				    //tx.commit();
				  
				}
				catch(Exception e){
					e.printStackTrace();
					tx.rollback(); 
				}
			  System.out.println("Update Successfully");
		 }
	
	 public List<Employeeleave>getEmployeeLeaveAllUsers() throws ClassNotFoundException, SQLException {
		  Session session=HibernateUtil.getSessionFactory().openSession();
		  Transaction tx = null; 
		  Criteria criteria = session.createCriteria(Employeeleave.class);
			criteria.add(Restrictions.eq("isActive", true));
			List<Employeeleave> employeeleaves =  (List<Employeeleave>) (criteria.list().size() > 0 ? criteria.list() : null);
			if(employeeleaves !=null && ! employeeleaves.isEmpty()){
				tx=session.beginTransaction(); 
			for (Employeeleave employeeleave : employeeleaves) {
				employeeleave.getId();
				employeeleave.getEmployee();
				employeeleave.getSubject();
				employeeleave.getLeavedate();
				employeeleave.getAfterleavejoiningdate();
				employeeleave.getCreatedDate();
				employeeleave.getUpdatedDate();
				System.out.println(employeeleave);
			}
		       session.getTransaction().commit();
		      session.close();
			}else{
				System.out.println("please Insert Data");
			}
		return employeeleaves;
		  
	  } 
	 public void getEmployeeLeaveById() throws ClassNotFoundException {
		 Session session=HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null; 
		try {
			tx = session.beginTransaction();
			Employee employee = new Employee();
			Employeeleave employeeleave1 = new Employeeleave();
			System.out.println("Enter Employee Id.");
			integerValidation();
			employee.setId(Integer.parseInt(data));
			employeeleave1.setEmployee(employee);
			Criteria criteria = session.createCriteria(Employeeleave.class);
			criteria.add(Restrictions.eq("employee.id", employeeleave1.getEmployee().getId()));
			Employeeleave employeeleave = criteria.list().size() > 0 ? (Employeeleave) criteria.list().get(0) : null;
			if(employeeleave!=null){
				session.getTransaction().commit();
			    session.close();  
				
			}else{
				System.out.println("please enter valid id");
			}
					
			System.out.println("Employe Daily Task:" + employeeleave);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
  }
	 
	 private Employeeleave getEmployeeByEmployeeIdAndDate(Employee empId,Date date){
		 Session session=HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;  
		  Criteria criteria = session.createCriteria(Employeeleave.class);
		  criteria.add(Restrictions.eq("employee", empId));
		  criteria.add(Restrictions.eq("leavedate",date));
		  Employeeleave  employeeleave = criteria.list().size() > 0 ? (Employeeleave) criteria.list().get(0) : null;
		  return employeeleave;
	 }
	 
	 private List<Employeeleave> calculateEmployeeLeaveByEmployeeId(Employee empId){
		 SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  Session session = sessionFactory.openSession();
		  session.beginTransaction();
		  Criteria criteria = session.createCriteria(Employeeleave.class);
		  criteria.add(Restrictions.eq("employee", empId));
		  Employeeleave  employeeleave = criteria.list().size() > 0 ? (Employeeleave) criteria.list().get(0) : null;
		  
		  SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		  int year = Integer.valueOf(yearFormat.format(employeeleave.getLeavedate()));
		  Query query = session.createQuery("FROM Employeeleave where employeeid=:employeeid and year(leavedate)=:year");
		 query.setParameter("employeeid", empId);
		 query.setParameter("year", year);
		 //query.setParameter("year1", year1);
		 int totalCount=0;
		 List<Employeeleave> employeeleaves = query.list();
		 for (Employeeleave employeeleave1 : employeeleaves) {
			 SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
			  int day = Integer.valueOf(dayFormat.format(employeeleave1.getLeavedate()));
			  SimpleDateFormat dayFormat1 = new SimpleDateFormat("dd");
			  int day1 = Integer.valueOf(dayFormat1.format(employeeleave1.getAfterleavejoiningdate()));
			 Query query1 = session.createQuery("FROM Employeeleave where employeeid=:employeeid and Day(leavedate)=:day and Day(afterleavejoiningdate)=:day1");
			 query1.setParameter("employeeid", empId);
			 query1.setParameter("day", day);
			 query1.setParameter("day1", day1);
			 totalCount=totalCount+day1-day;
		}
		 System.out.println("Total Leave Count:"+totalCount);
			return employeeleaves;
	 }
	 public void calculateEmployeeleave(Employeeleave employeeleave) throws ClassNotFoundException, ParseException {
		    Session session=HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;  
			tx=session.beginTransaction(); 
		    List<Employeeleave> employeeleaves=calculateEmployeeLeaveByEmployeeId(employeeleave.getEmployee());
		    if(employeeleaves !=null && ! employeeleaves.isEmpty()){
			for (Employeeleave employeeleave1 : employeeleaves) {
				
				  System.out.println("Employee Deatials:\n" +employeeleave1);
			}
			session.getTransaction().commit();
		    session.close(); 
		    }else{
           	 System.out.println("There is no Employee Data Avilable for this month ");
				
			}
	 }
	 
	 
	 private List<Employeeleave> calculateMonthlyEmployeeLeaveByEmployeeId(Employee empId){
		 SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  Session session = sessionFactory.openSession();
		  session.beginTransaction();
		  Criteria criteria = session.createCriteria(Employeeleave.class);
		  criteria.add(Restrictions.eq("employee", empId));
		  Employeeleave  employeeleave = criteria.list().size() > 0 ? (Employeeleave) criteria.list().get(0) : null;
		  
		  SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		  int year = Integer.valueOf(yearFormat.format(employeeleave.getLeavedate()));
		  
		  SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		  int month = Integer.valueOf(monthFormat .format(employeeleave.getLeavedate()));
		  Query query = session.createQuery("FROM Employeeleave where employeeid=:employeeid and year(leavedate)=:year and month(leavedate)=:month");
		 query.setParameter("employeeid", empId);
		 query.setParameter("year", year);
		 query.setParameter("month", month);
		 //query.setParameter("year1", year1);
		 int totalCount=0;
		 List<Employeeleave> employeeleaves = query.list();
		 for (Employeeleave employeeleave1 : employeeleaves) {
			 SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
			  int day = Integer.valueOf(dayFormat.format(employeeleave1.getLeavedate()));
			  SimpleDateFormat dayFormat1 = new SimpleDateFormat("dd");
			  int day1 = Integer.valueOf(dayFormat1.format(employeeleave1.getAfterleavejoiningdate()));
			 Query query1 = session.createQuery("FROM Employeeleave where employeeid=:employeeid and Day(leavedate)=:day and Day(afterleavejoiningdate)=:day1");
			 query1.setParameter("employeeid", empId);
			 query1.setParameter("day", day);
			 query1.setParameter("day1", day1);
			 totalCount=totalCount+day1-day;
		}
		 System.out.println("Total Leave Count:"+totalCount);
			return employeeleaves;
	 }
	 public void calculateMonthlyEmployeeleave(Employeeleave employeeleave) throws ClassNotFoundException, ParseException {
		    Session session=HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;  
			tx=session.beginTransaction(); 
		    List<Employeeleave> employeeleaves=calculateMonthlyEmployeeLeaveByEmployeeId(employeeleave.getEmployee());
		    if(employeeleaves !=null && ! employeeleaves.isEmpty()){
			for (Employeeleave employeeleave1 : employeeleaves) {
				
				  System.out.println("Employee Deatials:\n" +employeeleave1);
			}
			session.getTransaction().commit();
		    session.close(); 
		    }else{
           	 System.out.println("There is no Employee Data Avilable for this month ");
				
			}
	 }
	 public void integerValidation(){
			while (true) {
				//data = sc.next();
				 nullValidation();
				if (EmployeeMain.numberOrNot(data)) {
					break;
				} else {
					System.out.println("please enter only number");
				}

			}
		 }
	 public void stringValidation(){
			while (true) {
				//data = sc.next();
				 nullValidation();
				if (EmployeeMain.isFullname(data)) {
					break;
				} else {
					System.out.println("please enter minimum or maximum text 2 to 255");
				}

			}
		 }
	 public void dateValidation() throws ParseException{
		 while (true) {
			     //data = sc.next();
			      nullValidation();
				if (EmployeeMain.isDate(data)) {
					break;
				} else {
					System.out.println("please enter date yyyy-mm-dd format");

				}

		 }
	    }
	 private void nullValidation() {
			while (true) {
				data = sc.nextLine();
				if (data != null && data.length() == 0) {
					System.out.println("please enter data");
				} else {
					break;
				}
			}

		}
	
}
	
