package com.nextech.hrms.main;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.nextech.hrms.dao.EmployeeAttendanceDao;
import com.nextech.hrms.dao.EmployeeDailyTaskDao;
import com.nextech.hrms.dao.EmployeeDao;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeedailytask;

public class EmployeeMain {
	public long totaltime;
	public static Scanner sc = new Scanner(System.in);
	public static List<Employee> employees = new ArrayList<Employee>();

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
			System.out.println("8.Update EmployeeAttendance Record");
			System.out.println("9.Display EmployeeAttendance Record");
			System.out.println("10.Search Employeestatus Record");
			System.out.println("11.Insert EmployeeDailyTask Record");
			System.out.println("12.Delete EmployeeDailyTask Record");
			System.out.println("13.Update EmployeeDailyTask Record");
			System.out.println("14.Display EmployeeDailyTask Record");
			System.out.println("15.Search EmployeeDailyTaskBYID Record");
			System.out.println("16.Search EmployeeDailyTaskName Record");
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
  				employeeMain.displayDailyTaskOpertaion();;
  				
  			}
             case 15:{
    				EmployeeMain employeeMain = new EmployeeMain();
    				employeeMain.searchDailyTaskByIDOpertaion();;
    				
    			}
             case 16:{
   				EmployeeMain employeeMain = new EmployeeMain();
   				employeeMain.searchEmployeeDailyTaskNameOpertaion();;
   				
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
		String userid = (sc.nextLine());
		employee.setUserid(userid);
		System.out.println("Enter Employee password");
		String password = (sc.nextLine());
		employee.setPassword(password);
		System.out.println("Enter Employee firstName");
		String firstName = (sc.nextLine());
		employee.setFirstName(firstName);
		System.out.println("Enter Employee lastName");
		String lastName = (sc.nextLine());
		employee.setLastName(lastName);
		System.out.println("Enter Employee phoneNumber");
		int phoneNumber = Integer.parseInt(sc.nextLine());
		employee.setPhoneNumber(phoneNumber);
		System.out.println("Enter Emailid");
		String emailId = (sc.nextLine());
		employee.setEmailid(emailId);
		System.out.println("Enter Dateofjoining");
		Date DateOfJoining = (Date.valueOf(sc.nextLine()));
		employee.setDateOfJoining(DateOfJoining);
		System.out.println("Enter Dateofbirth");
		Date DateOfBirth = (Date.valueOf(sc.nextLine()));
		employee.setDateOfBirth(DateOfBirth);
		System.out.println("Enter Address");
		String address = (sc.nextLine());
		employee.setAddress(address);
		System.out.println("Enter Department");
		String department = (sc.nextLine());
		employee.setDepartment(department);
		System.out.println("Enter salary");
		int salary = Integer.parseInt(sc.nextLine());
		employee.setSalary(salary);
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
	public void displayOpertaion() throws Exception{
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
	    int id = Integer.parseInt(sc.nextLine());
		employee.setId(id);
		//employee.setId(2);
		employeeAttendance.setEmployee(employee);
		System.out.println("Enter intime");
		Time intime=(Time.valueOf(sc.nextLine()));
		employeeAttendance.setIntime(intime);
		System.out.println("Enter outtime");
		Time outtime=(Time.valueOf(sc.nextLine()));
		employeeAttendance.setOuttime(outtime);
	    totaltime = outtime.getTime() - intime.getTime(); 
	    System.out.println("time : " +totaltime);
	    long diffHours = totaltime / (60 * 60 * 1000) % 24;
		System.out.print("Totaltime\n"  +diffHours);
		employeeAttendance.setTotaltime(diffHours);
		System.out.println();
		System.out.println("Enter date");
		Date date=(Date.valueOf(sc.nextLine()));
		employeeAttendance.setDate(date);
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
		/*System.out.println("Enter Employee Id.");
	    int id = Integer.parseInt(sc.nextLine());*/
		employee.setId(2);
		employeeDailyTask.setEmployee(employee);
		System.out.println("Enter date");
		Date date=(Date.valueOf(sc.nextLine()));
		employeeDailyTask.setDate(date);
		System.out.println("Enter TaskName");
		String tsakname=(sc.nextLine());
		employeeDailyTask.setTaskName(tsakname);
		System.out.println("Enter EstimationTime");
		Time estimation_time=(Time.valueOf(sc.nextLine()));
		employeeDailyTask.setEstimationTime(estimation_time);;
		System.out.println("Enter StartTime");
		Time starttime=(Time.valueOf(sc.nextLine()));
		employeeDailyTask.setStarttime(starttime);
		System.out.println("Enter EndTime");
		Time endtime=(Time.valueOf(sc.nextLine()));
		employeeDailyTask.setEndtime(endtime);
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
		EmployeeAttendanceDao employeeAttendanceDao=new EmployeeAttendanceDao();
		employeeAttendanceDao.getEmployeeAttendanceUserById() ;
		displayOperation();
		
	}
	private void searchEmployeeDailyTaskNameOpertaion()throws Exception{
		EmployeeDailyTaskDao employeeDailyTaskDao =new EmployeeDailyTaskDao();
		employeeDailyTaskDao.getUserTaskName();
		displayOperation();
		
	}
	

}
