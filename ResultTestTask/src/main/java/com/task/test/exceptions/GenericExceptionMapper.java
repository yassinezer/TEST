package com.task.test.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

/** this class called when any exception has met in the jax-rs */
@Provider
public class GenericExceptionMapper implements	ExceptionMapper<Throwable>
{
	private Logger logger = Logger.getLogger(GenericExceptionMapper.class);

	@Override
	public Response toResponse(Throwable excep) 
	{
		logger.error("invalid url has been detected");
		return Response.status(Status.NOT_FOUND)
				.entity(" make sure you have : "
				+ "\n 1 - an id for department exists in the database "
				+ "\n 2 - an id for Employee exists in the database "
				+ "\n 2 - the syntaxe of the url must be begin with : /test/webservices/ ")
				.build();
	}
	
}