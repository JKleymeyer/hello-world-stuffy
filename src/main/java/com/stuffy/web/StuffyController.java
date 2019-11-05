package com.stuffy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.stuffy.business.Stuffy;
import com.stuffy.db.StuffyRepository;

@CrossOrigin
@RestController
@RequestMapping("/stuffies")
public class StuffyController {
	@Autowired
	private StuffyRepository stuffyRepo;

	// list - return all stuffies
	@GetMapping("/")
	public JsonResponse listStuffies() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(stuffyRepo.findAll());
			
		}
		catch(Exception e){
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	// get - return 1 stuffy from given id
	@GetMapping("/{id}")
	public JsonResponse getStuffy(@PathVariable int id) {
		JsonResponse jr = null;
		try{
			jr = JsonResponse.getInstance(stuffyRepo.findById(id));
		} catch (Exception e) {
			
		}
		return jr;
	}

	// demo of request parameters
//	@GetMapping ("")
//	public Stuffy createAStuffy(@RequestParam int id, @RequestParam String type, 
//			@RequestParam String color, @RequestParam String size, @RequestParam int limbs) {
//		Stuffy s = new Stuffy(id, type, color, size, limbs);
//		return s;
//	}

	// adding a new stuffy
	@PostMapping("/")
	public JsonResponse addStuffy(@RequestBody Stuffy s) {
		JsonResponse jr = null;
		try{
			jr = JsonResponse.getInstance(stuffyRepo.save(s));
		} catch (Exception e) {
			
		}
		return jr;
	}

	// update a stuffy
	@PutMapping("/")
	public JsonResponse updateStuffy(@RequestBody Stuffy s) {
		JsonResponse jr = null;
		try{
			if (stuffyRepo.existsById(s.getId())) {
			jr = JsonResponse.getInstance(stuffyRepo.save(s));
			} else {
				//record doesn't exist
				jr = JsonResponse.getInstance("Error updating Stuffy. Id " +s.getId()+ " doesn't exist");
			}
		} catch (Exception e) {
			
		}
		return jr;
	}

	// Delete a stuffy
	@DeleteMapping("/{id}")
	public JsonResponse deleteStuffy(@PathVariable int id) {
		JsonResponse jr = null;
		try{
			if (stuffyRepo.existsById(id)) {
				stuffyRepo.deleteById(id);
			jr = JsonResponse.getInstance("Delete succesful!");
			} else {
				//record doesn't exist
				jr = JsonResponse.getInstance("Error deleting Stuffy. Id " +id+ " doesn't exist");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
			
		}
		return jr;
	}

}
