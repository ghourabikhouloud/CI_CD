package tn.esprit.devops_project.services.iservices;

import tn.esprit.devops_project.entities.Operator;

import java.util.List;


public interface IOperatorService {

	List<Operator> retrieveAllOperators(); // Change the return type to List<Operator>

	Operator addOperator(Operator operator);

	void deleteOperator(Long id);

	Operator updateOperator(Operator operator);

	Operator retrieveOperator(Long id);

}
