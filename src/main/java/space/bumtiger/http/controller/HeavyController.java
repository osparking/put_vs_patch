package space.bumtiger.http.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import space.bumtiger.http.domain.HeavyRepository;
import space.bumtiger.http.domain.HeavyResource;
import space.bumtiger.http.domain.ResourceIDAddress;

@RestController
@RequestMapping("/heavyresource")
@RequiredArgsConstructor
public class HeavyController {
	@Autowired
	private HeavyRepository heavyRepository;

	@PatchMapping
	public ResponseEntity<?> patchResource(
			@RequestBody ResourceIDAddress idAddress) {
		Optional<HeavyResource> resource = heavyRepository
				.findById(idAddress.getId());
		if (resource.isPresent()) {
			resource.get().setAddress(idAddress.getAddress());
			heavyRepository.save(resource.get());
		} 
		return ResponseEntity.of(resource);
	}

	@PutMapping
	public ResponseEntity<?> saveResource(
			@RequestBody HeavyResource heavyResource) {
		heavyRepository.save(heavyResource);
		return ResponseEntity.ok("자원 저장됨");
	}

	@GetMapping
	public Iterable<HeavyResource> getAllHeavyResources() {
		return heavyRepository.findAll();
	}

}
