package com.rorCitizenApplication.ROR.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CitizenSignupRequest extends BasicSignupRequest {
    @NotBlank(message = "Jurisdiction is required")
    private String jurisdictionId;
}
