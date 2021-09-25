package br.com.priscilasanfer.zedelivery.partner;

import br.com.priscilasanfer.zedelivery.factory.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PartnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PartnerRepository repository;

    @BeforeEach
    public void beforeAll() {
        repository.save(Factory.createPartner());
    }

    @Test
    public void insertShouldSavePartnerDTO() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(Factory.createPartnerDTO());

        mockMvc.perform(post("/partners")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.trading_name").value("Adega da Cerveja"))
                .andExpect(jsonPath("$.owner_name").value(("Zé da Silva")))
                .andExpect(jsonPath("$.document").value("43.693.436/0001-16"))
                .andExpect(jsonPath("$.coverage_area").exists())
                .andExpect(jsonPath("$.address").exists());
    }

    @Test
    public void findByIdShouldReturnPartnerWhenExists() throws Exception {
        mockMvc.perform(get("/partners/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.trading_name").value("Bar Morumbi town"))
                .andExpect(jsonPath("$.owner_name").value("Felipe Rudenberg"))
                .andExpect(jsonPath("$.document").value("71.028.255/0001-00"))
                .andExpect(jsonPath("$.coverage_area").exists())
                .andExpect(jsonPath("$.address").exists());
    }

    @Test
    public void findByIdShouldReturn404WhenPartnerDoesNotExist() throws Exception {
        mockMvc.perform(get("/partners/{id}", 100)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("The Partner with id informed was not found"));
    }

    @Test
   public void findByAddressShouldReturnNearestPartnerWhenLocationIsUnderCoverageArea() throws Exception {
        mockMvc.perform(get("/partners?lat=-23.632445092532187&long=-46.737563574385504")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.trading_name").value("Bar Morumbi town"))
                .andExpect(jsonPath("$.owner_name").value("Felipe Rudenberg"))
                .andExpect(jsonPath("$.document").value("71.028.255/0001-00"))
                .andExpect(jsonPath("$.coverage_area").exists())
                .andExpect(jsonPath("$.address").exists());
    }

    @Test
    public void findByAddressShouldReturn404WhenTheAddressIsNoCoveredByAnyPartner() throws Exception {
        mockMvc.perform(get("/partners?lat=-25.632445092532187&long=-45.737563574385504")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("There is no partner available in the researched area"));
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }
}
