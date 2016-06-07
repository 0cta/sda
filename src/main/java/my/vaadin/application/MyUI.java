package my.vaadin.application;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import application.view.MyLogin;
import application.view.MyOverview;
import application.view.MyRegister;
import application.view.MyUpload;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SuppressWarnings("serial")
@Theme("mytheme")
@Widgetset("my.vaadin.application.MyAppWidgetset")
public class MyUI extends UI {

	public static final String PERSISTENCE_UNIT = "my.vaadin.application";
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	Navigator nav = new Navigator(this, this);
    	nav.addView(MyLogin.NAME, MyLogin.class);
    	nav.addView(MyRegister.NAME, MyRegister.class);
    	nav.addView(MyOverview.NAME, MyOverview.class);
    	nav.addView(MyUpload.NAME, MyUpload.class);
    	
    	nav.addViewChangeListener(new ViewChangeListener() {

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				// TODO Auto-generated method stub
				boolean isLoggedIn = getSession().getAttribute("user") != null;
				boolean isLoginView = event.getNewView() instanceof MyLogin;
				boolean isRegisterView = event.getNewView() instanceof MyRegister;
				
				if(!isLoggedIn) {
					if(isLoginView) {
						return true;
					} else if(isRegisterView) {
						return true;
					} else {
					// redirect user to login view, as he's not logged in already
					getNavigator().navigateTo(event.getNewView().toString());
					return false;
					}
				} else if (isLoggedIn && (isLoginView || isRegisterView)) {
					// prevent the user from accessing the login view when already logged in
					return false;
				}
				
				return true;
			}

			@Override
			public void afterViewChange(ViewChangeEvent event) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
    	
    	nav.navigateTo(MyLogin.NAME);
    	
    }
    
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
