package com.project.DisneyApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Data
@Builder
public class Mensaje {
	
	private String mensaje;
	
}
