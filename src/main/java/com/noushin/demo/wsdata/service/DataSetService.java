package com.noushin.demo.wsdata.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.noushin.demo.wsdata.model.DataSet;

/**
 * This service demonstrates how to write web services that manage interactions with a database.
 * In this example, data is received by the service and persisted to a MySQL database. 
 * The system can also retrieve persisted data.
 * 
 * @author nbashir
 *
 */
@Path("/set")
public interface DataSetService {
	/*
	 *  curl  -i -v -X GET -H "Content-type: application/json" -H "Accept: application/json" http://localhost:8080/wsdata/services/set/ping
	 */
	@GET
    @Produces({ MediaType.APPLICATION_JSON })
	@Path("/ping")
	public String ping();

	/*
     * curl  -i -v -X GET -H "Accept: application/json" http://localhost:8080/wsdata/services/set/{id}
     * return: {"id":"1","content":"abc def ghi jkl"}
     */
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    public DataSet getContent(@PathParam("id") Integer id);
	
	
	/*
     * curl  -i -v -X POST -H "Content-type: application/json" -H "Accept: application/json" http://localhost:8080/wsdata/services/set/ -d @sample_data.json
     * return: 5
     */
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/")
    public Integer saveContent(DataSet set);
}
