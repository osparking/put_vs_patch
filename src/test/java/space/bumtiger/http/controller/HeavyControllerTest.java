package space.bumtiger.http.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import space.bumtiger.http.domain.HeavyRepository;
import space.bumtiger.http.domain.HeavyResource;

@WebMvcTest
class HeavyControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private HeavyRepository heavyRepository;
	
	@Test
	void testPut() throws JsonProcessingException, Exception {
		var result = mockMvc
				.perform(put("/heavyresource")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(
								new HeavyResource(1, "작업화 결합대", "중앙로 11번지"))))
				.andExpect(status().isOk()).andReturn();
		
		String message = result.getResponse().getContentAsString();
		assertEquals(message, "자원 저장됨");
	}

}
