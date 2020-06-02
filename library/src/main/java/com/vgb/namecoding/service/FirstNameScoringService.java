package com.vgb.namecoding.service;

import org.springframework.stereotype.Service;

/**
 * Implementation of name scoring service that scores based on the sum
 * of the alphabetical value of each letter (A=1, B=2, C=3, etc...)
 */
@Service
public class FirstNameScoringService implements NameScoringService {
    private static int BASELINE_ALPHABET = (int) 'A' - 1;

    @Override
    public int score(String name) {
        return name == null || name.trim().isEmpty() ? 0 : scoreValidName(name);
    }

    private int scoreValidName(String name) {
        return name.trim().toUpperCase().chars().map(operand -> operand - BASELINE_ALPHABET).sum();
    }

}
