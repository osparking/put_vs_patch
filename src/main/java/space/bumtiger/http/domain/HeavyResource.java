package space.bumtiger.http.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class HeavyResource {
	public HeavyResource(String name, String address) {
		this.name = name;
		this.address = address;
	}

	@Id
	private Integer id;
	private String name;
	private String address;
	// ...
}
