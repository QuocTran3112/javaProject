package uni.yourUniversity.finalProject.controller.customer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uni.yourUniversity.finalProject.controller.BaseController;

@Controller
public class LogInController extends BaseController{
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET) 
	public String contact(final Model model,
						  final HttpServletRequest request,
						  final HttpServletResponse response)
		throws IOException {

		
		//đẩy 1 data có tên là contactModel xuống view
		//với kiểu là Contact => spring form mới có thể mapping giữa html attribute với Class property
//		System.out.println(request.getParameter("email"));
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		return "customer/login";
	}

	
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST) 
	public String contact2(final Model model,
						  final HttpServletRequest request,
						  final HttpServletResponse response)
		throws IOException {
		
		return "customer/login";
	}
	
}
