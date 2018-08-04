package com.jaxrsinjection.context.cookie.header;

import java.util.List;
import java.util.Map;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

import org.apache.catalina.valves.rewrite.Substitution.RewriteRuleBackReferenceElement;

@Path("/uber")
public class Uber {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/ride/{source}/{destination}/{paymenttype}")

	public String findRide(@Context UriInfo uriinfo) {
		MultivaluedMap<String, String> multimap=uriinfo.getPathParameters();
		StringBuffer buffer=new StringBuffer();
		buffer.append("PathParameter values are=====").append(extractParameters(multimap));
		List<PathSegment> matrixparams=uriinfo.getPathSegments();
		buffer.append("Matrix Params are===");
		for(PathSegment pathSegment:matrixparams)
		{
			
			buffer.append(extractParameters(pathSegment.getMatrixParameters()));
		}
		MultivaluedMap<String, String> queryparams=uriinfo.getQueryParameters();
		
		buffer.append("Query params are====");
		buffer.append(queryparams);
		return buffer.toString();

	}
	public String extractParameters(MultivaluedMap<String, String> multiMap)
	{
		StringBuffer buffer=new StringBuffer();
				
		for(String name:multiMap.keySet())
		{
			buffer.append("==paramname==").append(name).append("==");
			buffer.append(multiMap.get(name));
		}
		return buffer.toString();
	}
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/register")		
	public String register(@HeaderParam("userName") String userName,@CookieParam("appId") String appId)
{
	return "Header iss=="+userName+"==cookie param iss==="+appId;
}
	
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/headers")		
	public String getHeaders(@Context HttpHeaders headers)
{
		StringBuffer buffer=new StringBuffer();
		buffer.append("Request Headers are===");
		buffer.append(headers.getRequestHeaders());
		buffer.append("Cookies are==");
		Map<String, Cookie> cookies= headers.getCookies();for(String name:cookies.keySet())
		{
			Cookie cookie=cookies.get(name);
			buffer.append(name).append("===").append(cookie.getValue());
		}
		
		return extractParameters(headers.getRequestHeaders());
}
	

	
}
