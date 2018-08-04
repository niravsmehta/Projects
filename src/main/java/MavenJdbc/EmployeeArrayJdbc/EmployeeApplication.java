package MavenJdbc.EmployeeArrayJdbc;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Scanner;
import Jdbc.EmployeeJdbc.*;
import static Jdbc.EmployeeJdbc.EmployeeUtil.chooseOperation;
import static Jdbc.EmployeeJdbc.EmployeeUtil.readEmployee;

@SuppressWarnings("unused")
public class EmployeeApplication {

	private EmployeeOperations eco = null;
	private EmployeeArrayDao eo = null;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		int i;

		EmployeeApplication employeeApplication = new EmployeeApplication();
		System.out.println("************trng.Employee System**********");
		employeeApplication.init();

		int choice;
		do {
			choice = chooseOperation();

			switch (choice) {
			case 1:
				employeeApplication.add();
				break;
			case 2:
				employeeApplication.display();
				break;
			case 3:
				employeeApplication.displayAll();
				break;
			case 4:
				employeeApplication.update();
				break;
			case 5:
				employeeApplication.delete();
				break;
			case 6:
				employeeApplication.averageSalary();
				break;
			case 7:
				employeeApplication.groupByGender();
				break;
			case 8:
				employeeApplication.sorting();
				break;
			case 9:
				break;
			default:
				System.out.println("Invalid Choice");
			}

		} while (choice != 9);

	}

	private void delete() throws SQLException {
		System.out.println("Enter the employee id to delete :");
		int empId = scanner.nextInt();
		boolean flag = eco.deleteEmployee(empId);
		if (flag) {
			System.out.println("Deleted successfully");
		} else {
			try {
				throw new EmployeeNotFoundException("Employee Not found");
			} catch (EmployeeNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		}

	}

	private void update() throws SQLException {
		System.out.println("Enter Employees data for update:");
		Employee employee = readEmployee();
		eco.updateEmployee(employee);
	}

	private void displayAll() throws SQLException {
		System.out.println("Employees in the system are :");
		Employee[] employees = eco.findAll();
		for (Employee e : employees) {
			System.out.println(e);

		}
	}

	private void display() throws SQLException {
		System.out.println("Enter the employee id to view employee info :");
		int empId = scanner.nextInt();
		Employee employee = eco.findEmployee(empId);
		System.out.println(employee);
	}

	private void add() throws SQLException {
		Employee employee = readEmployee();
		eco.createEmployee(employee);
	}

	private void init() {
		eco = new EmployeeArrayDao();
		eo = new EmployeeArrayDao();
		eco = eo;

	}

	private void groupByGender() throws SQLException {
		eco.groupByGender();

	}

	private void averageSalary() throws SQLException {
		double averageSalary = 0;
		averageSalary = eco.averageSalary();
		System.out.println("Average Salary: " + averageSalary);

	}

	private void sorting() throws SQLException {
		System.out.println("1. Sort by Name and Salary  .2 sort by Department  3. sort by id");
		int key = scanner.nextInt();
		switch (key) {
		case 1:
			eco.sortByNameAndSalary();
			break;
		case 2:
			eco.sortByDepartmentNo();
			break;
		case 3:
			eco.sortById();
			break;

		default:
			break;
		}
	}

}
