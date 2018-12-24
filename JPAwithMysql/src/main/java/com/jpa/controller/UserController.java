package com.jpa.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.entity.User;
import com.jpa.model.ApiStatus;
import com.jpa.service.IUserService;

@RestController
public class UserController {
	
	@Autowired
	private IUserService service   ; 
	
	@Autowired
	private ApiStatus api;

	@PostMapping("/add")
	public ResponseEntity<?> register(@ModelAttribute("user") User user)
	{
		int id =service.insert(user);
		
		return showResponse("User Added" , "success" , id);
		
		
	}
	
	@GetMapping("/find")
	private ResponseEntity<?> findOne(@RequestParam("id") int userId)
	{
		User user = service.findById(userId);
		if(user==null)
			return showResponse("user not found" , "error" , null);
		else
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping("/findall")
	private ResponseEntity<?> findAll(){
		List<User> list = service.findAll();
		if(list.isEmpty())
			return showResponse("user not found" , "error" , null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	
	

	@PutMapping("/update")
	public ResponseEntity<?> update(@ModelAttribute("user") User user)
	{
		User user1 = service.findById(user.getUid());
		if(user1==null)
			showResponse("user not found" , "error" , null);
		service.update(user);
		
		return showResponse("User updated" , "success" , null);
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam("id") int id)
	{
		User user1 = service.findById(id);
		if(user1==null)
			return showResponse("user not found" , "error" , null);
		service.delete(id);
		return showResponse("User deleted" , "success" , null);

	
	}
	
	
	private ResponseEntity<ApiStatus> showResponse(String message , String status , Integer id){
		api.setMessage(message);
		api.setStatus(status);
		api.setUserId(id);
		return ResponseEntity.status(HttpStatus.OK).body(api);
		
	}
	
	@ExceptionHandler({Exception.class})

	private ResponseEntity<ApiStatus> showException(Exception ex){
		api.setMessage(ex. getMessage());
		api.setStatus("error");
		api.setUserId(null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(api);
		
	}
	
	
	
	
	
	

}