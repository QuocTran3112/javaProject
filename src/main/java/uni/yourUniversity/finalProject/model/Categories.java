package uni.yourUniversity.finalProject.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_category")
public class Categories extends BaseEntity{

	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "description", length = 100, nullable = false)
	private String description;
	
	@Column(name = "seo", length = 1000, nullable = false)
	private String seo;
	
	//One category có Many products
	@OneToMany(cascade = CascadeType.ALL,
			   fetch = FetchType.LAZY,
			    //= cascade trong database
			   							  //on update, on delete = cascade
			   mappedBy = "category")     //phải trùng với tên property của @ManyToOne
	private Set<ProductEntity> products = new HashSet<ProductEntity>(); 
	//Nếu có OneToManythì cần 2 hàm update + delete
	
	public void addProduct(ProductEntity product) {
		products.add(product);
		product.setCategory(this);
	}
	
	public void deleteProduct(ProductEntity product) {
		products.remove(product);
		product.setCategory(null);
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	private Categories parent;
	
	@OneToMany(cascade = CascadeType.ALL, 
			   fetch = FetchType.EAGER, 
			   mappedBy = "parent")
	private Set<Categories> childs = new HashSet<Categories>();
	public void addChild(Categories category) {
		this.childs.add(category);
		category.setParent(this);
	}
	public void deleteChild(Categories category) {
		this.childs.remove(category);
		category.setParent(null);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeo() {
		return seo;
	}

	public void setSeo(String seo) {
		this.seo = seo;
	}

	public Set<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductEntity> products) {
		this.products = products;
	}

	public Categories getParent() {
		return parent;
	}

	public void setParent(Categories parent) {
		this.parent = parent;
	}

	public Set<Categories> getChilds() {
		return childs;
	}

	public void setChilds(Set<Categories> childs) {
		this.childs = childs;
	}
	
	
}
