package tn.esprit.devops_project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tn.esprit.devops_project.controllers.OperatorController;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.services.iservices.IOperatorService;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OperatorControllerTest {

    @Mock
    private IOperatorService operatorService;

    @InjectMocks
    private OperatorController operatorController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Configure MockMvc pour tester le contrôleur OperatorController
        mockMvc = standaloneSetup(operatorController).build();
    }

    @Test
    public void testGetOperators() throws Exception {
        Operator operator1 = new Operator();
        operator1.setIdOperateur(1L);
        operator1.setFname("John");
        operator1.setLname("Doe");
        operator1.setPassword("password");

        Operator operator2 = new Operator();
        operator2.setIdOperateur(2L);
        operator2.setFname("Jane");
        operator2.setLname("Smith");
        operator2.setPassword("password");

        List<Operator> operators = Arrays.asList(operator1, operator2);

        // Simule la méthode retrieveAllOperators() du service
        when(operatorService.retrieveAllOperators()).thenReturn(operators);

        // Effectue un appel GET à "/operator" et vérifie le résultat
        mockMvc.perform(get("/operator"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"idOperateur\":1,\"fname\":\"John\",\"lname\":\"Doe\",\"password\":\"password\"}," +
                        "{\"idOperateur\":2,\"fname\":\"Jane\",\"lname\":\"Smith\",\"password\":\"password\"}]"));

        // Vérifie que la méthode retrieveAllOperators() a été appelée une fois
        verify(operatorService, times(1)).retrieveAllOperators();
    }

    @Test
    public void testRetrieveOperator() throws Exception {
        Long operatorId = 1L;
        Operator operator = new Operator();
        operator.setIdOperateur(operatorId);
        operator.setFname("John");
        operator.setLname("Doe");
        operator.setPassword("password");

        // Simule la méthode retrieveOperator() du service
        when(operatorService.retrieveOperator(operatorId)).thenReturn(operator);

        // Effectue un appel GET à "/operator/" + operatorId et vérifie le résultat
        mockMvc.perform(get("/operator/" + operatorId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"idOperateur\":1,\"fname\":\"John\",\"lname\":\"Doe\",\"password\":\"password\"}"));

        // Vérifie que la méthode retrieveOperator() a été appelée une fois
        verify(operatorService, times(1)).retrieveOperator(operatorId);
    }

    @Test
    public void testAddOperator() throws Exception {
        Operator operator = new Operator();
        operator.setFname("John");
        operator.setLname("Doe");
        operator.setPassword("password");

        // Simule la méthode addOperator() du service
        when(operatorService.addOperator(any(Operator.class))).thenReturn(operator);

        // Effectue un appel POST à "/operator" et vérifie le résultat
        mockMvc.perform(post("/operator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fname\":\"John\",\"lname\":\"Doe\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"fname\":\"John\",\"lname\":\"Doe\",\"password\":\"password\"}"));

        // Vérifie que la méthode addOperator() a été appelée une fois
        verify(operatorService, times(1)).addOperator(any(Operator.class));
    }

//    @Test
//    public void testRemoveOperator() throws Exception {
//        Long operatorId = 1L;
//
//        // Simulate the deleteOperator() method of the service
//        doNothing().when(operatorService).deleteOperator(operatorId);
//
//        // Perform a DELETE request to "/operator/" + operatorId and verify the result
//        mockMvc.perform(delete("/operator/" + operatorId))
//                .andExpect(status().isNoContent());
//
//        // Verify that the deleteOperator() method was called once
//        verify(operatorService, times(1)).deleteOperator(operatorId);
//    }


    @Test
    public void testModifyOperator() throws Exception {
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("John");
        operator.setLname("Doe");
        operator.setPassword("password");

        // Simule la méthode updateOperator() du service
        when(operatorService.updateOperator(any(Operator.class))).thenReturn(operator);

        // Effectue un appel PUT à "/operator" et vérifie le résultat
        mockMvc.perform(put("/operator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idOperateur\":1,\"fname\":\"John\",\"lname\":\"Doe\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"idOperateur\":1,\"fname\":\"John\",\"lname\":\"Doe\",\"password\":\"password\"}"));

        // Vérifie que la méthode updateOperator() a été appelée une fois
        verify(operatorService, times(1)).updateOperator(any(Operator.class));
    }
}
