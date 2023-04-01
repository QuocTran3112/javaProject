package uni.yourUniversity.finalProject.controller.administrator;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uni.yourUniversity.finalProject.controller.BaseController;
import uni.yourUniversity.finalProject.dto.SaleOrderSearch;
import uni.yourUniversity.finalProject.model.SaleOrder;
import uni.yourUniversity.finalProject.model.SaleOrderProducts;
import uni.yourUniversity.finalProject.model.Users;
import uni.yourUniversity.finalProject.services.BaseService;
import uni.yourUniversity.finalProject.services.PagerData;
import uni.yourUniversity.finalProject.services.SaleOrderProductsService;
import uni.yourUniversity.finalProject.services.SaleOrderService;
import uni.yourUniversity.finalProject.services.UserService;

@Controller
public class DashBoardController extends BaseController {

	@Autowired
	private SaleOrderService saleOrderService;
	
	@Autowired
	private SaleOrderProductsService saleOrderProductService;
	
	@Autowired
	private UserService uservice;

	@RequestMapping(value = { "/administrator/dashBoard" }, method = RequestMethod.GET)
	private String adminProfile(final Model model, final HttpServletResponse response, final HttpServletRequest request)
			throws IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		// Lấy dữ liệu
		String keyword = request.getParameter("keyword");
		Integer id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
		}
		Integer currentPage = BaseService.NO_PAGING;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
		}

		// Lưu dữ liệu
		SaleOrderSearch saleOrderSearch = new SaleOrderSearch();

		saleOrderSearch.setId(id);
		saleOrderSearch.setKeyword(keyword);
		saleOrderSearch.setCurrentPage(currentPage);

//		PagerData<ProductEntity> prodList = productService.searchProduct(productSearch);
		PagerData<SaleOrder> saleOrderList = saleOrderService.pageDivider(saleOrderSearch);
		model.addAttribute("saleOrderList", saleOrderList);

		Integer totalSO = saleOrderList.getTotalItems();
		model.addAttribute("totalSO", totalSO);
		
		BigDecimal tolPrice = doanhThu(saleOrderList);
		model.addAttribute("tolPrice", tolPrice);
		
		List<Users> userList = uservice.findAll();
		model.addAttribute("tolUser", tolUser(userList));
		
		List<SaleOrderProducts> productSold = saleOrderProductService.findAll();
		model.addAttribute("productSold", tolProductSale(productSold));
		
//		List<SaleOrder> saleOrderList = saleOrderService.findAll();
//		model.addAttribute("saleOrderList", saleOrderList);
//		for (SaleOrder s: saleOrderList) {
//			System.out.println(s.getTotal());
//		}

		return "administrator/dashBoard";
	}
	
	private BigDecimal doanhThu(PagerData<SaleOrder> saleOrderList) {
		BigDecimal total = new BigDecimal(0);
		for (SaleOrder s: saleOrderList.getData()) {
			total = total.add(s.getTotal());
		}
		return total;
	}
	
	private Integer tolUser(List<Users> userList) {
		int toluser = 0;
		for (int i = 0; i<userList.size();i++) {
			toluser+=1;
		}
		return toluser;
	}
	
	private Integer tolProductSale(List<SaleOrderProducts> sOP) {
		int tolsOP = 0;
		for (int i = 0; i<sOP.size();i++) {
			tolsOP+=1;
		}
		return tolsOP;
	}
}
