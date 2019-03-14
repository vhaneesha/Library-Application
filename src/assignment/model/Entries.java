package assignment.model;

public class Entries {
	
	private Integer id;
	private String type;
	private String name;
	private String info;
	private String available;
	
	
	
	public Entries(int id, String type, String name, String info, String available) {
		super();
		this.id = id;
		this.name = name;
		this.info = info;
		this.available = available;
		this.type = type;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAvailable() {
		return available;
	}
	
	public void setAvailable(String available) {
		this.available = available;
	}
	
}
