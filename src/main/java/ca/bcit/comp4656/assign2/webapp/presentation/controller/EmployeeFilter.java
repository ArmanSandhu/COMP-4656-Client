package ca.bcit.comp4656.assign2.webapp.presentation.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class EmployeeFilter implements Filter {

	private static final String COMMANDS_FILE = "/WEB-INF/classes/commands.properties";
	private static final String SERVICE_INIT_ACTION = "service.getEmployees";
	
	private Logger logger = Logger.getLogger(EmployeeFilter.class);
	private static Properties commandsProps;
	
	@Override
	public void init(FilterConfig filterConfig ) throws ServletException
	{
		
		commandsProps = new Properties();
		try 
		{
			commandsProps.load( new FileInputStream(filterConfig.getServletContext().getRealPath( COMMANDS_FILE ) ) );
			logger.info( "Command properties loaded!");
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			logger.fatal("Can't find " + COMMANDS_FILE, e );
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			logger.fatal( "Problem loading " + COMMANDS_FILE , e );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			logger.fatal( "something went wrong " + e );
		}
	};
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		
		String action = request.getParameter("action");
		Command c;
		try {
			c = getCommand(action);
			c.execute((HttpServletRequest)request, (HttpServletResponse)response);
		} catch (Exception e) {
			logger.fatal("something went wrong in doFilter " + e);
		}
		filterChain.doFilter(request, response);
	}
	
	
	private Command getCommand(String action) throws Exception 
	{

		/* First time in, call initialisation command */
		if ( action == null ) 
		{
			action = SERVICE_INIT_ACTION;
		}
		
		/* Simple factory for the commands */
		String clazz = commandsProps.getProperty( action );
		Command command  = (Command) Class.forName( clazz ).newInstance();
		
		return command;	
	}

	@Override
	public void destroy() {}
}
