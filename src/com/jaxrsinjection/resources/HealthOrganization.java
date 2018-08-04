package com.jaxrsinjection.resources;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.StreamingOutput;

@Path("/hospital/{location}")
public class HealthOrganization {
	@PathParam("location")
	private PathSegment location;
	@GET
	@Path("/searchdoctor")
	@Produces(MediaType.APPLICATION_XML)
	public StreamingOutput searchHospital(@QueryParam("dept") String dept,
			@QueryParam("qualification") @DefaultValue("qualification") String qualification) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<parameters>").append("<pathparam>").append(location.getPath()).append("</pathparam>")
				.append("<queryparam>").append(qualification).append("</queryparam>");
		MultivaluedMap<String, String> mutlimap = location.getMatrixParameters();
		for (String matrixparamname : mutlimap.keySet()) {
			List<String> matrixval = mutlimap.get(matrixparamname);
			for (String values : matrixval) {
				buffer.append("<MatrixParam>").append(values).append("</MatrixParam>");
			}
		}
		buffer.append("</parameters>");
		StreamingOutput output = new StreamingOutput() {

			@Override
			public void write(OutputStream os) throws IOException, WebApplicationException {
				// TODO Auto-generated method stub
				os.write(buffer.toString().getBytes());
				os.close();
			}
		};
		return output;
	}

}
