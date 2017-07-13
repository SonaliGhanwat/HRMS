package com.nextech.hrms.main;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.nextech.hrms.dao.EmployeeAttendanceDao;
import com.nextech.hrms.dao.EmployeeDailyTaskDao;
import com.nextech.hrms.dao.EmployeeDao;
import com.nextech.hrms.dao.EmployeeLeaveDao;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.model.Employeeleave;

public class EmployeeMain {
	public long totaltime;
	public static Scanner sc = new Scanner(System.in);
	public static List<Employee> employees = new ArrayList<Employee>();
	public static String data ="";
	public static void main(String args[]) throws Exception {
		EmployeeMain employeeMain = new EmployeeMain();
		employeeMain.displayOperation();
	}

	private void displayOperation() throws Exception {

		System.out.println("**** Menu Options ****");
		int menuChoice = -1;
		while (menuChoice != 0) {

			System.out.println("1. Insert Employee Record");
			System.out.println("2. Delete Employee Record");
			System.out.println("3. Update Employee Record");
			System.out.println("4. Display Employee Record");
			System.out.println("5. Search Employee Record");
			System.out.println("6. Insert EmployeeAttendance Record");
			System.out.println("7. Delete EmployeeAttendance Record");
			System.out.println("8. Update EmployeeAttendance Record");
			System.out.println("9. Display EmployeeAttendance Record");
			System.out.println("10.Search Employeestatus Record");
			System.out.println("11.Insert EmployeeDailyTask Record");
			System.out.println("12.Delete EmployeeDailyTask Record");
			System.out.println("13.Update EmployeeDailyTask Record");
			System.out.println("14.Display EmployeeDailyTask Record");
			System.out.println("15.Search EmployeeDailyTaskBYID Record");
			System.out.println("16.Search EmployeeDailyTaskName Record");
			System.out.println("17.Insert EmployeeLeave Record");
			System.out.println("18.Delete EmployeeLeave Record");
			System.out.println("19.Update EmployeeLeave Record");
			System.out.println("20.Search EmployeeLeave Record");
			System.out.println("21.Search EmployeeLeaveById Record");
			System.out.println("Enter your choice:");
			Scanner sc = new Scanner(System.in);
			menuChoice = sc.nextInt();
			

			switch (menuChoice) {
			case 1: {
				EmployeeMain employeeMain = new EmployeeMain();
				employeeMain.createOpertaion();

			}
			break;
			case 2:{
				EmployeeMain employeeMain = new EmployeeMain();
				employeeMain.deleteOpertaion();
			}
			case 3:{
				EmployeeMain employeeMain = new EmployeeMain();
				employeeMain.updateOpertaion();
				
			}
			break;
			case 4:{
				EmployeeMain employeeMain = new EmployeeMain();
				employeeMain.displayOpertaion();
			}
			case 5:{
				EmployeeMain employeeMain = new EmployeeMain();
				employeeMain.searchOpertaion();
			}
             case 6:{
				
				EmployeeMain employeeMain = new EmployeeMain();
				employeeMain.createAttendanceOpertaion();
			}
             case 7:{
 				EmployeeMain employeeMain = new EmployeeMain();
 				employeeMain.deleteAttendanceOpertaion();
 			}
             case 8:{
 				EmployeeMain employeeMain = new EmployeeMain();
 				employeeMain.updateEmployeeAttendanceOpertaion();
 			}
             case 9:{
  				EmployeeMain employeeMain = new EmployeeMain();
  				employeeMain.displayAttendanceOpertaion();
  			}
             case 10:{
   				EmployeeMain employeeMain = new EmployeeMain();
   				employeeMain.searchEmployeeAttendanceOpertaion();
   			}
             case 11:{
 				EmployeeMain employeeMain = new EmployeeMain();
 				employeeMain.createEmployeeDailyTaskOpertaion();
 				
 			}
             case 12:{
 				EmployeeMain employeeMain = new EmployeeMain();
 				employeeMain.deleteDailyTaskOpertaion();
 				
 			}
             case 13:{
 				EmployeeMain employeeMain = new EmployeeMain();
 				employeeMain.updateDailyTaskOpertaion();
 				
 			}
             case 14:{
  				EmployeeMain employeeMain = new EmployeeMain();
  				employeeMain.displayDailyTaskOpertaion();
  				
  			}
             case 15:{
    				EmployeeMain employeeMain = new EmployeeMain();
    				employeeMain.searchDailyTaskByIDOpertaion();
    				
    			}
             case 16:{
   				EmployeeMain employeeMain = new EmployeeMain();
   				employeeMain.searchEmployeeDailyTaskNameOpertaion();
   				
   			}
             case 17:{
    				EmployeeMain employeeMain = new EmployeeMain();
    				employeeMain.createEmployeeLeaveOpertaion();
    				
    			}
             case 18:{
 				EmployeeMain employeeMain = new EmployeeMain();
 				employeeMain.deleteEmployeeLeaveOpertaion();
 				
 			}
             case 19:{
  				EmployeeMain employeeMain = new EmployeeMain();
  				employeeMain.updateEmployeeLeaveOpertaion();
  				
  			}
             case 20:{
   				EmployeeMain employeeMain = new EmployeeMain();
   				employeeMain.searchEmployeeLeaveOpertaion();
   				
   			}
             case 21:{
    				EmployeeMain employeeMain = new EmployeeMain();
    				employeeMain.searchEmployeeLeaveByIdOpertaion();;
    				
    			}
			}
		}
	}
	private void createOpertaion() throws Exception{
		Employee employee = new Employee();
		/*System.out.println("Enter Employee Id.");
	    int id = Integer.parseInt(sc.nextLine());
		employee.setId(id);*/
		System.out.println("Enter Employee userid");
		//String userid = (sc.nextLine());
		nullValidation();
		employee.setUserid(data);
		System.out.println("Enter Employee password");
		//String password = (sc.nextLine());
		nullValidation();
		employee.setPassword(data);
		System.out.println("Enter Employee firstName");
		stringValidation();
		employee.setFirstName(data);
		System.out.println("Enter Employee lastName");
		stringValidation();
		employee.setLastName(data);
		System.out.println("Enter Employee phoneNumber");
		phoneNumberValidation();
		System.out.println("Enter Emailid");
		emailidValidation();
		employee.setEmailid(data);
		System.out.println("Enter Dateofjoining");
		dateValidation();
		employee.setDateOfJoining(Date.valueOf(data));
		System.out.println("Enter Dateofbirth");
		dateValidation();
		employee.setDateOfBirth(Date.valueOf(data));
		String s =(sc.nextLine());
		System.out.println("Enter Address");
		String address = (sc.nextLine());
		employee.setAddress(address);
		System.out.println("Enter Department");
		String department = (sc.nextLine());
		employee.setDepartment(department);
		System.out.println("Enter salary");
		integerValidation();
		employee.setSalary(Integer.parseInt(data));
		EmployeeDao employeeDao=new EmployeeDao();
		employeeDao.addUser(employee);
		displayOperation();
		
	}
	private void deleteOpertaion() throws Exception{
		EmployeeDao employeeDao=new EmployeeDao();
		employeeDao.deleteUser();
		displayOperation();
	}
	private void updateOpertaion() throws Exception{
		EmployeeDao employeeDao=new EmployeeDao();
		employeeDao.updateUser();
		displayOperation();
	}
	private void displayOpertaion() throws Exception{
		EmployeeDao employeeDao=new EmployeeDao();
		employeeDao.getAllUsers();
		displayOperation();
	}
	private void searchOpertaion() throws Exception{
		EmployeeDao employeeDao=new EmployeeDao();
		employeeDao.getUserById();
		displayOperation();
		
	}
	private void createAttendanceOpertaion() throws Exception{
		Employeeattendance employeeAttendance=new Employeeattendance();
		Employee employee = new Employee();
		System.out.println("Enter Employee Id.");
	    //int id = Integer.parseInt(sc.nextLine());
		integerValidation();
		employee.setId(Integer.parseInt(data));
		//employee.setId(2);
		employeeAttendance.setEmployee(employee);
		System.out.println("Enter intime");
		timeValidation();
		employeeAttendance.setIntime(Time.valueOf(data));
		Time intime = employeeAttendance.getIntime();
		System.out.println("Enter outtime");
		timeValidation();
		employeeAttendance.setOuttime(Time.valueOf(data));
		Time outtime = employeeAttendance.getOuttime();
	    totaltime = outtime.getTime() - intime.getTime(); 
	    System.out.println("time : " +totaltime);
	    long diffHours = totaltime / (60 * 60 * 1000) % 24;
		System.out.print("Totaltime\n"  +diffHours);
		employeeAttendance.setTotaltime(diffHours);
		System.out.println();
		System.out.println("Enter date");
		dateValidation();
		employeeAttendance.setDate(Date.valueOf(data));
		EmployeeAttendanceDao employeeAttendanceDao=new EmployeeAttendanceDao();
		employeeAttendanceDao.addEmployeeAttendance(employeeAttendance);
		displayOperation();
		
	}
	private void deleteAttendanceOpertaion() throws Exception{
	    EmployeeAttendanceDao employeeAttendanceDao=new EmployeeAttendanceDao();
		employeeAttendanceDao.deleteEmployeeAttendance();
		displayOperation();
	}
	private void updateEmployeeAttendanceOpertaion() throws Exception{
		EmployeeAttendanceDao employeeAttendanceDao=new EmployeeAttendanceDao();
		employeeAttendanceDao.updateEmployeeAtteandanceUser();
		displayOperation();
	}
	private void displayAttendanceOpertaion() throws Exception{
		EmployeeAttendanceDao employeeAttendanceDao=new EmployeeAttendanceDao();
		employeeAttendanceDao.getEmployeeAttendanceAllUsers();
		displayOperation();
	}
	private void searchEmployeeAttendanceOpertaion() throws Exception{
		EmployeeAttendanceDao employeeAttendanceDao=new EmployeeAttendanceDao();
		employeeAttendanceDao.getEmployeeAttendanceUserById() ;
		displayOperation();
		
	}
	private void createEmployeeDailyTaskOpertaion() throws Exception{
		Employeedailytask employeeDailyTask = new Employeedailytask();
		Employee employee = new Employee();
		System.out.println("Enter Employee Id.");
		integerValidation();
		employee.setId(Integer.parseInt(data));
		employeeDailyTask.setEmployee(employee);
		System.out.println("Enter date");
		dateValidation();
		employeeDailyTask.setDate(Date.valueOf(data));
		System.out.println("Enter TaskName");
		stringValidation();
		employeeDailyTask.setTaskName(data);
		System.out.println("Enter EstimationTime");
		timeValidation();
		employeeDailyTask.setEstimationTime(Time.valueOf(data));;
		System.out.println("Enter StartTime");
		timeValidation();
		employeeDailyTask.setStarttime(Time.valueOf(data));
		Time starttime= employeeDailyTask.getStarttime();
		System.out.println("Enter EndTime");
		timeValidation();
		employeeDailyTask.setEndtime(Time.valueOf(data));
		Time endtime = employeeDailyTask.getEndtime();
		long totaltime = endtime.getTime() - starttime.getTime();
		long diffHours = totaltime / (60 * 60 * 1000) % 24;
		System.out.print("Totaltime\n"  +diffHours);
		employeeDailyTask.setTakenTime(diffHours);
		System.out.println();
		EmployeeDailyTaskDao employeeDailyTaskDao =new EmployeeDailyTaskDao();
		employeeDailyTaskDao.addEmployeedailytaskUser(employeeDailyTask);
		displayOperation();
		
	}
	private void deleteDailyTaskOpertaion() throws Exception{
		EmployeeDailyTaskDao employeeDailyTaskDao =new EmployeeDailyTaskDao();
		employeeDailyTaskDao.deleteEmployeeDailyTask();
		displayOperation();
	}
	private void updateDailyTaskOpertaion() throws Exception{
		EmployeeDailyTaskDao employeeDailyTaskDao =new EmployeeDailyTaskDao();
		employeeDailyTaskDao.updateDailyTaskUser();
		displayOperation();
	}
	private void displayDailyTaskOpertaion() throws Exception{
		EmployeeDailyTaskDao employeeDailyTaskDao =new EmployeeDailyTaskDao();
		employeeDailyTaskDao.getDailyTaskAllUsers();
		displayOperation();
	}
	private void searchDailyTaskByIDOpertaion() throws Exception{
		EmployeeDailyTaskDao employeeDailyTaskDao =new EmployeeDailyTaskDao();
		employeeDailyTaskDao.getDailyTaskUserById();
		displayOperation();
		
	}
	private void searchEmployeeDailyTaskNameOpertaion()throws Exception{
		EmployeeDailyTaskDao employeeDailyTaskDao =new EmployeeDailyTaskDao();
		employeeDailyTaskDao.getUserTaskName();
		displayOperation();
		
	}
	private void createEmployeeLeaveOpertaion() throws Exception{
		Employeeleave employeeleave = new Employeeleave();
		Employee employee = new Employee();
		System.out.println("Enter Employee Id.");
		integerValidation();
		employee.setId(Integer.parseInt(data));
		//employee.setId(2);
	    employeeleave.setEmployee(employee);
	    System.out.println("Enter Subjet");
	    stringValidation();
	    employeeleave.setSubject(data);
	    System.out.println("Enter leaveDate");
	    dateValidation();
	    employeeleave.setLeavedate(Date.valueOf(data));
	    System.out.println("Enter AfterLeaveJoiningdate");
	    dateValidation();
	    employeeleave.setAfterleavejoiningdate(Date.valueOf(data));
	    EmployeeLeaveDao employeeLeaveDao = new EmployeeLeaveDao();
	    employeeLeaveDao.addEmployeeLeaveUser(employeeleave);
	    displayOperation();
	}
	private void deleteEmployeeLeaveOpertaion() throws Exception{
		 EmployeeLeaveDao employeeLeaveDao = new EmployeeLeaveDao();
		 employeeLeaveDao.deleteEmployeeLeave();
		 displayOperation();
	}
	private void updateEmployeeLeaveOpertaion() throws Exception{
		 EmployeeLeaveDao employeeLeaveDao = new EmployeeLeaveDao();
		 employeeLeaveDao.updateEmployeeLeaveUser();
		 displayOperation();
	}
	private void searchEmployeeLeaveOpertaion() throws Exception{
		 EmployeeLeaveDao employeeLeaveDao = new EmployeeLeaveDao();
		 employeeLeaveDao.getEmployeeLeaveAllUsers();
		displayOperation();
		
	}
	private void searchEmployeeLeaveByIdOpertaion() throws Exception{
		 EmployeeLeaveDao employeeLeaveDao = new EmployeeLeaveDao();
		 employeeLeaveDao.getEmployeeLeaveById();
		displayOperation();
		
	}
	
