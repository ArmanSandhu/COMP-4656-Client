package ca.bcit.comp4656.assign2.webapp.presentation.controller;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ca.bcit.comp4656.assign2.webapp.domain.Employee;

public class GetAllEmployeesCommand extends AbstractCommand {

	Logger logger = Logger.getLogger(GetAllEmployeesCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Employee> employees = null;
		try {
			employees = employeeServices.getEmployeeList();
		} catch (Exception e) {
			logger.error("Couldn't retrieve list of Employees");
			logger.error(e.getMessage());
		}
		if(employees != null && employees.size() > 0) {
			request.getSession().setAttribute("employees", employees);
		} else {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/JSPs/noEmployees.jsp");
			dispatcher.forward(request, response);
		}
	}

}
