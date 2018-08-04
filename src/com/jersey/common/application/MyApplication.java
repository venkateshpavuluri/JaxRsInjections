package com.jersey.common.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class MyApplication extends Application{
	Set<Object>  set;
	
	
	
	/*public Set<Object> getSingletons()
	{
		return set;
		
	}*/

}
