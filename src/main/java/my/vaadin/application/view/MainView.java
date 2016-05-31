package my.vaadin.application.view;

import java.util.Date;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MainView extends VerticalLayout implements View {
	
	public MainView(Navigator nav) {
		setSizeFull();
		
		final HorizontalLayout layout_head = new HorizontalLayout();
		final Label table_heading = new Label("Available Documents");
		final TextField search_box = new TextField("Enter search here...");
		layout_head.addComponents(table_heading, search_box);
		layout_head.setComponentAlignment(table_heading, Alignment.MIDDLE_LEFT);
		layout_head.setComponentAlignment(search_box, Alignment.MIDDLE_RIGHT);
		
		final Table documents_table = new Table();
		documents_table.addContainerProperty("Title", String.class, null);
		documents_table.addContainerProperty("Author", String.class, null);
		documents_table.addContainerProperty("Date", Date.class, null);
		documents_table.addItem(new Object[]{"Test Title", "Test Author", Date.UTC(2016, 9, 21, 23, 59, 59)});
		
		documents_table.setSizeFull();
		
		addComponents(layout_head, documents_table);
		setComponentAlignment(documents_table, Alignment.TOP_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
