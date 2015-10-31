package com.spock.rsservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.spock.exceptions.NoValidKeyFoundException;
import com.spock.opearations.HouseService;

@Path("/api")
public class ServiceClass {
	HouseService objService = new HouseService();
	
	@GET	
	@Path("/getLockKey")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getLockKey() {
		String key = null;
		try {
			key = objService.getLockKey();
		} catch (NoValidKeyFoundException e) {
			return Response.status(404).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(key).build();
	}
	
	@GET	
	@Path("/unlockHouse")
	@Produces(MediaType.TEXT_PLAIN)
	public Response unlockHouse(@QueryParam("key") String key) {
		String unlockStatus = objService.unlockHouse(key);
		return Response.status(200).entity(unlockStatus).build();
	}
	
	@GET	
	@Path("/lockHouse")
	@Produces(MediaType.TEXT_PLAIN)
	public Response lockHouse() {
		String lockStatus = objService.lockHouse();
		return Response.status(200).entity(lockStatus).build();
	}
	
	@GET	
	@Path("/updateAllKeys")
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateKeys() {
		objService.updateExpiredKeys();
		return Response.status(200).entity("").build();
	}
	
	@GET	
	@Path("/pollRequest")
	@Produces(MediaType.TEXT_PLAIN)
	public Response pollRequest() {
		String status = objService.pollRequest();
		return Response.status(200).entity(status).build();
	}
	
	@GET	
	@Path("/putRequest")
	@Produces(MediaType.TEXT_PLAIN)
	public Response putRequest() {
		String reqStatus = objService.putRequest();
		return Response.status(200).entity(reqStatus).build();
	}
}