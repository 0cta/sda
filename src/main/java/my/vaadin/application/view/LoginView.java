package my.vaadin.application.view;

import java.security.MessageDigest;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout implements View {

	public LoginView(Navigator nav) {
		// TODO Auto-generated constructor stub
		final TextField email_field = new TextField("E-Mail");
		final PasswordField password_field = new PasswordField("Password");
		final Button login_btn = new Button("Login");
		final Button register_btn = new Button("Create Account");
		
		login_btn.addClickListener(e -> {
			String email = new String(email_field.getValue());
			String password = new String(password_field.getValue());

			// Prüfen, ob authorisierter User
			// Pseudo Redirect auf Overview-Page

		});
		
		register_btn.addClickListener(e -> {
			nav.navigateTo("register");
		});
		
		final HorizontalLayout layout_btn = new HorizontalLayout();
		layout_btn.addComponents(login_btn, register_btn);
		
		addComponents(email_field, password_field, layout_btn);
		setSizeFull();
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}
	
}
