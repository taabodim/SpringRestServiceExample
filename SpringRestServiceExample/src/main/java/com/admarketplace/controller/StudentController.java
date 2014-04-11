package com.admarketplace.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/*
 * The goal of Spring's REST support is to make the development of RESTful Web services and applications easier.

 Client-side access to RESTful resources is greatly simplified using Spring RestTemplate. RestTemplate follows in the footsteps of other template classes in Spring such as JdbcTemplate and JmsTemplate. Instead of dealing with a verbose lower level API such as Apache Commons HttpClient to create RESTful request, RestTemplate provides one liner methods that are purpose built for RESTful programming.

 On the server-side, Spring's REST support is based upon Spring's existing annotation based MVC framework. (For those interested in the rational for that decision, and for not implementing JAX-RS, read Arjen Poutsma's SpringSource TeamBlog entry.) With little effort, you can marshall data out of a RESTful request using @RequestMapping and @PathVariable annotations and return different views as determined by the request's Context-Type header.
 * */

//The @Controller annotation indicates that a particular class serves the role of a controller.
//The basic purpose of the @Controller annotation is to act as a stereotype for the annotated class, indicating its role. The dispatcher will scan such annotated classes for mapped methods, detecting @RequestMapping annotations
//The following code snippet shows the use of path variables on a relative path
@Controller
public class StudentController {

	
	

	List<User> allUsers = new ArrayList<User>();

