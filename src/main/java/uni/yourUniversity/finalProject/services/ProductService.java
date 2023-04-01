package uni.yourUniversity.finalProject.services;

import java.io.File;
import java.io.IOException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import uni.yourUniversity.finalProject.dto.ProductSearch;
import uni.yourUniversity.finalProject.model.ProductEntity;
import uni.yourUniversity.finalProject.model.ProductImages;

import org.apache.commons.lang3.StringUtils;


@Service
public class ProductService extends BaseService<ProductEntity>{
	
	@Autowired
	private ProductImagesService productImagesService;

	@Override
	protected Class<ProductEntity> clazz() {
		// TODO Auto-generated method stub
		return ProductEntity.class;
	}

	
	/**
	 * dùng để kiểm tra xem admin có upload ảnh product hay không
	 * 
	 * @param images
	 * @return
	 */
	private boolean isEmptyUploadFile(MultipartFile[] images) {
		if (images == null || images.length <= 0)
			return true;

		if (images.length == 1 && images[0].getOriginalFilename().isEmpty())
			return true;

		return false;
	}

	/**
	 * dùng để kiểm tra xem admin có upload ảnh product hay không
	 * 
	 * @param image
	 * @return
	 */
	private boolean isEmptyUploadFile(MultipartFile image) {
		return image == null || image.getOriginalFilename().isEmpty();
	}

	private String getUniqueUploadFileName(String fileName) {
		String[] splitFileName = fileName.split("\\.");
		return splitFileName[0] + System.currentTimeMillis() + "." + splitFileName[1];
	}
	
	@Transactional
	public ProductEntity saveProduct(ProductEntity product, MultipartFile productAvatar, MultipartFile[] productPictures)
			throws IllegalStateException, IOException {

		// kiểm tra xem admin có đẩy avatar lên không ???
		if (!isEmptyUploadFile(productAvatar)) { // có đẩy avatar lên.

			String fileName = getUniqueUploadFileName(productAvatar.getOriginalFilename());

			// tạo đường dẫn tới folder chứa avatar
			String pathToAvatar = "D:/Future/JavaWeb/Upload/Product/avatar/" + fileName;

			// lưu avatar vào đường dẫn trên
			productAvatar.transferTo(new File(pathToAvatar));

			product.setAvatar("product/avatar/" + fileName);
		}

		// có đẩy pictures(product_images) ???
		if (!isEmptyUploadFile(productPictures)) { // có đẩy pictures lên.

			// nếu admin đẩy lên thì duyệt tất cả file đẩy lên và lưu trên server
			for (MultipartFile pic : productPictures) {
				String fileName = getUniqueUploadFileName(pic.getOriginalFilename());

				// lưu ảnh admin đẩy lên vào server
				pic.transferTo(new File("D:/Future/JavaWeb/Upload/Product/images/" + fileName));

				// tạo mới 1 bản ghi product_images
				ProductImages productImages = new ProductImages();
				productImages.setPath("Product/images/" + fileName);
				productImages.setTitle(fileName);

				product.addProductImages(productImages);
			}
		}

		// tạo seo: bổ sung thêm thời gian tính bằng miliseconds để tránh trùng seo
//				p.setSeo(new Slugify().slugify(p.getTitle() + "-" + System.currentTimeMillis()));

		// lưu vào database
		return super.saveOrUpdate(product);

	}


	@Transactional
	public ProductEntity updateProduct(ProductEntity p, MultipartFile productAvatar, MultipartFile[] productPictures)
			throws IllegalStateException, IOException {

		// lấy thông tin cũ của product theo id đang có trong database
		ProductEntity productInDb = super.getById(p.getId());

		// có đẩy avartar ??? => xóa avatar cũ đi và thêm avatar mới
		if (!isEmptyUploadFile(productAvatar)) {
			// xóa avatar cũ trong folder
			new File("C:/upload/" + productInDb.getAvatar()).delete();

			// update avatar mới
			String fileName = getUniqueUploadFileName(productAvatar.getOriginalFilename());
			productAvatar.transferTo(new File("D:/Future/JavaWeb/Upload/Product/avatar/" + fileName));
			p.setAvatar("Product/avatar/" + fileName);
		} else {
			// sử dụng lại avartar cũ
			p.setAvatar(productInDb.getAvatar());
		}

		// có đẩy pictures ???
		if (!isEmptyUploadFile(productPictures)) {

			// xóa pictures cũ
			if (productInDb.getProductList() != null && productInDb.getProductList().size() > 0) {
				for (ProductImages opi : productInDb.getProductList()) {
					// xóa avatar trong folder lên
					new File("D:/Future/JavaWeb/Upload/" + opi.getPath()).delete();

					// xóa dữ liệu của image cho sản phẩm đang sửa trong database
					productImagesService.delete(opi);
				}
			}

			// update pictures mới
			for (MultipartFile pic : productPictures) {
				String fileName = getUniqueUploadFileName(pic.getOriginalFilename());
				
				pic.transferTo(new File("D:/Future/JavaWeb/Upload/Product/images/" + fileName));
				
				ProductImages pi = new ProductImages();
				pi.setPath("Product/images/" + fileName);
				pi.setTitle(fileName);
				
				p.addProductImages(pi);
			}
		}
		
		//tạo seo
//		p.setSeo(new Slugify().slugify(p.getTitle() + "-" + System.currentTimeMillis()));
		
		// lưu vào database
		return super.saveOrUpdate(p);
	}
	
	public PagerData<ProductEntity> searchProduct(ProductSearch productSearch) {
		// khởi tạo câu lệnh
		String sql = "SELECT * FROM tbl_products p WHERE 1=1";

		if (productSearch != null) {
			
			// tìm kiếm theo category
			if(productSearch.getCategoryId()!=0 && productSearch.getCategoryId() > 0) {
				sql += " and category_id = " + productSearch.getCategoryId();
			}
		
			// tìm kiếm theo title và description
			if (!StringUtils.isEmpty(productSearch.getKeyword())) {
				sql += " and (p.title like '%" + productSearch.getKeyword() + "%'" + 
						     " or p.detail_description like '%" + productSearch.getKeyword() + "%'" + 
						     " or p.short_description like '%" + productSearch.getKeyword() + "%')";
			}
		}

		return super.getEntitiesByNativeSQL(sql, productSearch.getCurrentPage());
	}
	
}
