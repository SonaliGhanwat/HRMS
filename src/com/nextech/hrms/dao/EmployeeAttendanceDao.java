package com.nextech.hrms.dao;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Transaction;  
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.nextech.hrms.main.EmployeeMain;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.util.HibernateUtil;


public class EmployeeAttendanceDao {
	public static Scanner sc = new Scanner(System.in);
	public long totaltime;
	public static String data ="";
	public void addEmployeeAttendance(Employeeattendance employeeattendance) throws ClassNotFoundException {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;  
		
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
		 System.out.println("Insert Successfully");
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();  
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
		    	  System.out.println("Enter Employee Id to delete.");
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
	    		  System.out.println("Deleted Successfully");
	    		}
	    		catch(Exception e){
	    			e.printStackTrace();
	    		}
	    	}
	 public void updateEmployeeAtteandanceUser() throws ClassNotFoundException {
		 Session session=HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;  
			try{
				tx=session.beginTransaction(); 
				Employeeattendance employeeattendance1 = new Employeeattendance();
				System.out.println("Enter Employee Id to update data.");
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
			    System.out.println("Update Successfully");
			}
			catch(Exception e){
				e.printStackTrace();
				tx.rollback(); 
			}
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
				System.out.println("Enter Employee Id .");
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
			while (sc.hasNext()) {
				data = sc.next();
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
		 while (sc.hasNext()) {
			     data = sc.next();
				if (EmployeeMain.isTime(data)) {
					break;
				} else {
					System.out.println("please enter date hh-mm-ss format");

				}
		 }
	    }
	 
}