	@RequestMapping(value = "/add/{name}", method = RequestMethod.GET)
	public @ResponseBody UserResponse addStudent(@PathVariable String name) {
		User user = new User(name);
		allUsers.add(user);
		System.out.println("server adding a student in the school using get method");
		UserResponse res = new UserResponse(name, "student was added to school");

		return res;
	}

	
	@RequestMapping(value = "/add/{name}", method = RequestMethod.POST)
	public @ResponseBody UserResponse addStudentPostMethod(@PathVariable String name) {
		User user = new User(name);
		System.out.println("server adding a student in the school using post method");
		allUsers.add(user);
		UserResponse res = new UserResponse(name, "student was added to school");
		return res;
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public @ResponseBody
	UserResponse getAllStudent() {
	
		UserResponse res = new UserResponse(allUsers);
		return res;
	}
	
	

	
	@RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
	public @ResponseBody UserResponse getStudent(@PathVariable String name) {
		for (User user : allUsers) {
			if (user.name.equalsIgnoreCase(name)) {
				UserResponse res = new UserResponse(user);
				return res;
			}
		}
		UserResponse res = new UserResponse(name,
				"student doesnt exist in school");

		return res;
	}
	@RequestMapping(value = "/update/name/{name}/{newName}", method = RequestMethod.GET)
	public @ResponseBody UserResponse updateNameStudent(@PathVariable String name,@PathVariable String newName) {
		for (User user : allUsers) {
			if (user.name.equalsIgnoreCase(name)) {
				user.name = newName;
				break;
			}
		}
		UserResponse res = new UserResponse(name,
				"student name was updated from "+name+" to "+newName);
		
		return res;
	}
	
	@RequestMapping(value = "/update/password/{name}/{newPassword}", method = RequestMethod.GET)
	public @ResponseBody UserResponse updatePasswordStudent(@PathVariable String name,@PathVariable String newPassword) {
		String oldPassword="";
		for (User user : allUsers) {
			if (user.name.equalsIgnoreCase(name)) {
				oldPassword = user.password;
				user.password = newPassword;
				break;
			}
		}
		UserResponse res = new UserResponse(name,
				"student password was updated from "+oldPassword+" to "+newPassword);
		
		return res;
	}
	
	@RequestMapping(value = "/update/username/{name}/{newUsername}", method = RequestMethod.GET)
	public @ResponseBody UserResponse updateUsernameStudent(@PathVariable String name,@PathVariable String newUsername) {
		String oldUsername="";
		for (User user : allUsers) {
			if (user.name.equalsIgnoreCase(name)) {
				oldUsername = user.username;
				user.username = newUsername;
				break;
			}
		}
		UserResponse res = new UserResponse(name,
				"student username was updated from "+oldUsername+" to "+newUsername);
		
		return res;
	}
	@RequestMapping(value = "/delete/{name}", method = RequestMethod.GET)
	public @ResponseBody UserResponse deleteStudent(@PathVariable String name) {
		String oldUsername="";
		for (User user : allUsers) {
			if (user.name.equalsIgnoreCase(name)) {
				oldUsername = user.name;
				allUsers.remove(user);
				break;
			}
		}
		UserResponse res = new UserResponse(name,
				"student "+oldUsername+" was deleted from school");
		
		return res;
	}
	@RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
	public @ResponseBody UserResponse deleteAllStudents() {
		allUsers = new ArrayList<User>();
		UserResponse res = new UserResponse("sampleUser",
				"all students were deleted from  school");
		return res;
	}

	// The @PathVariable method parameter annotation is used to indicate that a
	// method parameter should be bound to the value of a URI template variable.
	// The URI Template "/owners/{ownerId}" specifies the variable name ownerId.
	// When the controller handles this request, the value of ownerId is set the
	// value in the request URI. For example, when a request comes in for
	// /owners/fred, the value 'fred' is bound to the method parameter String
	// ownerId.
	@RequestMapping(value = "/owners/{ownerId}", method = RequestMethod.GET)
	public @ResponseBody
	String findOwner(@PathVariable String ownerId, Model model) {
		// Owner owner = ownerService.findOwner(ownerId);
		String owner = "pinkel";
		model.addAttribute("owner", owner);
		return "displayOwner";
	}

	// Multiple @PathVariable annotations can be used to bind to multiple URI
	// Template variables as shown below:
	@RequestMapping(value = "/owners/{ownerId}/pets/{petId}", method = RequestMethod.GET)
	public @ResponseBody
	String findPet(@PathVariable String ownerId, @PathVariable String petId,
			Model model) {
		// Owner owner = ownerService.findOwner(ownderId);
		// Pet pet = owner.getPet(petId);
		String pet = "wiskey";
		model.addAttribute("pet", pet);
		return "displayPet";
	}

	@RequestMapping("/pets/{petId}")
	public void findPetRelativePath(@PathVariable String ownerId,
			@PathVariable String petId, Model model) {
		// implementation omitted
	}

	// method parameters that are decorated with the @PathVariable annotation
	// can be of any simple type such as int, long, Date... Spring automatically
	// converts to the appropriate type and throws a TypeMismatchException if
	// the type is not correct.

	/*
	 * Mapping the request body with the @RequestBody annotation The
	 * 
	 * @RequestBody method parameter annotation is used to indicate that a
	 * method parameter should be bound to the value of the HTTP request body.
	 * For example,
	 */
	/*
	 * the conversion of the request body to the method argument is done using a
	 * HttpMessageConverter. HttpMessageConverter is responsible for converting
	 * for converting from the HTTP request message to an object and converting
	 * from an object to the HTTP response body.
	 */
	@RequestMapping(value = "/something", method = RequestMethod.PUT)
	public void handle(@RequestBody String body, Writer writer)
			throws IOException {
		writer.write(body);

	}

	/*
	 * The @RequestHeader annotation allows a method parameter to be bound to a
	 * request header.
	 * 
	 * Here is a request header sample:
	 * 
	 * Host localhost:8080 Accept
	 * text/html,application/xhtml+xml,application/xml;q=0.9 Accept-Language
	 * fr,en-gb;q=0.7,en;q=0.3 Accept-Encoding gzip,deflate Accept-Charset
	 * ISO-8859-1,utf-8;q=0.7,*;q=0.7 Keep-Alive 300 The following code sample
	 * allows you to easily get the value of the "Accept-Encoding" and
	 * "Keep-Alive" headers:
	 */
	@RequestMapping("/displayHeaderInfo.do")
	public void displayHeaderInfo(
			@RequestHeader("Accept-Encoding") String encoding,
			@RequestHeader("Keep-Alive") long keepAlive) {

	}

	
//	@RequestMapping(value = "/{id}/{name}/{surname}/{job}", method = RequestMethod.POST)
//	 public String saveEmployee(@PathVariable("id") long employeeId, @PathVariable String name, @PathVariable String surname, @PathVariable("job") String jobDescription) throws Exception {
//	  employeeService.saveEmployee(employeeId, name, surname, jobDescription);
//	  return "redirect:/restServices/employeeService/" + employeeId;
//	 }
//	 
//	 @RequestMapping(value = "/{id}/{name}/{surname}/{job}", method = RequestMethod.PUT)
//	 public String updateEmployee(@PathVariable("id") long employeeId, @PathVariable String name, @PathVariable String surname,  @PathVariable("job") String jobDescription) throws Exception {
//	  employeeService.updateEmployee(employeeId, name, surname, jobDescription);
//	  return "redirect:/restServices/employeeService/" + employeeId;
//	 }
//	
//	 @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	 @ResponseBody
//	 public String deleteEmployee(@PathVariable("id") long employeeId) throws Exception {
//	  employeeService.deleteEmployee(employeeId);
//	  return "OK";
//	 }


}
