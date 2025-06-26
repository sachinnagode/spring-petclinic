package org.springframework.samples.petclinic.dto.visit;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VisitDTO {

	private LocalDate date;

	private String description;

}
