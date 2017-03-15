package hello;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Greeting")
public class Greeting {

	
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="id")
	@GeneratedValue
	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	private int id;
	
    private String content;

    public Greeting() {
    }

    public Greeting(String content) {
        this.content = content;
    }
    @Column(name="content")
    @NotNull
    public String getContent() {
        return content;
    }

}
