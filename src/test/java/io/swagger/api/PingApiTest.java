package io.swagger.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PingApiTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testPingStatus() throws Exception {
		invoke().andExpect(status().is(200));
	}

	@Test
	public void testPingHost() throws Exception {
		invoke().andExpect(jsonPath("$.host", is("localhost")));
	}

	@Test
	public void testPingReachble() throws Exception {
		invoke().andExpect(jsonPath("$.reachble", is(true)));
	}

	private ResultActions invoke() throws Exception {
		return mvc.perform(get("/ping/localhost").accept(MediaType.APPLICATION_JSON));
	}

}
