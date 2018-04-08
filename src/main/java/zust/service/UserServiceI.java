package zust.service;


import zust.dto.Json;
import zust.dto.UserDto;

public interface UserServiceI {
		public Json register(UserDto u);

		public UserDto login(String username,String password);

		public void delete(int id);
}
