package org.fiteagle.interactors.sfa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.fiteagle.interactors.sfa.allocate.AllocateResult;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Authorization;
import org.fiteagle.interactors.sfa.common.Credentials;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniCompressedOption;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.delete.DeleteOptions;
import org.fiteagle.interactors.sfa.delete.DeleteResult;
import org.fiteagle.interactors.sfa.describe.DescribeOptions;
import org.fiteagle.interactors.sfa.describe.DescribeResult;
import org.fiteagle.interactors.sfa.getversion.GeniAPIVersion;
import org.fiteagle.interactors.sfa.getversion.GeniRequestRSpecVersions;
import org.fiteagle.interactors.sfa.getversion.GetVersionResult;
import org.fiteagle.interactors.sfa.getversion.GetVersionValue;
import org.fiteagle.interactors.sfa.listresources.ListResourceOptions;
import org.fiteagle.interactors.sfa.listresources.ListResourcesResult;
import org.fiteagle.interactors.sfa.provision.ProvisionResult;
import org.fiteagle.interactors.sfa.rspec.ObjectFactory;
import org.fiteagle.interactors.sfa.rspec.Property;
import org.fiteagle.interactors.sfa.rspec.RSpecContents;
import org.fiteagle.interactors.sfa.rspec.Resource;
import org.fiteagle.interactors.sfa.status.StatusOptions;
import org.fiteagle.interactors.sfa.status.StatusResult;
import org.junit.Before;
import org.junit.Test;

public class SFAInteractorTest {
	public static final String EXPECTED_TYPE = "GENI";
	public static final int EXPECTED_VERSION = 2;
	public static final String EXPECTED_API_URL = "https://fiteagle.org/api/sfa/v2/xmlrpc/am";

	private transient ISFA sfaInteractor;
	
	

	@Before
	public void setUp() {
		this.sfaInteractor = new SFAInteractor_v3();
	}

	@Test
	public void testGetVersion() throws IOException {
		final GetVersionResult getVersionResult = this.getGeniVersion();
		this.validateGeniCode(getVersionResult);

		final GetVersionValue value = (GetVersionValue) this
				.getGeniValue(getVersionResult);

		// TODO to be tested on delivery layer =>
		// this.validateGetVersionGeniValue(value);
		// TODO to be tested on delivery layer =>
		// this.valudateGeniAPIs(value);
		this.validateRSpecVersions(value);
	}

	@Test
	public void testListResources() throws IOException {
		ListResourceOptions options = createMinimalListResourceOptions("GENI",
				"3");
		final ListResourcesResult listResourcesResult = this.sfaInteractor
				.listResources(getListCredentials(), options);
		Assert.assertEquals(0, listResourcesResult.getCode().getGeni_code());

	}
	
	@Test
  public void testListResourcesWithNode() throws IOException {
    ListResourceOptions options = createMinimalListResourceOptions("GENI",
        "3");
    final ListResourcesResult listResourcesResult = this.sfaInteractor
        .listResources(getListCredentials(), options);
    
    String listResourcesValue = (String)listResourcesResult.getValue();
    Assert.assertEquals(0, listResourcesResult.getCode().getGeni_code());
    Assert.assertTrue(listResourcesValue.contains("node"));
    Assert.assertTrue(listResourcesValue.contains("Germany"));
    Assert.assertTrue(listResourcesValue.contains("demolaptop"));

  }

	@Test
	public void testCombinedGetVersionAndListResources() throws IOException {
		final GetVersionResult getVersionResult = this.getGeniVersion();
		ListResourceOptions options = createMinimalListResourceOptions(
				getVersionResult.getValue().getGeni_ad_rspec_versions().get(0)
						.getType(), getVersionResult.getValue()
						.getGeni_ad_rspec_versions().get(0).getVersion());

		final ListResourcesResult listResourcesResult = this.sfaInteractor
				.listResources(getListCredentials(), options);
		Assert.assertEquals(0, listResourcesResult.getCode().getGeni_code());

	}
	
	@Test
	public void testDescribe() throws IOException {
		DescribeOptions options;// = createMinimalListResourceOptions("GENI", "3");
		ArrayList<String> urns = new ArrayList<String>();
		urns.add("urn:publicid:IDN+fiteagletest+slice+testtest");
		RSpecContents testRSpec = getTestRspec();
		testRSpec.setType("request");
    
		this.sfaInteractor.allocate(urns.get(0), getListCredentials(), testRSpec, null);
		
		DescribeResult describeResult = this.sfaInteractor.describe(urns, getListCredentials(), createTestDescribeOptions("GENI", "3", false));
		Assert.assertEquals(0, describeResult.getCode().getGeni_code());
	}
	
	@Test
  public void testAllocate() throws IOException {
    ArrayList<String> urns = new ArrayList<String>();
    urns.add("urn:publicid:IDN+fiteagletest+slice+testtest");
    RSpecContents testRSpec = getTestRspec();
    testRSpec.setType("request");
    
    AllocateResult allocateResult = this.sfaInteractor.allocate(urns.get(0), getListCredentials(), testRSpec, null);
    
    Assert.assertEquals(0, allocateResult.getCode().getGeni_code());
  }
	
	@Test
  public void testProvision() throws IOException {
    ArrayList<String> urns = new ArrayList<String>();
    urns.add("urn:publicid:IDN+fiteagletest+slice+testtest");
    this.testAllocate();
    
    ProvisionResult provisionResult = this.sfaInteractor.provision(urns, getListCredentials(), null);
    
    Assert.assertEquals(0, provisionResult.getCode().getGeni_code());
  }
	
