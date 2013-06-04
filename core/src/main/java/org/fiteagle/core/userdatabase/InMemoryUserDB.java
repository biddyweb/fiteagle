package org.fiteagle.core.userdatabase;

import java.util.HashMap;

public class InMemoryUserDB implements UserDB {

	private HashMap<String, User> users;

	public InMemoryUserDB(){
		users = new HashMap<String, User>();
	}

	public int getNumberOfUsers(){
		return users.size();
	}

	@Override
	public void add(User u) throws DuplicateUsernameException {
		if(users.get(u.getUsername()) != null){
			throw new DuplicateUsernameException();
		}
		else{
			users.put(u.getUsername(), u);
		}
	}	

	@Override
	public void delete(String username){
		users.remove(username);			
	}

	@Override
	public void delete(User u){
		delete(u.getUsername());
	}

	@Override
	public void update(User u) throws RecordNotFoundException {
		if(users.get(u.getUsername()) == null)
			throw new RecordNotFoundException();
		else
			users.put(u.getUsername(), u);		
	}

	@Override
	public User get(String username) throws RecordNotFoundException {
		if(users.get(username) == null)
			throw new RecordNotFoundException();
		return users.get(username);		
	}

	@Override
	public User get(User u) throws RecordNotFoundException {
		return get(u.getUsername());		
	}

	@Override
	public void addKey(String username, String key) throws RecordNotFoundException {
	  User u;
		if((u=users.get(username)) == null)
			throw new RecordNotFoundException();
		u.addPublicKey(key);
		update(u);
	}
	
	@Override
	public void deleteAllEntries(){
		users = new HashMap<String, User>();
	}
}