package com.vgb.namecoding.service.scoring;

/**
 * Implementation of name scoring service that scores based on the sum
 * of the alphabetical value of each letter (A=1, B=2, C=3, etc...)
 */

public class FirstNameScoringService implements NameScoringService {
    private static int BASELINE_ALPHABET = (int) 'A' - 1;

    @Override
    public int score(String str) {
        return str == null || str.trim().isEmpty() ? 0 : scoreValidName(str);
    }

    private int scoreValidName(String str) {
        return str.trim().toUpperCase().chars().map(operand -> operand - BASELINE_ALPHABET).sum();
    }

}
