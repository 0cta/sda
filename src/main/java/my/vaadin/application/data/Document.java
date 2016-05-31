package my.vaadin.application.data;

@Entity
public class Document {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min=1,max=255)
	private String title;
	
	@Size(min=3,max=255)
	private String author;
	
	@Size(min=1,max=255)
	private String affiliation;
}
