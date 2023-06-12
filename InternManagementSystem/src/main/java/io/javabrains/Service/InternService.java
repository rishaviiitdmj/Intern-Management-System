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

import io.javabrains.Entity.Buddy;
import io.javabrains.Entity.Intern;
import io.javabrains.Entity.Mentor;
import io.javabrains.Repository.InternRepository;


@Service
public class InternService {

	@Autowired
	InternRepository internRepository;
	public void saveAnIntern(Intern intern) {
		internRepository.save(intern);
	}
	public List<Intern> getAllInterns(Integer pageNumber,Integer pageSize){
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		Page<Intern> page=internRepository.findAll(pageable);
		List<Intern> interns=page.getContent();
		for(Intern i:interns) {
			Buddy b=i.getBuddy();
			Mentor m = i.getMentor();
			b.setIntern(null);
			m.setInterns(null);
		}
		return interns;
	}
	public ResponseEntity<Intern> updateIntern(Intern intern) {
		// TODO Auto-generated method stub
		Optional<Intern> optionalIntern=internRepository.findById(intern.getInternId());
		Intern oldIntern=null;
		if(optionalIntern.isPresent()) {
			oldIntern=optionalIntern.get();
			oldIntern.setAddress(intern.getAddress());
			oldIntern.setBuddy(intern.getBuddy());
			oldIntern.setCreated(intern.getCreated());
			
			oldIntern.setMentor(intern.getMentor());
			oldIntern.setMobile(intern.getMobile());
			oldIntern.setName(intern.getName());
			oldIntern.setUpdated(intern.getUpdated());
			internRepository.save(oldIntern);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(oldIntern);

	}
	
}
