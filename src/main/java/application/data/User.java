package application.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity(name = "USER")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2030885943886993215L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@JoinColumn(name = "DOCUMENTS", referencedColumnName = "id", nullable = true)
	private List<Document> documents;

	public User () {
		
	}
	
	public User(String email, String password, List<Document> documents) {
		super();
		this.email = email;
		this.password = password;
		this.documents = documents;
	}

	// ----- ----- ----- ----- ----- ----- //
	
	
	
	public String getEmail() {
		return email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", documents=" + documents + "]";
	}
	
	

}
