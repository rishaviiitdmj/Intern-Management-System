package io.javabrains.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.javabrains.Entity.Intern;
import io.javabrains.Entity.Mentor;
import io.javabrains.Repository.MentorRepository;

@Service
public class MentorService {

	@Autowired
	private MentorRepository mentorRepository;
	public List<Mentor> getAllMentors(Integer pageNumber,Integer pageSize){
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		Page<Mentor> page=mentorRepository.findAll(pageable);
		List<Mentor> mentors=page.getContent();
		for(Mentor m:mentors) {
			List<Intern> i=m.getInterns();
			i.forEach(y->{y.setBuddy(null);y.setMentor(null);});
		}
		
		return mentors;
	}
	public Mentor addAMentor(Mentor mentor) {
		mentorRepository.save(mentor);
		return mentor;
	}
	public ResponseEntity<Mentor> updateMentor(Mentor mentor) {
		Optional<Mentor> optionalMentor=mentorRepository.findById(mentor.getId());
		Mentor oldMentor=null;
		if(optionalMentor.isPresent()) {
			oldMentor=optionalMentor.get();
			oldMentor.setAddress(mentor.getAddress());
			oldMentor.setCreated(mentor.getCreated());
			oldMentor.setInterns(mentor.getInterns());
			oldMentor.setMobile(mentor.getMobile());
			oldMentor.setUpdated(mentor.getUpdated());
			oldMentor.setName(mentor.getName());
			
			mentorRepository.save(oldMentor);
			return ResponseEntity.ok(oldMentor);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}
