package uni.yourUniversity.finalProject.controller.administrator;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uni.yourUniversity.finalProject.controller.BaseController;

@Controller
public class ProfileController extends BaseController{

	@RequestMapping(value = {"/administrator/profile"}, method = RequestMethod.GET)
	private String adminProfile(final Model model,
							  final HttpServletResponse response,
							  final HttpServletRequest request) 
		throws IOException{
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		return "administrator/profile";
	}
	
}
