package uni.yourUniversity.finalProject.services;

import org.springframework.stereotype.Service;

import uni.yourUniversity.finalProject.model.Role;

@Service
public class RoleService extends BaseService<Role>{
		
	@Override
	protected Class<Role> clazz() {
		// TODO Auto-generated method stub
		return Role.class;
	}
	
}
