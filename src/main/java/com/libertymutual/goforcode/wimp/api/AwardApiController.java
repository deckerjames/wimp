//package com.libertymutual.goforcode.wimp.api;
//
//import java.util.List;
//
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.libertymutual.goforcode.wimp.models.Actor;
//import com.libertymutual.goforcode.wimp.models.Award;
//import com.libertymutual.goforcode.wimp.models.Award;
//import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
//import com.libertymutual.goforcode.wimp.repositories.AwardRepository;
//
//import javassist.NotFoundException;
//
//@RestController
//@RequestMapping("/api/awards")
//public class AwardApiController {
//	
//	private AwardRepository awardRepo;
//	private ActorRepository actorRepo;
//	
//	public AwardApiController(AwardRepository awardRepo, ActorRepository actorRepo) {
//		this.awardRepo = awardRepo;
//		this.actorRepo = actorRepo;		
//	}
//	
//	@GetMapping("")
//	public List<Award> getAll() {
//		return awardRepo.findAll();	
//	}
//	
//	@GetMapping("{id}")
//	public Award getOne(@PathVariable long id) throws StuffNotFoundException {
//		Award award = awardRepo.findOne(id);
//		if (award == null) {
//			throw new StuffNotFoundException();
//		}
//		return award;
//	}
//	
//	@DeleteMapping("{id}")
//	public Award deleteOne(@PathVariable long id) {
//		try {
//			Award award = awardRepo.findOne(id);
//			awardRepo.delete(id);
//			return award;
//		} catch (EmptyResultDataAccessException erdae) {
//			return null;			
//		}
//	}
//	
//	@PostMapping("{awardId}/actors")
//	public Award associateAnActor(@PathVariable long awardId, @RequestBody Actor actor) {
//		Award award = awardRepo.findOne(awardId);
//		actor = actorRepo.findOne(actor.getId());		
//		award.addActor(actor);
//		awardRepo.save(award);		
//		return award;
//	}
//		
//	@PostMapping("")
//	public Award create(@RequestBody Award award) {
//		return awardRepo.save(award);		
//	} 
//	
//	@PutMapping("{id}")
//	public Award update(@RequestBody Award award, @PathVariable long id) {
//		award.setId(id);
//		return awardRepo.save(award);
//	}
//}
