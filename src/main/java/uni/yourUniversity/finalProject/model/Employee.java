package uni.yourUniversity.finalProject.model;

public class Employee {
	
	private int MaNv;     //property
	private String tenNv; //property
	
	public Employee() {
		super();
	}
	
	public Employee(int maNv, String tenNv) {
		super();
		MaNv = maNv;
		this.tenNv = tenNv;
	}
	
	public int getMaNv() {
		return MaNv;
	}
	public void setMaNv(int maNv) {
		MaNv = maNv;
	}
	public String getTenNv() {
		return tenNv;
	}
	public void setTenNv(String tenNv) {
		this.tenNv = tenNv;
	}
}
