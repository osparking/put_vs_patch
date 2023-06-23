package space.bumtiger.http.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import space.bumtiger.http.domain.HeavyRepository;
import space.bumtiger.http.domain.HeavyResource;

@RestController
@RequestMapping("/heavyresource")
@RequiredArgsConstructor
public class HeavyController {
	@Autowired
	private HeavyRepository heavyRepository;

	@PutMapping
	public ResponseEntity<?> saveResource(
			@RequestBody HeavyResource heavyResource) {
		heavyRepository.save(heavyResource);
		return ResponseEntity.ok("자원 저장됨");
	}

}
