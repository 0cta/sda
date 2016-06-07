package application.view;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import application.data.User;
import my.vaadin.application.MyUI;

@SuppressWarnings("serial")
public class MyLogin extends VerticalLayout implements View, Button.ClickListener {
	
	public static final String NAME = "MyLogin";
	
	private final Label caption;
	private final TextField email;
	private final PasswordField password;
	private final Button login_btn;
	private final Button register_btn;
	
	public MyLogin() {
		setSizeFull();
		FormLayout form = new FormLayout();
		
		caption = new Label("Login");
		form.addComponent(caption);
		
		// Email-TextField
		email = new TextField("E-Mail");
		email.setIcon(FontAwesome.USER);
		email.setRequired(true);
		email.addValidator(new EmailValidator("Please provide a correct email address."));
		email.setWidth("300px");
		form.addComponent(email);
		
		// Password-TextField
		password = new PasswordField("Password");
		password.setIcon(FontAwesome.USER_SECRET);
		password.setRequired(true);
		password.addValidator(new NullValidator("Please provide a password", false));
		password.setWidth("300px");
		password.setValue("");
		password.setNullRepresentation("");
		password.setImmediate(true);
		form.addComponent(password);
		
		HorizontalLayout btn_layout = new HorizontalLayout();
		
		login_btn = new Button("Login", this);
		btn_layout.addComponent(login_btn);
				
		register_btn = new Button("Create a new account");
		btn_layout.addComponent(register_btn);	
		register_btn.addClickListener(e -> {
			getUI().getNavigator().navigateTo(MyRegister.NAME);
		});		
		
		form.addComponent(btn_layout);
		form.setSizeUndefined();
		
		addComponent(form);
		setComponentAlignment(form, Alignment.TOP_CENTER);
		setMargin(true);
		setSpacing(true);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		email.focus();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if(!email.isValid() || !password.isValid()) {
			Notification.show("Invalid email or password supplied.");
			return;
		}
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(MyUI.PERSISTENCE_UNIT);
		EntityManager em = emf.createEntityManager();
		
		String email = this.email.getValue();
		String password = this.password.getValue();
		
		// Include a database request here to check if this user exists with that password
		Boolean isValid = isValid(em);
		
		if(isValid) {
			User user = getUser(em);
			getSession().setAttribute("user", user);
			getUI().getNavigator().navigateTo(MyOverview.NAME);
		} else {
			// wrong password, reset and focus it
			this.password.setValue(null);
			this.password.focus();
		}
		
	}
	
	public boolean isValid(EntityManager em) {
		
		Query query = em.createQuery("select u from USER u WHERE u.email=:arg1 AND u.password=:arg2");
		query.setParameter("arg1", (String) email.getValue());
		query.setParameter("arg2", (String) password.getValue());
		
		// better return a user object to store in the session
		
		if(query.getResultList().isEmpty()) {
			return false;
		} else {
			return true;
		}
		

	}
	
	public User getUser(EntityManager em) {
		
		User user;
		
		TypedQuery<User> query = em.createQuery("select u from USER u WHERE u.email=:arg1", User.class);
		query.setParameter("arg1", (String) email.getValue());

		try {
			user = query.getSingleResult();
			return user;
		} catch (Exception e) {
			new Notification("Warning", e.getMessage(), Notification.TYPE_ERROR_MESSAGE).show(Page.getCurrent());
			user = new User();
		}
		
		return user;
	}

}
