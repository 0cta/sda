package application.view;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

import application.data.Document;

@SuppressWarnings("serial")
public class MyOverview extends VerticalLayout implements View {

	public static final String NAME = "MyOverview";
	
	private Table doc_table;	
	private Button logout_btn;
	
	
	@SuppressWarnings("deprecation")
	public MyOverview() {
		setSizeFull();
		Notification welcome_msg = new Notification("Welcome", "This is a document management system for DocBook 5 XML documents. You are free to upload your XML files, let them be parsed and download a PDF file afterwards.", Notification.TYPE_HUMANIZED_MESSAGE);
		welcome_msg.setDelayMsec(5000);
		welcome_msg.setPosition(Position.TOP_CENTER);
		welcome_msg.setIcon(FontAwesome.INFO);
		welcome_msg.show(Page.getCurrent());
		
		addComponent(createDocumentTable());
		setComponentAlignment(doc_table, Alignment.TOP_CENTER);
		
		addComponent(createUploadButton());
		//User user = (User) getSession().getAttribute("user");
		//addComponent(new Label(user.getEmail()));
		
		addComponent(createLogoutButton());
		setComponentAlignment(logout_btn, Alignment.BOTTOM_LEFT);
		
		setSpacing(true);
		setMargin(true);
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public Button createUploadButton() {
		Button upload_btn = new Button("Upload an XML file");
		upload_btn.addClickListener(e -> {
			getUI().getNavigator().navigateTo(MyUpload.NAME);
		});
		return upload_btn;
	}
	
	public Table createDocumentTable() {
		
		// Create a persistent document container
		JPAContainer<Document> documents = JPAContainerFactory.make(Document.class, "my.vaadin.application");
		
		// Add an entity (testwise) 
		//User user = new User("kevin.lemmer@yahoo.de", "lemmer12", null);
		//documents.addEntity(new Document("Kevin Lemmer", user, "Mörder und andere Menschen", "Heine Verlag", new File("C:\\windows-version.txt")));
		doc_table = new Table("Available Documents", documents); 
		doc_table.addGeneratedColumn("download", new ColumnGenerator() {
			
			@Override
			public Object generateCell(Table source, Object itemId, Object columnId) {
				// TODO Auto-generated method stub
				Item item = source.getItem(itemId);
				Link link = (Link) item.getItemProperty("file");
				
				final Button download_btn = new Button(FontAwesome.DOWNLOAD);
				download_btn.addClickListener(e -> {
					Notification.show("I'm listening");
				});
				
				return download_btn;
			}
		});
		
		doc_table.setSortEnabled(false);
		doc_table.setSelectable(true);
		doc_table.setColumnHeader("download", "Download File");
		doc_table.setColumnHeader("author", "Author");
		doc_table.setColumnHeader("owner", "Owner");
		doc_table.setColumnHeader("id", "ID");
		doc_table.setColumnHeader("title", "Document Title");
		doc_table.setColumnHeader("affiliate", "Affiliate");
		doc_table.setColumnHeader("file", "File Name");
		doc_table.setVisibleColumns("id","author","title","affiliate","file","download");
		doc_table.setSizeFull();
		return doc_table;
	}
	
	public Button createLogoutButton() {
		
		logout_btn = new Button("Logout");
		logout_btn.addClickListener(e -> {
			getSession().setAttribute("user", null);
			getUI().getNavigator().navigateTo(MyLogin.NAME);
		});
		
		return logout_btn;
	}
	
}
