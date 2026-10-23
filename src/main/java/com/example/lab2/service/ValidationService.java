
package com.example.lab2.service;

import com.example.lab2.exception.ValidationFailedException;
import org.springframework.validation.BindingResult;

public interface ValidationService {

    void isValid(BindingResult bindingResult)
            throws ValidationFailedException;
}
