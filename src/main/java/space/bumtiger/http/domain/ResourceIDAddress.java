package space.bumtiger.http.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceIDAddress {
	private Integer id;
	private String address;
}
