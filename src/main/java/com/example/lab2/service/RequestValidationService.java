
package com.example.lab2.service;

import com.example.lab2.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult)
            throws ValidationFailedException {

        if (bindingResult.hasErrors()) {

            StringBuilder errors = new StringBuilder();

            bindingResult.getFieldErrors().forEach(error -> {
                errors.append(error.getDefaultMessage())
                        .append("; ");
            });

            throw new ValidationFailedException(errors.toString());
        }
    }
}
