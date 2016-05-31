package my.vaadin.application;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import my.vaadin.application.view.LoginView;
import my.vaadin.application.view.MainView;
import my.vaadin.application.view.RegisterView;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */

@Theme("valo")
@Widgetset("my.vaadin.application.MyAppWidgetset")
public class NavigatorUI extends UI {
	
	Navigator nav;
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
       getPage().setTitle("Navigation Example");
       
       Label label = new Label("Label!");
       
       VerticalLayout layout = new VerticalLayout();
       layout.addComponent(label);
       setContent(layout);
       
       nav = new Navigator(this, this);
       nav.addView("login", new LoginView(nav));
       nav.addView("register", new RegisterView(nav));	
       nav.addView("main", new MainView(nav));
       
       nav.navigateTo("main");
       
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
    public static class NavigatorUIServlet extends VaadinServlet {
    }

}
