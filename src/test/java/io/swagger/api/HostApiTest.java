package io.swagger.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
public class HostApiTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testHostStatus() throws Exception {
		invokeHost().andExpect(status().is(200));
	}

	@Test
	public void testHostName() throws Exception {
		invokeHost().andExpect(jsonPath("$.name", is(localHostName())));
	}

	@Test
	public void testHostIp() throws Exception {
		invokeHost()
				.andExpect(jsonPath("$.ip", is(localHostIp())));
	}

	private ResultActions invokeHost() throws Exception {
		return mvc.perform(get("/host").accept(MediaType.APPLICATION_JSON));
	}
	
	private String localHostName() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostName();
	}
	
	private String localHostIp() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}
}
