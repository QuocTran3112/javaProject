package uni.yourUniversity.finalProject.controller.administrator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uni.yourUniversity.finalProject.controller.BaseController;
import uni.yourUniversity.finalProject.model.Assesses;
import uni.yourUniversity.finalProject.model.ProductEntity;
import uni.yourUniversity.finalProject.services.CommentService;
import uni.yourUniversity.finalProject.services.ProductService;

@Controller
public class CommentController extends BaseController{
		
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = { "/administrator/commentControl/{productId}" }, method = RequestMethod.GET)
	public String adminProductDetail(final Model model, 
							   		 final HttpServletRequest request,
								   	 final HttpServletResponse response, 
								   	 @PathVariable("productId") int productId) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		// lấy product trong db theo ProductId
		ProductEntity productInDatabase = productService.getById(productId);
		model.addAttribute("product", productInDatabase);
		
		// lấy danh sách comment
		List<Assesses> commentInDatabase = commentService.findAll();
		
		List<Assesses> commentEachProduct = new ArrayList<>();
		
		int totalComment = 0;
		
		for (Assesses cmt: commentInDatabase) {
			if (cmt.getProduct_comment()==productId) {
				commentEachProduct.add(cmt);
				totalComment+=1;
			}
		}
		
//		System.out.println(tolStar(commentEachProduct));
		
		model.addAttribute("tolStar", tolStar(commentEachProduct));
		
		model.addAttribute("commentList", commentEachProduct);
		model.addAttribute("totalComment", totalComment);
		
		// đẩy danh sách categories xuống view(thêm mới sản phẩm) để hiển thị
		// selectbox cho phép chọn category
//		List<Categories> categories = categoryService.getEntitiesByNativeSQL("SELECT * FROM tbl_category");
//		model.addAttribute("categories", categories);
		
		return "administrator/productComment";
	}
	
	//addComment
	@RequestMapping(value = {"/productDetail/deleteComment"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajaxContact(final Model model,
														   final HttpServletRequest request,
														   final HttpServletResponse response,
														   final @RequestBody Assesses assesses
														   ) {
		System.out.println(assesses.getId());
		
	    Assesses as = commentService.getById(assesses.getId());
	    
		  
		commentService.delete(as);
		 
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("statusCode", 200); //200: success; 404: not found; 500: error

		
		return ResponseEntity.ok(jsonResult);
	}
	
	//Hàm tính tổng star-rate
	private double tolStar(List<Assesses> commentEachProduct) {
		Double tol = 0.0;
		Double ssh =0.0;
		Double average =0.0;
		
		for (Assesses a: commentEachProduct) {
			tol+=a.getStarRate();
			ssh+=1;
		}
		average = tol/ssh;
		return average;
	}
}
