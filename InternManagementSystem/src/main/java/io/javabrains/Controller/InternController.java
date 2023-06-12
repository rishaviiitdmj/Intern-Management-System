package io.javabrains.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.Entity.Buddy;
import io.javabrains.Entity.Intern;
import io.javabrains.Entity.Mentor;
import io.javabrains.Service.BuddyService;
import io.javabrains.Service.InternService;
import io.javabrains.Service.MentorService;

@RestController
public class InternController {

	@Autowired
	InternService internService;
	@Autowired
	BuddyService buddyService;
	@Autowired
	MentorService mentorService;
	
	//Mappings for Interns
	
	@PostMapping("/api/v1/intern")
	public ResponseEntity<Intern> saveAnIntern(@RequestBody Intern intern) {
		internService.saveAnIntern(intern);
		
		return ResponseEntity.ok(intern);
	}
	
//	@GetMapping("/api/v1/intern/example")
//	public Intern getIntern() {
//		Intern intern=new Intern();
//		intern.setAddress("dehli");
//		intern.setCreated(new Date());
//		intern.setInternId(1);
//		intern.setMobile("1234567891");
//		intern.setName("ekom");
//		intern.setUpdated(new Date());
//		Buddy buddy=new Buddy();
//		buddy.setAddress("mumbai");
//		buddy.setCreated(new Date());
//		buddy.setId(2);
//		buddy.setMobile("9876543219");
//		buddy.setName("abc");
//		buddy.setUpdated(new Date());
//		
//		
//		
//		Mentor mentor=new Mentor();
//		mentor.setAddress("hyd");
//		mentor.setCreated(new Date());
//		mentor.setId(3);
//		mentor.setMobile("35378325743");
//		mentor.setName("xyz");
//		mentor.setUpdated(new Date());
//		
//		
//		intern.setBuddy(buddy);
//		intern.setMentor(mentor);
//		
//		return intern;
//	}
	
	@GetMapping("/api/v1/intern")
	public List<Intern> getAllInterns(@RequestParam(value="pageNumber",required=true) Integer pageNumber,
			@RequestParam(value="limit",required=true) Integer limit){
		return internService.getAllInterns(pageNumber,limit);
	}
	@PutMapping("/api/v1/intern")
	public ResponseEntity<Intern> updateIntern(@RequestBody Intern intern) {
		return internService.updateIntern(intern);
		
	}
	
	//Mappings for buddies

	@PostMapping("/api/v1/buddy")
	public Buddy addABuddy(@RequestBody Buddy buddy) {
	//	internService.saveAnIntern(buddy.getIntern());
		return buddyService.addABuddy(buddy);
	}
	
	@GetMapping("/api/v1/buddy")
	public List<Buddy> getAllBuddies(@RequestParam(value="pageNumber",required=true) Integer pageNumber,
			@RequestParam(value="limit",required=true) Integer limit){
		return buddyService.getAllBuddies(pageNumber,limit);
	}
	
	@PutMapping("/api/v1/buddy")
	public ResponseEntity<Buddy> updateBuddy(@RequestBody Buddy buddy) {
		return buddyService.updateBuddy(buddy);
	}
	
	//Mappings for Mentors
	
	@PostMapping("/api/v1/mentor")
	public Mentor addAMentor(@RequestBody Mentor mentor) {
		
		
		return mentorService.addAMentor(mentor);
	}
	
	@GetMapping("/api/v1/mentor")
	public List<Mentor> getAllMentors(@RequestParam(value="pageNumber",required=true) Integer pageNumber,
			@RequestParam(value="limit",required=true) Integer pageSize){
		
		return mentorService.getAllMentors(pageNumber,pageSize);
	}
	
	@PutMapping("/api/v1/mentor")
	public ResponseEntity<Mentor> updateMentor(@RequestBody Mentor mentor) {
		return mentorService.updateMentor(mentor);
	}
	
	
	
}
