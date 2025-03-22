package com.basis67.dbkits.service;

import com.basis67.dbkits.exception.CodeGeneratorException;

public interface CodeGeneratorService {
    String generateMyBatis() throws CodeGeneratorException;
}