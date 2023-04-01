package uni.yourUniversity.finalProject.controller.administrator;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uni.yourUniversity.finalProject.controller.BaseController;
import uni.yourUniversity.finalProject.dto.UserSearch;
import uni.yourUniversity.finalProject.model.Users;
import uni.yourUniversity.finalProject.services.BaseService;
import uni.yourUniversity.finalProject.services.PagerData;
import uni.yourUniversity.finalProject.services.UserService;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService uService;
	

	//ex: /admin/product/management/144 (thông tin sản phẩm có id = 144) <=> Path Variable
	//ex: /admin/product/management?productId=144						 <=> request param
	@RequestMapping(value = { "/administrator/userControl/{userId}" }, method = RequestMethod.GET)
	public String adminProductDetail(final Model model, 
							   		 final HttpServletRequest request,
								   	 final HttpServletResponse response, 
								   	 @PathVariable("userId") int userId) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		// lấy product trong db theo ProductId
		Users userInDatabase = uService.getById(userId);
		model.addAttribute("updateUserModel", userInDatabase);
		
//		System.out.println(productInDatabase);
//		
//		System.out.println(categoryService.getById(1));
		
		// đẩy danh sách categories xuống view(thêm mới sản phẩm) để hiển thị
		// selectbox cho phép chọn category
//		List<Categories> categories = categoryService.getEntitiesByNativeSQL("SELECT * FROM tbl_category");
//		model.addAttribute("categories", categories);
		
		return "administrator/signUp";
		
	}
	
	@RequestMapping(value = { "administrator/userList" }, method = RequestMethod.GET)
	private String findProduct(	final Model model, 
								final HttpServletResponse response,
								final HttpServletRequest request)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

//List<Categories> categories = categoryService.getEntitiesByNativeSQL("SELECT * FROM tbl_category");
//model.addAttribute("categories", categories);

//Lấy dữ liệu
		String keyword = request.getParameter("keyword");
		Integer currentPage = BaseService.NO_PAGING;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
		}

		//Lưu dữ liệu
		UserSearch uSearch = new UserSearch();
		uSearch.setKeyword(keyword);
		uSearch.setCurrentPage(currentPage);

		
		
		//PagerData<ProductEntity> prodList = productService.searchProduct(productSearch);
		PagerData<Users> userList = uService.searchUser(uSearch);
		model.addAttribute("userList", userList);

		model.addAttribute("userSearch", uSearch);

		return "administrator/userList";
	}
	
	@RequestMapping(value = {"administrator/userList/deleteUser/{userId}"}, method = RequestMethod.GET)
	private String deleteProduct(final Model model,
							  final HttpServletResponse response,
							  final HttpServletRequest request,
							  @PathVariable("userId") int userId) 
		throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		String sql = "SELECT * FROM tbl_users WHERE id = " + userId ;
		Users user = uService.getEntityByNativeSQL(sql);
		user.setStatus(false);
		
		uService.saveOrUpdate(user);
	
		return "redirect:/administrator/userList";
	}

	//Trang hiển thị phần thêm/ sửa sp
		@RequestMapping(value = {"/administrator/userControl"}, method = RequestMethod.GET)
		private String product(final Model model,
								  final HttpServletResponse response,
								  final HttpServletRequest request) 
			throws IOException{
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			
			model.addAttribute("updateUserModel", new Users());
			
			return "administrator/signUp";
		}
	
		@RequestMapping(value = {"/administrator/userControl/updateUser"}, method = RequestMethod.POST) 
		public String contact3(final Model model,
							  final HttpServletRequest request,
							  final HttpServletResponse response,
							  @ModelAttribute("updateUserModel") Users user //spring-form binding
							  )
			throws IOException {
			
			
			 System.out.println(user.getShipping_address());
			 System.out.println(user.getId());
//			System.out.println(product.getDetailDes());
			
			// kiểm tra xem thông tin product đẩy lên khi click submit nên là tạo mới hay chỉnh sửa
					if(user.getId() != null && user.getId() > 0) { //chỉnh sửa sản phẩm
						uService.saveOrUpdate(user);
					}
			
			model.addAttribute("updateUserModel", new Users());
			
			return "redirect:/administrator/userList";
		}
		
}
