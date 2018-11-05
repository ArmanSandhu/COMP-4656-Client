package ca.bcit.comp4656.assign2.webapp.services;

import java.util.List;

import ca.bcit.comp4656.assign2.webapp.domain.Employee;
import ca.bcit.comp4656.assign2.webapp.domain.EmployeeResponse;
import ca.bcit.comp4656.assign2.webapp.domain.ResponseCode;

public interface EmployeeServices {

	public List<Employee> getEmployeeList();
	public EmployeeResponse findEmployeeById(String id);
	public ResponseCode addEmployee(Employee e);
	public ResponseCode deleteEmployee(String id);
}
