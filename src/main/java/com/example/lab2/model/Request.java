
package com.example.lab2.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @NotBlank(message = "uid обязателен")
    @Size(max = 32, message = "uid максимум 32 символа")
    private String uid;

    @NotBlank(message = "operationUid обязателен")
    @Size(max = 32, message = "operationUid максимум 32 символа")
    private String operationUid;

    private String systemName;

    @NotBlank(message = "systemTime обязателен")
    private String systemTime;

    private String source;

    @Min(value = 1, message = "communicationId минимум 1")
    @Max(value = 100000, message = "communicationId максимум 100000")
    private int communicationId;

    private int templateId;

    private int productCode;

    private int smsCode;
}
