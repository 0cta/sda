package my.vaadin.application.view;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class RegisterView extends VerticalLayout implements View {

	public RegisterView(Navigator nav) {
		
		Label label = new Label("Ich bin die REGISTER VIEW!");
		Button btn = new Button("ICH BIN EIN BUTTTTOOOOOON");
		btn.addClickListener(e -> {
			nav.navigateTo("login");
		});
		addComponent(btn);
		addComponent(label);
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {

	}

	
	
}
