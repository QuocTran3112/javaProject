package uni.yourUniversity.finalProject.controller.administrator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uni.yourUniversity.finalProject.dto.SaleOrderSearch;
import uni.yourUniversity.finalProject.model.ProductEntity;
import uni.yourUniversity.finalProject.model.SaleOrder;
import uni.yourUniversity.finalProject.model.SaleOrderProducts;
import uni.yourUniversity.finalProject.services.BaseService;
import uni.yourUniversity.finalProject.services.PagerData;
import uni.yourUniversity.finalProject.services.ProductService;
import uni.yourUniversity.finalProject.services.SaleOrderProductsService;
import uni.yourUniversity.finalProject.services.SaleOrderService;

@Controller
public class SaleOrderController {

	@Autowired
	private SaleOrderService sOService;
	
	@Autowired
	private SaleOrderProductsService sOPService;
	
	@Autowired
	private ProductService productService;

	// ex: /admin/product/management/144 (thông tin sản phẩm có id = 144) <=> Path
	// Variable
	// ex: /admin/product/management?productId=144 <=> request param
	@RequestMapping(value = { "/administrator/saleOrderControl/{saleOrderId}" }, method = RequestMethod.GET)
	public String adminProductDetail(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("saleOrderId") int saleOrderId) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		// lấy saleOrder trong db theo saleOrderId
		SaleOrder sOInDatabase = sOService.getById(saleOrderId);
		model.addAttribute("sOInDatabase", sOInDatabase);
		
		//Lấy saleOrderProducts trong db
		List<SaleOrderProducts> sOP = sOPService.findAll();
		
		//Lấy sản phẩm của từng saleOrder
		List<SaleOrderProducts> productListBySO = getSaleOrderProducts(sOInDatabase.getId(), sOP);
		
		//Lấy thông tin products trong SaleOrderProduct
		List<ProductEntity> productBySOP = getproductInfo(productListBySO);
		model.addAttribute("productBySOP", productBySOP);

		return "administrator/saleOrderProduct";

	}
	
	//Lấy list products của 1 saleOrder
	private List<SaleOrderProducts> getSaleOrderProducts(Integer sOId, List<SaleOrderProducts> sOP) {
		List<SaleOrderProducts> SOPList = new ArrayList<>();
		
		for (SaleOrderProducts s: sOP) {
			if (s.getSaleOrder().getId()==sOId) {
				SOPList.add(s);
			}
		}
		return SOPList;
	}
	
	//Lấy thông tin product từ SaleOrderProduct
	private List<ProductEntity> getproductInfo(List<SaleOrderProducts> sOP) {
		List<ProductEntity> productBySOP = new ArrayList<>();
		for (SaleOrderProducts s: sOP) {
			productBySOP.add(productService.getById(s.getProduct().getId()));
		}
		return productBySOP;
	}

	@RequestMapping(value = { "administrator/saleOrderList" }, method = RequestMethod.GET)
	private String findProduct(final Model model, final HttpServletResponse response, final HttpServletRequest request)
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

		PagerData<SaleOrder> saleOrderList = sOService.pageDivider(saleOrderSearch);
		model.addAttribute("saleOrderList", saleOrderList);

		return "administrator/saleOrderList";
	}

	@RequestMapping(value = { "administrator/saleOrderList/deleteSO/{saleOrderId}" }, method = RequestMethod.GET)
	private String deleteProduct(final Model model, 
			final HttpServletResponse response,
			final HttpServletRequest request,
			@PathVariable("saleOrderId") int saleOrderId) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		String sql = "SELECT * FROM tbl_saleorder WHERE id = " + saleOrderId;
		SaleOrder sO = sOService.getEntityByNativeSQL(sql);
		sO.setStatus(false);

		sOService.saveOrUpdate(sO);

		return "redirect:/administrator/saleOrderList";
	}
	
}	
 

