package io.javabrains.monitoring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.javabrains.Entity.Intern;
import io.javabrains.Repository.InternRepository;
import jakarta.servlet.http.HttpServletResponse;


@RestController
public class MonitoringController {

	@Autowired
	private ScoreRepository scoreRepository;
	@Autowired
	private SkillWiseScoreRepository skillWiseRepository;
	@Autowired
	private InternRepository internRepository;
	
	
	@PutMapping("/api/v1/score/upload")
	public ResponseEntity<String> uploadScore(@RequestParam("file") MultipartFile file) {
		
		if(file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please give a file");
		}

		
		BufferedReader br;
		try {
			
		     String line;
		     InputStream is = file.getInputStream();
		     br = new BufferedReader(new InputStreamReader(is));
		     int i=0;
		     while ((line = br.readLine()) != null) {
		        
		         if(i!=0) {
		        	 String[] s=line.split(",");
		        	 SkillWiseScore skillScore=new SkillWiseScore(Integer.valueOf(s[0]),
		        			 Integer.valueOf(s[1]),Integer.valueOf(s[2]),Integer.valueOf(s[3]),Integer.valueOf(s[4]),Integer.valueOf(s[5]));
		        	 skillWiseRepository.save(skillScore);
		        	 int internId=skillScore.getInternId();
		        	 Optional<Intern> newIntern=internRepository.findById(internId);
		        	 if(newIntern.isPresent()) {
		        		 Intern intern=newIntern.get();
			        	 String buddyName=intern.getBuddy().getName();
			        	 String mentorName=intern.getMentor().getName();
			        	 int total=skillScore.getJava()+skillScore.getJpa()+skillScore.getJunit()+skillScore.getSpringBoot()+skillScore.getSql();
			        	 Score score=new Score(internId,intern.getName(),buddyName,mentorName,total,0,skillScore);
			        	 scoreRepository.save(score);
		        	 }
		        	 else {
		        		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please add the intern in the DataBase first");
		        	 }
		         }
		         i++;
		     }
		     List<Score> scores=scoreRepository.findAll();
		     Collections.sort(scores,(s1,s2)->s2.getTotalScore()-s1.getTotalScore());
		     for(int j=0;j<scores.size();j++) {
		    	scores.get(j).setRank(j+1);
		    	scoreRepository.save(scores.get(j));
		     }

		  } catch (IOException e) {
		    System.err.println(e.getMessage());       
		  }
				

		
		return ResponseEntity.ok("The file has been read Succesfully");
		

	}
//	@GetMapping("/api/skill/score")
//	public List<SkillWiseScore> getScores(){
//		
//		return skillWiseRepository.findAll();
//
//	}
//	
	@Autowired
	CsvGenerator csvGenerator;
	@GetMapping("/api/v1/score/downloadtemplate")
	public void exportSkillScoreToCsv(HttpServletResponse response) throws IOException{
		response.setContentType("text/csv");
		
		response.addHeader("SkillWise Score", "attachment; filename=\"SkillWiseScore.csv\"");
		csvGenerator.writeSkillScores(skillWiseRepository.findAll(),response.getWriter());
	}
	
	@GetMapping("/api/v1/score/result")
	public List<Score> getResult(){
		return scoreRepository.findAll();
	}
	
}
