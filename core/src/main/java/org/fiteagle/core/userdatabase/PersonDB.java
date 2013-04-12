package org.fiteagle.core.userdatabase;

import java.sql.SQLException;



public interface PersonDB {

	public void add(Person p) throws DuplicateUIDException, SQLException;
	
	public void delete(String UID) throws SQLException;
	public void delete(Person p) throws SQLException;
	
	public void update(Person p) throws RecordNotFoundException, SQLException;
	public void addKey(Person p, String key) throws RecordNotFoundException, SQLException;
	
	public Person get(String UID) throws RecordNotFoundException, SQLException;
	public Person get(Person p) throws RecordNotFoundException, SQLException;
	
	public int getSize() throws SQLException;

		
	public static class RecordNotFoundException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2315125279537534064L;
	}
	
	public class DuplicateUIDException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7242105025265481986L;
		
	}

}