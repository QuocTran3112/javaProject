package uni.yourUniversity.finalProject.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_products")
public class ProductEntity extends BaseEntity{
	
	@Column(name = "title", length = 1000, nullable = false)
	private String title;
	
	@Column(name = "price", precision = 13,scale = 2, nullable = false)
	private java.math.BigDecimal price;
	
	@Column(name = "price_sale", precision = 13,scale = 2, nullable = true)
	private java.math.BigDecimal priceSale;
	
	@Column(name = "short_description", length = 3000, nullable = true)
	private String shortDes;
	
	@Lob
	@Column(name = "detail_description", nullable = false, columnDefinition = "LONGTEXT")
	private String detailDes;
	
	@Column(name = "avatar", nullable = true)
	private String avatar;
	
	@Column(name = "seo", length = 1000, nullable = true)
	private String seo;
	
	@Column(name = "is_hot", nullable = true)
	private Boolean isHot = Boolean.TRUE;

	
	//Many product thuộc One category 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id") //cột khóa ngoại
	private Categories category;
	//------------
	

	////One product có Many images 
	@OneToMany(cascade = CascadeType.ALL, 
			   fetch = FetchType.EAGER, 
			   mappedBy = "product")
	private Set<ProductImages> productList = new HashSet<ProductImages>();
	
	public void addProductImages(ProductImages productImages) {
		productImages.setProduct(this);
		productList.add(productImages);
	}
	public void deleteProductImages(ProductImages productImages) {
		productImages.setProduct(null);
		productList.remove(productImages);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public java.math.BigDecimal getPrice() {
		return price;
	}
	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}
	public java.math.BigDecimal getPriceSale() {
		return priceSale;
	}
	public void setPriceSale(java.math.BigDecimal priceSale) {
		this.priceSale = priceSale;
	}
	public String getShortDes() {
		return shortDes;
	}
	public void setShortDes(String shortDes) {
		this.shortDes = shortDes;
	}
	public String getDetailDes() {
		return detailDes;
	}
	public void setDetailDes(String detailDes) {
		this.detailDes = detailDes;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getSeo() {
		return seo;
	}
	public void setSeo(String seo) {
		this.seo = seo;
	}
	public Boolean getIsHot() {
		return isHot;
	}
	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}
	public Categories getCategory() {
		return category;
	}
	public void setCategory(Categories category) {
		this.category = category;
	}
	public Set<ProductImages> getProductList() {
		return productList;
	}
	public void setProductList(Set<ProductImages> productList) {
		this.productList = productList;
	}
	
	
}
