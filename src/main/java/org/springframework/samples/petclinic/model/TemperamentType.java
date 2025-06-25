package org.springframework.samples.petclinic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "temperaments")
public class TemperamentType extends NamedEntity {

}
