
package com.example.lab2.controller;

import com.example.lab2.exception.UnsupportedCodeException;
import com.example.lab2.exception.ValidationFailedException;
import com.example.lab2.model.Request;
import com.example.lab2.model.Response;
import com.example.lab2.service.ValidationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;

@RestController
public class MyController {

    private final ValidationService validationService;

    public MyController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping("/feedback")
    public ResponseEntity<Response> feedback(
            @Valid @RequestBody Request request,
            BindingResult bindingResult) {

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(Instant.now().toString())
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();

        try {

            validationService.isValid(bindingResult);

            if ("123".equals(request.getUid())) {
                throw new UnsupportedCodeException(
                        "Не поддерживаемая ошибка");
            }

        } catch (ValidationFailedException e) {

            response.setCode("failed");
            response.setErrorCode("ValidationException");
            response.setErrorMessage(
                    "Ошибка валидации: " + e.getMessage());

            return new ResponseEntity<>(
                    response, HttpStatus.BAD_REQUEST);

        } catch (UnsupportedCodeException e) {

            response.setCode("failed");
            response.setErrorCode("UnsupportedCodeException");
            response.setErrorMessage(e.getMessage());

            return new ResponseEntity<>(
                    response, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            response.setCode("failed");
            response.setErrorCode("UnknownException");
            response.setErrorMessage(
                    "Произошла непредвиденная ошибка");

            return new ResponseEntity<>(
                    response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
