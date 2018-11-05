package ca.bcit.comp4656.assign2.webapp.presentation.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ca.bcit.comp4656.assign2.webapp.domain.Employee;
import ca.bcit.comp4656.assign2.webapp.domain.ResponseCode;

public class AddEmployeesCommand extends AbstractCommand {

	Logger logger = Logger.getLogger(AddEmployeesCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("Client: Attempting to add new Employee!");
		ResponseCode responseCode = new ResponseCode();
		Employee employee = new Employee();
		employee.setId(request.getParameter("id"));
		employee.setFirstName(request.getParameter("fname"));
		employee.setLastName(request.getParameter("lname"));
		try {
			employee.setDob(getDob(request.getParameter("dob")));
		} catch (ParseException pe) {
			responseCode.setCode("902");
			responseCode.setDesc("Invalid Date Format provided!");
		}
		responseCode = employeeServices.addEmployee(employee);
		request.getSession().setAttribute("employees", employeeServices.getEmployeeList());
		request.setAttribute("addResponseCode", responseCode);
	}
	
	private Date getDob(String dob) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		return format.parse(dob);
	}

}
