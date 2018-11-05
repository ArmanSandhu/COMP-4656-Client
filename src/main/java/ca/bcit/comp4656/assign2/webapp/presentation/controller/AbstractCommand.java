package ca.bcit.comp4656.assign2.webapp.presentation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.bcit.comp4656.assign2.webapp.services.EmployeeServices;
import ca.bcit.comp4656.assign2.webapp.services.EmployeeServicesImpl;

public abstract class AbstractCommand implements Command {
	
	protected EmployeeServices employeeServices;

	public AbstractCommand() {
		employeeServices = new EmployeeServicesImpl();
	}

	public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
