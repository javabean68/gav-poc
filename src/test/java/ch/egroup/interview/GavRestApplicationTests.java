package ch.egroup.interview;

import ch.egroup.interview.repository.MitarbeitRepository;
import ch.egroup.interview.security.RestSecurityConfig;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GavRestApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MitarbeitRepository personRepository;


	@BeforeEach
	public void deleteAllBeforeTests() throws Exception {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
		personRepository.deleteAll();
	}

	@Test
	@WithMockUser(username = "spring", password = "secret")
	public void shouldReturnRepositoryIndex() throws Exception {

		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
				jsonPath("$._links.people").exists());
	}

	@Test
	@WithMockUser(username = "spring", password = "secret")
	public void shouldCreateEntity() throws Exception {

		mockMvc.perform(post("/people").content(
				"{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
						status().isCreated()).andDo(print()).andExpect(
								header().string("Location", containsString("people/")));
	}

	@Test
	@WithMockUser(username = "spring", password = "secret")
	public void shouldRetrieveEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/people").content(
				"{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.firstName").value("Frodo")).andExpect(
						jsonPath("$.lastName").value("Baggins"));
	}

	@Test
	@WithMockUser(username = "spring", password = "secret")
	public void shouldQueryEntity() throws Exception {

		mockMvc.perform(post("/people").content(
				"{ \"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
						status().isCreated());

		mockMvc.perform(
				get("/people/search/findByLastName?name={name}", "Baggins")).andExpect(
						status().isOk()).andDo(print()).andExpect(
								jsonPath("$._embedded.people[0].firstName").value(
										"Frodo"));
	}

	@Test
	@WithMockUser(username = "spring", password = "secret")
	public void shouldUpdateEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/people").content(
				"{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).content(
				"{\"firstName\": \"Bilbo\", \"lastName\":\"Baggins\"}")).andExpect(
						status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.firstName").value("Bilbo")).andExpect(
						jsonPath("$.lastName").value("Baggins"));
	}

	@Test
	@WithMockUser(username = "spring", password = "secret")
	public void shouldPartiallyUpdateEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/people").content(
				"{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(
				patch(location).content("{\"firstName\": \"Bilbo Jr.\"}")).andExpect(
						status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.firstName").value("Bilbo Jr.")).andExpect(
						jsonPath("$.lastName").value("Baggins"));
	}

	@Test
	@WithMockUser(username = "spring", password = "secret")
	public void shouldDeleteEntity() throws Exception {

		MvcResult mvcResult = mockMvc.
				perform(post("/people").content(
				"{ \"firstName\": \"Bilbo\", \"lastName\":\"Baggins\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(delete(location)).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}

	@Test
	@WithMockUser(username = "spring", password = "secret")
	public void shouldCreateRegel() throws Exception {

		mockMvc.perform(post("/rules").content(
				"{\"description\": \"Regel1\"," +
				"\"composition_strategy\": \"replace\","
				+ " \"regel_type\": \"ArbeitzeitRegel\"" +
						"}")).andExpect(
				status().isCreated()).andDo(print()).andExpect(
				header().string("Location", containsString("arbeitzeitRegel/")));
	}
}