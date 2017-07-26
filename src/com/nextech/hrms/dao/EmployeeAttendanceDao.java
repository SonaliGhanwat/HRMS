package com.nextech.hrms.dao;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;  
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import com.nextech.hrms.main.EmployeeMain;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.EmployeeAttendanceDTO;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.util.HibernateUtil;


public class EmployeeAttendanceDao {
	public static Scanner sc = new Scanner(System.in);
	public long totaltime;
	public static String data ="";
	
	public void addEmployeeAttendance(Employeeattendance employeeattendance) throws ClassNotFoundException {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;  
	    Employeeattendance employeeattendance2=	getEmployeeByEmployeeIdAndDate(employeeattendance.getEmployee(), employeeattendance.getDate());
		if(employeeattendance2==null){
		try{
	    tx=session.beginTransaction();  
		Employee employee = new Employee();
		//employee.setId(employeeAttendance.getEmployee().getId());
		employee.setId(employeeattendance.getEmployee().getId());
		employeeattendance.setEmployee(employee);
		employeeattendance.setIntime(employeeattendance.getIntime());
		employeeattendance.setOuttime(employeeattendance.getOuttime());
		employeeattendance.setTotaltime(employeeattendance.getTotaltime());
		employeeattendance.setDate(employeeattendance.getDate());
		getEmployeeAttendanceStatus(employeeattendance);
		employeeattendance.setStatus(employeeattendance.getStatus());
		employeeattendance.setCreatedDate(employeeattendance.getCreatedDate());
		employeeattendance.setUpdatedDate(employeeattendance.getUpdatedDate());
		employeeattendance.setIsActive(true);
		session.save(employeeattendance);
		session.getTransaction().commit();
	    session.close();  
	    //tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();  
		}
		System.out.println("Employee Attendance Insert Successfully");
		}else{
			System.out.println("id and Date already avilable");
		}
	}
	 public void getEmployeeAttendanceStatus(Employeeattendance employeeAttendance) throws ClassNotFoundException, SQLException{
		 //Session session=HibernateUtil.getSessionFactory().openSession();
		 //Transaction tx = null;  
		 try {
			 //tx=session.beginTransaction();  
			
		        if(employeeAttendance.getTotaltime()>=1 && employeeAttendance.getTotaltime()<=4){
			        employeeAttendance.setStatus("HalfDay");
		        }else if(employeeAttendance.getTotaltime()>=4 && employeeAttendance.getTotaltime()>=9){
			        employeeAttendance.setStatus("Fullday");
		        }else{
			        employeeAttendance.setStatus("Absent");
		        }
	    	}
	        catch (Exception e)
	        {
	          System.err.println("Got an exception!");
	          e.printStackTrace();
	        }
	        
		}
	 public void deleteEmployeeAttendance() throws ClassNotFoundException {
	    		Session session=HibernateUtil.getSessionFactory().openSession();
	    		Transaction tx = null;  
	    		try{
	    		  tx=session.beginTransaction();  
	    		  Employeeattendance employeeAttendance1=new Employeeattendance();
		    	  System.out.println("Enter EmployeeAttendance Id to delete.");
		    	  integerValidation();
		    	  employeeAttendance1.setId(Integer.parseInt(data));
		    	Employeeattendance employeeAttendance = (Employeeattendance)session.get(Employeeattendance.class,employeeAttendance1.getId());
		    	if(employeeAttendance !=null){
		    	  employeeAttendance.setIsActive(false);
		    	  session.update(employeeAttendance);
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
	 public void updateEmployeeAtteandanceUser() throws ClassNotFoundException {
		 Session session=HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;  
			try{
				tx=session.beginTransaction(); 
				Employeeattendance employeeattendance1 = new Employeeattendance();
				System.out.println("Enter EmployeeAttendance Id to update data.");
				integerValidation();
				employeeattendance1.setId(Integer.parseInt(data));
			    Employeeattendance employeeattendance = (Employeeattendance)session.get(Employeeattendance.class,employeeattendance1.getId());
			    if(employeeattendance !=null){
			    System.out.println("Enter intime");
			    timeValidation();
				employeeattendance.setIntime(Time.valueOf(data));
				employeeattendance.setIntime(employeeattendance.getIntime());
				Time intime = employeeattendance.getIntime();
				System.out.println("Enter outtime");
				timeValidation();
				employeeattendance.setOuttime(Time.valueOf(data));
				employeeattendance.setOuttime(employeeattendance.getOuttime());
				Time outtime = employeeattendance.getOuttime();
				totaltime = outtime.getTime() - intime.getTime(); 
				System.out.println("time : " +totaltime);
				long diffHours = totaltime / (60 * 60 * 1000) % 24;
			    System.out.print("Totaltime\n"  +diffHours);
				employeeattendance.setTotaltime(diffHours);
				employeeattendance.setTotaltime(employeeattendance.getTotaltime());
				getEmployeeAttendanceStatus(employeeattendance);
				employeeattendance.setStatus(employeeattendance.getStatus());
			    session.update(employeeattendance);
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
	 public List<Employeeattendance> getEmployeeAttendanceAllUsers() throws ClassNotFoundException, SQLException {
		  Session session=HibernateUtil.getSessionFactory().openSession();
		  Transaction tx = null; 
		  Criteria criteria = session.createCriteria(Employeeattendance.class);
			criteria.add(Restrictions.eq("isActive", true));
			List<Employeeattendance> employeeattendances =  (List<Employeeattendance>) (criteria.list().size() > 0 ? criteria.list() : null);
			if(employeeattendances !=null && ! employeeattendances.isEmpty()){
				tx=session.beginTransaction(); 
		for (Employeeattendance employeeattendance : employeeattendances) {
			  employeeattendance.getId();
			  employeeattendance.getEmployee();
			  employeeattendance.getIntime();
			  employeeattendance.getOuttime();
			  employeeattendance.getTotaltime();
			  employeeattendance.getDate();
			  employeeattendance.getStatus();
			  employeeattendance.getCreatedDate();
			  employeeattendance.getUpdatedDate();
			  System.out.println(employeeattendance);
		}
		session.getTransaction().commit();
		session.close();
			}else{
				System.out.println("please Insert Data");
			}
		return employeeattendances;
		  
	  } 
	 public void getEmployeeAttendanceUserById() throws ClassNotFoundException {
		 Session session=HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null; 
			try{
				tx=session.beginTransaction(); 
				Employeeattendance employeeattendance1 = new Employeeattendance();
				System.out.println("Enter EmployeeAttendance Id .");
				integerValidation();
				employeeattendance1.setId(Integer.parseInt(data));
				Employeeattendance employeeattendance = (Employeeattendance)session.get(Employeeattendance.class,employeeattendance1.getId());
				if(employeeattendance !=null){
			    session.getTransaction().commit();
			    session.close();  
			    //tx.commit();
			    System.out.println(employeeattendance);
				}else{
					System.out.println("please enter valid id");
				}
			
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback(); 
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
	 public static boolean numberOrNot(String input)
	    {
	        try
	        {
	            Integer.parseInt(input);
	        }
	        catch(NumberFormatException ex)
	        {
	            return false;
	        }
	        return true;
	    }
	 public void timeValidation() throws ParseException{
		while (true) {
			// data = sc.next();
			nullValidation();
			if (EmployeeMain.isTime(data)) {
				break;
			} else {
				System.out.println("please enter date hh-mm-ss format");

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
	 private Employeeattendance getEmployeeByEmployeeIdAndDate(Employee empId,Date date){
		 
		  Session session=HibernateUtil.getSessionFactory().openSession();
		  Transaction tx = null;  
		  Criteria criteria = session.createCriteria(Employeeattendance.class);
		  criteria.add(Restrictions.eq("employee", empId));
		  criteria.add(Restrictions.eq("date",date));
		  Employeeattendance employeeattendance = criteria.list().size() > 0 ? (Employeeattendance) criteria.list().get(0) : null;
		  return employeeattendance;
	 }
	 
	/* @SuppressWarnings("unchecked")
	private List<Employeeattendance> getEmployeeByEmployeeIdAndDate1(Employee empId){
		  Session session=HibernateUtil.getSessionFactory().openSession();
		  Transaction tx = null;  
		  Criteria criteria = session.createCriteria(Employeeattendance.class);
		  criteria.add(Restrictions.eq("employee", empId));
		  //criteria.add(Restrictions.eq("date",date));
		  return (criteria.list().size() > 0 ? (List<Employeeattendance>)criteria.list() : null);
	 }
	 public void calculateEmployeeAttendance1(Employeeattendance employeeattendance) throws ClassNotFoundException {
		
		    Session session=HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;  
			long daytime=45;
			long totaltime=0;
		    float percent=0;
		    List<Employeeattendance> employeeattendances=	getEmployeeByEmployeeIdAndDate1(employeeattendance.getEmployee());
			for (Employeeattendance employeeattendance1 : employeeattendances) {
				 totaltime= totaltime+employeeattendance1.getTotaltime();
			}
			percent = totaltime*100;
			percent=percent/daytime;
			System.out.println(percent);
			
			
	 }*/
	 @SuppressWarnings({ "rawtypes", "unused" })
	public List<Employeeattendance> calculateEmployeeAttendanceByEmployeeIdAndDate1(Employee empid,Date date) throws ParseException{
		 SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  Session session = sessionFactory.openSession();
		  session.beginTransaction();
		  SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		  int year = Integer.valueOf(yearFormat.format(date));

		  SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		  int month = Integer.valueOf(monthFormat .format(date));
		  String totaltime="";
		  Query query = session.createQuery("from Employeeattendance where  employeeid=:employeeid and year(date)=:year and month(date)=:month");
		 query.setParameter("employeeid", empid);
		 query.setParameter("year", year);
		 query.setParameter("month", month);
		 List<EmployeeAttendanceDTO> employeeAttendanceDTOs =  new ArrayList<EmployeeAttendanceDTO>();
		 List<Employeeattendance> employeeattendances = query.list();
		 for (Employeeattendance employeeattendance1 : employeeattendances) {
			 EmployeeAttendanceDTO employeeAttendanceDTO =  new EmployeeAttendanceDTO();
			 employeeattendance1.getStatus();
			 if(employeeattendance1.getStatus().equals("Fullday")){
				 employeeAttendanceDTO.setStatus(employeeattendance1.getStatus());
				 employeeAttendanceDTOs.add(employeeAttendanceDTO);
			 }else if(employeeattendance1.getStatus().equals("HalfDay")){
				 employeeAttendanceDTO.setStatus(employeeattendance1.getStatus());
				 employeeAttendanceDTOs.add(employeeAttendanceDTO);
			 }
			
			 
		 }
		 Integer count = employeeAttendanceDTOs.size();
		 System.out.println("Employee attendance:" + count);
		 

		return employeeattendances;
	 }
	 public void calculateEmployeeAttendance(Employeeattendance employeeattendance) throws ClassNotFoundException, ParseException {
			
		    Session session=HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;  
			tx=session.beginTransaction(); 
			List<Employeeattendance> employeeattendances=calculateEmployeeAttendanceByEmployeeIdAndDate1(employeeattendance.getEmployee(),employeeattendance.getDate());
			if(employeeattendances !=null && ! employeeattendances.isEmpty()){
			for (Employeeattendance employeeattendance1 : employeeattendances) {
				  System.out.println(employeeattendance1);
			}
			 session.getTransaction().commit();
			    session.close(); 
             }else{
            	 System.out.println("There is no Employee Data Avilable for this month ");
				
			}
	 }
}
	 

	 