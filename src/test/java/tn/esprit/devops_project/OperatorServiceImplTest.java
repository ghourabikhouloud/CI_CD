package tn.esprit.devops_project;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OperatorServiceImplTest {

    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @BeforeEach
    public void setup() {
        // Configuration préalable, si nécessaire
    }

    @Test
    public void testRetrieveAllOperators() {
        // Given
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
        when(operatorRepository.findAll()).thenReturn(operators);

        // When
        List<Operator> result = operatorService.retrieveAllOperators();

        // Then
        assertEquals(2, result.size());
        assertEquals(operators, result);
    }

    @Test
    public void testAddOperator() {
        // Given
        Operator operator = new Operator();
        operator.setFname("John");
        operator.setLname("Doe");
        operator.setPassword("password");

        when(operatorRepository.save(operator)).thenReturn(operator);

        // When
        Operator result = operatorService.addOperator(operator);

        // Then
        assertEquals(operator, result);
    }

    @Test
    public void testDeleteOperator() {
        // Given
        Long operatorId = 1L;

        // When
        operatorService.deleteOperator(operatorId);

        // Then
        verify(operatorRepository, times(1)).deleteById(operatorId);
    }

    @Test
    public void testUpdateOperator() {
        // Given
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("John");
        operator.setLname("Doe");
        operator.setPassword("password");

        when(operatorRepository.save(operator)).thenReturn(operator);

        // When
        Operator result = operatorService.updateOperator(operator);

        // Then
        assertEquals(operator, result);
    }

    @Test
    public void testRetrieveOperator() {
        // Given
        Long operatorId = 1L;
        Operator operator = new Operator();
        operator.setIdOperateur(operatorId);
        operator.setFname("John");
        operator.setLname("Doe");
        operator.setPassword("password");

        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(operator));

        // When
        Operator result = operatorService.retrieveOperator(operatorId);

        // Then
        assertEquals(operator, result);
    }
}
