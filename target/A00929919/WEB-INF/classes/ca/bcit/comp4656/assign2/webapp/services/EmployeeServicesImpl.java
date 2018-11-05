package ca.bcit.comp4656.assign2.webapp.services;

import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import ca.bcit.comp4656.assign2.webapp.domain.Employee;
import ca.bcit.comp4656.assign2.webapp.domain.EmployeeResponse;
import ca.bcit.comp4656.assign2.webapp.domain.ResponseCode;

public class EmployeeServicesImpl implements EmployeeServices {
	
	ClientConfig config;
	Client client;
	WebResource service;
	
	private static final String GET_EMPLOYEES_URL = "http://localhost:8080/A00929919ws/rest/employees";
	private static final String FIND_EMPLOYEE_BY_ID_URL = "http://localhost:8080/A00929919ws/rest/employees/";
	private static final String ADD_EMPLOYEE_URL = "http://localhost:8080/A00929919ws/rest/employees/add";
	private static final String DELETE_EMPLOYEE_URL = "http://localhost:8080/A00929919ws/rest/employees/delete/";
	
	public EmployeeServicesImpl() {
		config = new DefaultClientConfig();
		client = Client.create(config);;
	}

	@Override
	public List<Employee> getEmployeeList() {
		service = client.resource(GET_EMPLOYEES_URL);
		List<Employee> employees = service.get(new GenericType<List<Employee>>() {});
		return employees;
	}

	@Override
	public EmployeeResponse findEmployeeById(String id) {
		service = client.resource(FIND_EMPLOYEE_BY_ID_URL + id);
		ClientResponse resp = service.type(MediaType.APPLICATION_XML).get(ClientResponse.class);
		EmployeeResponse empResp = resp.getEntity(EmployeeResponse.class);
		return empResp;
	}

	@Override
	public ResponseCode addEmployee(Employee e) {
		service = client.resource(ADD_EMPLOYEE_URL);
		ClientResponse resp = service.accept(MediaType.APPLICATION_XML).type(MediaType.APPLICATION_XML).put(ClientResponse.class, e);
		ResponseCode responseCode = resp.getEntity(ResponseCode.class);
		return responseCode;
	}

	@Override
	public ResponseCode deleteEmployee(String id) {
		service = client.resource(DELETE_EMPLOYEE_URL + id);
		ClientResponse resp = service.type(MediaType.APPLICATION_XML).delete(ClientResponse.class);
		ResponseCode responseCode = resp.getEntity(ResponseCode.class);
		return responseCode;
	}
}
