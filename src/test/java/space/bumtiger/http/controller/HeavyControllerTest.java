package space.bumtiger.http.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import space.bumtiger.http.config.TestConfig;
import space.bumtiger.http.domain.HeavyRepository;
import space.bumtiger.http.domain.HeavyResource;
import space.bumtiger.http.domain.ResourceIDAddress;

@WebMvcTest
class HeavyControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private HeavyRepository heavyRepository;
	
	final static Logger logger = LoggerFactory.getLogger(TestConfig.class);

	@Test
	void testPatch() throws JsonProcessingException, Exception {
		mockMvc.perform(put("/heavyresource")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(
						new HeavyResource(1, "탱크", "육군 전승부대 1번지"))))
				.andExpect(status().isOk())
		.andDo(result->
		mockMvc.perform(patch("/heavyrecource")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper
						.writeValueAsString(new ResourceIDAddress(1, "11번가"))))
				.andExpect(status().isOk()));
	}

	@Test
	void testPut() throws JsonProcessingException, Exception {
		var result = mockMvc
				.perform(
						put("/heavyresource").contentType(MediaType.APPLICATION_JSON_VALUE)
								.content(objectMapper.writeValueAsString(
										new HeavyResource(1, "작업화 결합대", "중앙로 11번지"))))
				.andExpect(status().isOk()).andReturn();

		String message = result.getResponse().getContentAsString();
		assertEquals(message, "자원 저장됨");
	}

}
