package application.view;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import application.data.User;
import my.vaadin.application.MyUI;

@SuppressWarnings("serial")
public class MyRegister extends VerticalLayout implements View, Button.ClickListener {

	public static final String NAME = "MyRegister";
	
	private final Label caption;
	private final TextField email;
	private final PasswordField password;		
	private final Button register_btn;
	
	public MyRegister () {
		setSizeFull();	
		
		FormLayout form = new FormLayout();
		form.setSizeUndefined();
		
		caption = new Label("Create a new user");
		form.addComponent(caption);
		
		email = new TextField("Your E-Mail");
		email.setDescription("This will be your username");
		email.setWidth("300px");
		email.addValidator(new EmailValidator("Not a valid email address"));
		email.setIcon(FontAwesome.USER);
		email.setRequired(true);
		form.addComponent(email);
		
		password = new PasswordField("Your new password");
		password.setDescription("Choose carefully. No validation by now");
		password.setWidth("300px");
		password.addValidator(new NullValidator("May not be empty", false));
		password.setNullRepresentation("");
		password.setIcon(FontAwesome.USER_SECRET);
		password.setRequired(true);
		form.addComponent(password);
		
		register_btn = new Button("Submit", this);
		form.addComponent(register_btn);
		
		
		addComponent(form);
		setComponentAlignment(form, Alignment.TOP_CENTER);
		setSpacing(true);
		setMargin(true);
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if(email.isValid() && password.isValid()) {
			// save user to database, log him in
			User user = new User((String) email.getValue(), (String) password.getValue(), null);
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(MyUI.PERSISTENCE_UNIT);
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			
			getSession().setAttribute("user", user.getEmail());
			getUI().getNavigator().navigateTo(MyOverview.NAME);
		
			em.close();
			emf.close();
		}
	}
	

}
