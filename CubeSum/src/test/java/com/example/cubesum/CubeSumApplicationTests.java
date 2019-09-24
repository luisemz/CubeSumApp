package com.example.cubesum;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.cubesum.app.CubeSumNotFoundException;
import com.example.cubesum.data.StoreData;
import com.example.cubesum.data.StoreDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CubeSumApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private StoreDataRepository storeDataRepository;

	@Test
	public void putNumberTest() throws Exception {
		StoreData storeData = new StoreData();
		storeData.setNumberTest(5);

	    mockMvc.perform(post("/cubesum/start")
	            .content(objectMapper.writeValueAsString(storeData)))
	            .andExpect(status().isOk());
	    
	    StoreData findStoreData = storeDataRepository
	    		.findById(1L).orElseThrow(() -> new CubeSumNotFoundException(1L));
	    assertThat(findStoreData.getNumberTest()).isEqualTo(5);
	}

}