	@Test
  public void testStatus() throws IOException {
    ArrayList<String> urns = new ArrayList<String>();
    urns.add("urn:publicid:IDN+fiteagletest+slice+testtest");
    this.testAllocate();
    
    DescribeResult describeResult = this.sfaInteractor.describe(urns, getListCredentials(), createTestDescribeOptions("GENI", "3", false));
    
    
    ArrayList<String> statusUrns= new ArrayList<String>();
    statusUrns.add(describeResult.getValue().getGeni_slivers().get(0).getGeni_sliver_urn());
    StatusResult statusResult = this.sfaInteractor.status(statusUrns, getListCredentials(), new StatusOptions());
    
    Assert.assertEquals(0, statusResult.getCode().getGeni_code());
  }
	
	@Test
  public void testDelete() throws IOException {
    ArrayList<String> urns = new ArrayList<String>();
    urns.add("urn:publicid:IDN+fiteagletest+slice+testtest");
    this.testAllocate();
    
    DeleteResult deleteResult = this.sfaInteractor.delete(urns, getListCredentials(), new DeleteOptions());
    
    Assert.assertEquals(0, deleteResult.getCode().getGeni_code());
  }

	
	@Test
	public void testInvalidListResourcesVersion() throws IOException {

		ListResourceOptions options = createMinimalListResourceOptions("GENI",
				"2");

		ListResourcesResult listResourcesResult = this.sfaInteractor
				.listResources(getListCredentials(), options);
		Assert.assertEquals(listResourcesResult.getCode().retrieveGeni_code(),
				GENI_CodeEnum.BADVERSION);

	}
	
	
	
	
	private DescribeOptions createTestDescribeOptions(String type, String version, boolean compressed) {
		DescribeOptions options = new DescribeOptions();
		GeniCompressedOption geni_compressed = new GeniCompressedOption(compressed);
		options.setGeni_compressed(geni_compressed);
		
		Geni_RSpec_Version geni_rspec_version = new Geni_RSpec_Version();
		geni_rspec_version.setType(type);
		geni_rspec_version.setVersion(version);
		options.setGeni_rspec_version(geni_rspec_version);
		return options;
	}

	
	private ListResourceOptions createMinimalListResourceOptions(String type,
			String version) {
		ListResourceOptions options = new ListResourceOptions();
		Geni_RSpec_Version geni_rspec_version = new Geni_RSpec_Version();
		geni_rspec_version.setType(type);
		geni_rspec_version.setVersion(version);
		options.setGeni_rspec_version(geni_rspec_version);
		return options;
	}

	@SuppressWarnings("unchecked")
	private void validateRSpecVersions(final GetVersionValue value) {
		final List<GeniRequestRSpecVersions> request_rspec_versions = value
				.getGeni_request_rspec_versions();
		Assert.assertNotNull(request_rspec_versions);
		Assert.assertFalse(request_rspec_versions.isEmpty());
		Assert.assertEquals(request_rspec_versions.get(0).getType(),
				SFAInteractorTest.EXPECTED_TYPE);
	}

	@SuppressWarnings("unchecked")
	private void valudateGetVersionGeniAPIs(final GetVersionValue value) {
		final Map<String, String> api_versions = value
				.getGeni_api_versions();
		Assert.assertNotNull(api_versions);
		// Assert.assertEquals(SFAInteractorTest.EXPECTED_API_URL, api_versions.
		// .get(String.valueOf(SFAInteractorTest.EXPECTED_VERSION)));
	}

	@SuppressWarnings("unchecked")
	private Object getGeniValue(final AMResult amResult) {
		final Object value = amResult.getValue();
		return value;
	}

	private void validateGetVersionGeniValue(final GetVersionValue value) {
		Assert.assertNotNull(value);

		int resultedVersion = value.getGeni_api();

		Assert.assertEquals(SFAInteractorTest.EXPECTED_VERSION, Integer
				.valueOf(resultedVersion).intValue());
	}

	private void validateGeniCode(final GetVersionResult getVersionResult) {
		final AMCode code = getVersionResult.getCode();

		Assert.assertNotNull(code);
		Assert.assertEquals(ISFA.ERRORCODE_SUCCESS, code.getGeni_code());
	}

	private GetVersionResult getGeniVersion() throws IOException {
		final GetVersionResult version = this.sfaInteractor.getVersion();
		Assert.assertNotNull(version);
		// version is to be supplied by the according delivery mechanism
		// Assert.assertEquals(SFAInteractorTest.EXPECTED_VERSION,
		// version.getGeniApiVersion());
		return version;
	}

	private ListCredentials getListCredentials() {
		Credentials credentials = new Credentials();
		credentials.setGeni_type(Authorization.GENI_TYPE);
		credentials.setGeni_version(Authorization.GENI_VERSION);
		ListCredentials listCredentials = new ListCredentials();
		listCredentials.getCredentialsList().add(credentials);
		return listCredentials;
	}
	
	private RSpecContents getTestRspec(){
	  
	  
	  RSpecContents testRSpec = new RSpecContents();
    List<Object> fiteagleResources = testRSpec .getAnyOrNodeOrLink();
    Resource fiteagleResource1 = new Resource();
    List<Property> properties = fiteagleResource1.getProperty();
    Property idProperty= new Property();
//    idProperty.setName("id");
//    idProperty.setValue("TestId");
    Property typeProperty= new Property();
    typeProperty.setName("type");
    typeProperty.setValue("org.fiteagle.adapter.stopwatch.StopwatchAdapter");
    
//    properties.add(idProperty);
    properties.add(typeProperty);
    fiteagleResources.add(new ObjectFactory().createResource(fiteagleResource1));
    return testRSpec;
	}

}
