package zust.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movie_preferences")
public class MoviePreference {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int userID;
	private int preference;
	private long timestamp;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="movieID", referencedColumnName="id")
	private movie movie;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public movie getMovie() {
		return movie;
	}
	public void setMovie(movie movie) {
		this.movie = movie;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getPreference() {
		return preference;
	}
	public void setPreference(int preference) {
		this.preference = preference;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
