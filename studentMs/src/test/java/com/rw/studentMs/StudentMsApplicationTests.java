package com.rw.studentMs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rw.studentMs.dto.CreateStudentDto;
import com.rw.studentMs.enums.EStudentDepartment;
import com.rw.studentMs.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@Testcontainers
class StudentMsApplicationTests {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13.3")
            .withDatabaseName("studentMs")
            .withUsername("postgres")
            .withPassword("Mugisha12!");
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;


    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private StudentRepository studentRepository;

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldCreateStudent() throws Exception {
        CreateStudentDto createStudentRequest = getCreateStudentRequest();
        String studentRequestString = objectMapper.writeValueAsString(createStudentRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/student")
                .contentType("application/json")
                .content(studentRequestString))
                .andExpect(status().isCreated());

        Assertions.assertEquals(1, studentRepository.findAll().size());

    }

    private CreateStudentDto getCreateStudentRequest() {
        return CreateStudentDto.builder()
                .phone("0788888888")
                .name("John Doe")
                .department(EStudentDepartment.MEDICINE)
                .email("doe@gmail.com")
                .build();
    }
}
