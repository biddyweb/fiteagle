package org.fiteagle.adapter.stopwatch;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fiteagle.adapter.common.AdapterConfiguration;
import org.fiteagle.adapter.common.Instantiable;
//import org.fiteagle.adapter.common.InMemoryResourceDatabase;
import org.fiteagle.adapter.common.ResourceAdapter;


public class StopwatchAdapter extends ResourceAdapter implements Instantiable {

	private static boolean loaded = false;
	private transient boolean runningState = false;

	public StopwatchAdapter(){
	  super();
	  this.setType("org.fiteagle.adapter.stopwatch.StopwatchAdapter");
		this.create();
	}
	public boolean isRunning() {
		return runningState;
	}

	@Override
	public void stop() {
		this.runningState = false;
	}

	@Override
	public void start() {
		this.runningState=true;
		
	}

	@Override
	public void create() {
	  
	  //adding some test data to specify stopwatch
	  
	  HashMap<String, Object> props = this.getProperties();
	  props.put("format", "SimpleDateFormat");
	
	}

	

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}
	
	public static List<ResourceAdapter> getJavaInstances() {
//		List<ResourceAdapter> resourceAdapters = new ArrayList<ResourceAdapter>();
//	    ResourceAdapter dummyResourceAdapter = new StopwatchAdapter();
//	    dummyResourceAdapter.setExclusive(false);
//	    dummyResourceAdapter.setAvailable(true);
//	    resourceAdapters.add(dummyResourceAdapter);
//		
//		return resourceAdapters;
		return new ArrayList<ResourceAdapter>();
	}
	@Override
	public boolean isLoaded() {
		return loaded;
	}
	@Override
	public void setLoaded(boolean loaded) {
		this.loaded=loaded;
	}
	@Override
	public void configure(AdapterConfiguration configuration) {
		//TODO do something
	}
	@Override
	public void checkStatus() {
		// TODO Auto-generated method stub
		
	}


}
