package uni.yourUniversity.finalProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_assesses")
public class Assesses extends BaseEntity{
	
	@Column(name = "content", length = 1000, nullable = false)
	private String content;
	
	@Column(name = "author", nullable = true)
	private String author;
	
	@Column(name = "star_rate",nullable = false)
	private Double starRate;
	
	@Column(name = "user_id",nullable = false)
	private Integer user_id;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Double getStarRate() {
		return starRate;
	}

	public void setStarRate(Double starRate) {
		this.starRate = starRate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "product_id") //cột khóa ngoại
	private Integer product_comment;
	
	public Integer getProduct_comment() {
		return product_comment;
	}

	public void setProduct_comment(Integer product_comment) {
		this.product_comment = product_comment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [content=" + content + "]";
	}

}
