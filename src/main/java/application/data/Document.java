package application.data;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Document implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4241565858213764586L;

	@Id
	@Column(name = "DOCUMENT_ID", nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name = "FILE", nullable = false)
	private File file;
	
	@Column(name = "AUTHOR", nullable = false)
	private String author;
	
	@JoinColumn(name = "OWNER", referencedColumnName = "email", nullable = false)
	@ManyToOne
	private User owner;
	
	@Column(name ="TITLE", nullable = false)
	private String title;
	
	@Column(name = "AFFIILIATE", nullable = false)
	private String affiliate;
	
	
	// ----- ----- ----- ----- ----- ----- //

	
	
	public Document() {
		
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Document(String author, User owner, String title, String affiliate, File file) {
		super();
		this.author = author;
		this.owner = owner;
		this.title = title;
		this.affiliate = affiliate;
		this.file = file;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAffiliate() {
		return affiliate;
	}

	public void setAffiliate(String affiliate) {
		this.affiliate = affiliate;
	}
	
	
	
	
}
