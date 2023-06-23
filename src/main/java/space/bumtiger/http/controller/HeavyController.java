package space.bumtiger.http.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	private static final Logger logger = LoggerFactory
			.getLogger(HeavyController.class);
	@Autowired
	private HeavyRepository heavyRepository;

	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> partialUpdateGeneric(
			@RequestBody Map<String, Object> updates, 
			@PathVariable("id") String id) {
		Optional<HeavyResource> resource = heavyRepository
				.findById(Integer.parseInt(id));
		if (resource.isPresent()) {
			updates.forEach((key, val) -> {
				switch (key) {
				case "name":
					resource.get().setName((String) val);
					break;
				case "address":
					resource.get().setAddress((String) val);
					break;
				}
			});
			heavyRepository.save(resource.get());
		}
		return ResponseEntity.of(resource);
	}

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
