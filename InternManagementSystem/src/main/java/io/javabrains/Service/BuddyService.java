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
import io.javabrains.Repository.BuddyRepository;

@Service
public class BuddyService {

	@Autowired
	private BuddyRepository buddyRepository;
	public Buddy addABuddy(Buddy buddy) {
		buddyRepository.save(buddy);
		return buddy;
	}
	public List<Buddy> getAllBuddies(Integer pageNumber,Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		Page<Buddy> page=buddyRepository.findAll(pageable);
		List<Buddy> buddies=page.getContent();
		for(Buddy b:buddies) {
			List<Intern> i=b.getIntern();
			i.forEach(y->{y.setBuddy(null);y.setMentor(null);});
		}
		return buddies;
	}
	public ResponseEntity<Buddy> updateBuddy(Buddy buddy) {
		
		Optional<Buddy> optionalBuddy=buddyRepository.findById(buddy.getId());
		Buddy oldBuddy=null;
		if(optionalBuddy.isPresent()) {
			oldBuddy=optionalBuddy.get();
			oldBuddy.setAddress(buddy.getAddress());
			oldBuddy.setCreated(buddy.getCreated());
			List<Intern> interns=oldBuddy.getIntern();
			interns.addAll(buddy.getIntern());
			oldBuddy.setIntern(interns);
			
			oldBuddy.setMobile(buddy.getMobile());
			oldBuddy.setName(buddy.getName());
			
			oldBuddy.setUpdated(buddy.getUpdated());
			buddyRepository.save(oldBuddy);

		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(oldBuddy);
	}
}
