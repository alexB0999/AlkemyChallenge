package com.project.DisneyApi.emailSender.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChangePasswordDTO {

  @NotBlank
  private String password;

  @NotBlank
  private String confirmPassword;

  @NotBlank
  private String tokenPassword;

}
