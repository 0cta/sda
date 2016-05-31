package my.vaadin.application.service;

public class SessionService {

	private static SessionService instance = null;
	
	protected SessionService(){};
	
	public static SessionService getInstance() {
		if(instance == null) {
			instance = new SessionService();
		}
		return instance;
	}
	
	public static void login(String email, String password) {
		return;
	}
	
	public static void timeout() {
		
	}
	
	public static boolean isAuth() {
		
		return true;
	}
	
}