	private void phoneNumberValidation() throws ParseException{
		while (true) {
			//data = sc.next();
			nullValidation();
			if (EmployeeMain.isPhoneNumber(data)) {
				break;
			} else {
				System.out.println("please enter only 10 digit mobile number");
			}

		}
		
	}
	private void integerValidation(){
		
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
	private void nullValidation(){
		while(true){
			data=sc.nextLine();
			if(data != null && data.length() == 0){
				System.out.println("please enter  data");
			}else{
				break;
			}
		}
		
	}
	
	private void stringValidation(){
		while (true) {
			//data = sc.next();
			nullValidation();
			if (EmployeeMain.isFullname(data)) {
				break;
			} else {
				System.out.println("please enter only character");
			}

		}
	 }
	private void emailidValidation(){
		 while (true) {
				//data = sc.next();
			     nullValidation();
				if (EmployeeMain.isEmailid(data)) {
					break;
				} else {
					System.out.println("please enter emailid abc@ds.com format");

				}

			}
	    }
	private void dateValidation() throws ParseException{
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
	private void timeValidation() throws ParseException{
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
	 public static boolean isFullname(String str) {
		    String expression = "[a-zA-Z]+"; 
		    return str.matches(expression);        
		}
	 public static boolean isEmailid(String str) {
		    String expression = "[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9]{3}"; 
		    return str.matches(expression);        
		}
	 public static boolean isDate(String str) throws ParseException {
		 String expression = "([0-9]{4})-([0-1]{1}[1-9]{1})-([0-2][0-9]||3[0-1])"; 
		return str.matches(expression); 
     }
     public static boolean isTime(String str) throws ParseException {
		 
		 String expression = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]"; 
		return str.matches(expression); 
     }
     public static boolean isPhoneNumber(String str) throws ParseException {
		 String expression = "([0-9]{10})"; 
		return str.matches(expression); 
     }
     
}
