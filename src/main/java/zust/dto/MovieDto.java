package zust.dto;

public class MovieDto {

	private int id;
	private String name;
	private String published_year;
	private String type;
	private String poster;
	private int comments;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPublished_year() {
		return published_year;
	}
	public void setPublished_year(String published_year) {
		this.published_year = published_year;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	
	
}
