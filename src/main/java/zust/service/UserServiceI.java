package zust.service;


import java.util.List;

import zust.dto.Json;
import zust.dto.UserDto;

public interface UserServiceI {
		public Json register(UserDto u);

		public UserDto login(String username,String password);

		public void delete(int id);

		public List<UserDto> getUsers(String currentPage);

		public int getCount();

		public Json modifyUser(UserDto u);

		public List<UserDto> search(String username);
}
