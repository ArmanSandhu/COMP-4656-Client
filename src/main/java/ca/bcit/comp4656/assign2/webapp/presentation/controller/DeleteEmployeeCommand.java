package ca.bcit.comp4656.assign2.webapp.presentation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ca.bcit.comp4656.assign2.webapp.domain.ResponseCode;

public class DeleteEmployeeCommand extends AbstractCommand {

	Logger logger = Logger.getLogger(DeleteEmployeeCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("Client: Attempting to delete an Employee");
		String empId = request.getParameter("id");
		ResponseCode responseCode = employeeServices.deleteEmployee(empId);
		request.getSession().setAttribute("employees", employeeServices.getEmployeeList());
		request.setAttribute("delResponseCode", responseCode);
	}

}
