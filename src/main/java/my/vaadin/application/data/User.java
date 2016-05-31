package my.vaadin.application.data;

import java.util.Set;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	public Long id;
	
	@NotNull
	public String email;
	@NotNull
	public String password;
	
	@OneToMany(mappedBy = "owner")
	private Set<Document> documents;
	
	// ---- GETTER / SETTER -----
	
	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
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
	
	public Set<Document> getDocuments() {
		return documents;
	}
	
	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}
	
}
