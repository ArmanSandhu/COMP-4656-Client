package ca.bcit.comp4656.assign2.webapp.presentation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ca.bcit.comp4656.assign2.webapp.domain.EmployeeResponse;

public class FindEmployeeCommand extends AbstractCommand {

	Logger logger = Logger.getLogger(FindEmployeeCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("Client: Trying to find Employee!");
		String empId = request.getParameter("id");
		EmployeeResponse empResp = employeeServices.findEmployeeById(empId);
		
		if(empResp.getEmployee() != null) {
			request.setAttribute("foundEmp", empResp.getEmployee());
		}
		request.setAttribute("findResponseCode", empResp.getResponseCode());
	}

}
