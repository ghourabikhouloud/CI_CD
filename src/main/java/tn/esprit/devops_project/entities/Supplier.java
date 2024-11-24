package tn.esprit.devops_project.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSupplier;
	String code;
	String label;
	@Enumerated(EnumType.STRING)
	SupplierCategory supplierCategory;
	@OneToMany(mappedBy="supplier")
	@JsonIgnore
	Set<Invoice> invoices;
    

	
}
