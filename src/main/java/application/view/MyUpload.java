package application.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MyUpload extends VerticalLayout implements View {

	public static final String NAME = "MyUpload";
	
	Panel panel;
	FormLayout form;
	Upload upload;
	
	public MyUpload() {
		setSizeFull();
		
		upload = new Upload();
		DocumentUploader receiver = new DocumentUploader();
		upload.setButtonCaption("Start File Upload");
		upload.setReceiver(receiver);
		upload.addSucceededListener(receiver);
		
		final ProgressBar progressBar = new ProgressBar(0.0f);
		
		form = new FormLayout();
		form.addComponent(upload);
		
		panel = new Panel();
		panel.setCaption("Upload an XML document");
		panel.setContent(form);
		
		addComponent(panel);
		setComponentAlignment(panel, Alignment.TOP_CENTER);
		
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
			
	}

	class DocumentUploader implements Receiver, SucceededListener {
		public File file;

		@SuppressWarnings("deprecation")
		@Override
		public OutputStream receiveUpload(String filename, String mimeType) {
			// TODO Auto-generated method stub
			// Create upload stream
			FileOutputStream fos = null; // stream to write to
			try {
				// open the file for writing
				String tempDir = System.getProperty("java.io.tmpdir");
				file = new File(tempDir + filename);
				fos = new FileOutputStream(file);
			} catch (final java.io.FileNotFoundException e) {
				new Notification("Could not open file",
						e.getMessage(),
						Notification.TYPE_ERROR_MESSAGE).show(Page.getCurrent());
				return null;
			}
			
			return fos;
		}
		
		@Override
		public void uploadSucceeded(SucceededEvent event) {
			// TODO Auto-generated method stub
			Notification.show("Upload succeeded");
			// Parsing-Prozess anstoßen
			// file ist hier verfügbar zur weiteren Verarbeitung und anschließender Speicherung in die Datenbank, vielleicht sogar parallel?
		}
		
	}
}
