package org.fiteagle.core.aaa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import junit.framework.Assert;

import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserDBManager;
import org.fiteagle.core.userdatabase.UserPublicKey;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CertificateAuthorityTest {
  CertificateAuthority CA ;
  UserDBManager um;
  User dummyUser ;
  @Before
  public void setUp() throws Exception {
    CA =CertificateAuthority.getInstance();
    um = UserDBManager.getInstance();
    dummyUser = new User("dummy@localhost", "dummy", "dummy", "dummy@dummy.org", "dummyAffiliation", "blub", new ArrayList<UserPublicKey>());
  }
  
 
 
  @Test
  public void testCreateCertificateForDummyUser() throws Exception {
    KeyPair keypair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
    X509Certificate userCert =   CA.createCertificate(dummyUser, keypair.getPublic());
    Assert.assertTrue(userCert != null);   
  }
  
//  @Test 
//  public void testWriteCertificate() throws Exception{
//    X509Certificate cert = CA.createCertificate(dummyUser);
//    CA.saveCertificate("acceptedCert.pem", cert);
//  }
//  
  
}
