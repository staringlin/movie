package zust.service;


import zust.dto.AdminDto;
import zust.dto.Json;
import zust.dto.UserDto;

public interface AdminServiceI {

		public AdminDto login(String username,String password);
		public String modifyAdmin(int id,String username,String password);

}
