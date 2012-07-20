package org.teagle.clients.cli;

import java.net.MalformedURLException;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;

import teagle.vct.model.ResourceInstance;
import teagle.vct.model.Vct;

public class TeagleClientTest {

	private TeagleClient client;
	
	@Before public void setup() throws MalformedURLException {
		String repoUrl = "http://localhost:9080/repository/rest";
		String reqUrl = "http://localhost:9000/reqproc";
		String user = "root";
		String pwd = "root";
		
		//todo: inject a local mock - do not test using external servers
		this.client = new TeagleClient(user, pwd, reqUrl, repoUrl);
	}
	
	//@Test
	public void testGetResourceInstances() {
		Collection<ResourceInstance> actual = this.client.getResourceInstances();
		Assert.assertFalse(actual.isEmpty());
	}

	//@Test
	public void testGetVCTs() {
		Collection<Vct> actual = this.client.getVCTs();
		Assert.assertFalse(actual.isEmpty());
	}

}