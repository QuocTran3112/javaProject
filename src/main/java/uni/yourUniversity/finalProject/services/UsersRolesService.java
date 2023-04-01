package uni.yourUniversity.finalProject.services;

import org.springframework.stereotype.Service;

import uni.yourUniversity.finalProject.model.UsersRoles;

@Service
public class UsersRolesService extends BaseService<UsersRoles>{

	@Override
	protected Class<UsersRoles> clazz() {
		// TODO Auto-generated method stub
		return UsersRoles.class;
	}	

}
