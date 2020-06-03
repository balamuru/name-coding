package com.vgb.namecoding.service.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of name scoring service that scores based on the sum
 * of the alphabetical value of each letter (A=1, B=2, C=3, etc...)
 */
public class FirstNameScoringService implements NameScoringService {
    private static int BASELINE_ALPHABET = (int) 'A' - 1;
    private static Logger logger = LoggerFactory.getLogger(FirstNameScoringService.class);

    @Override
    public int score(String str) {
        return str == null || str.trim().isEmpty() ? 0 : scoreValidName(str);
    }

    private int scoreValidName(String str) {
        // subtract int value of 'A' from the uppercase representation of each character
        // and sum the results of this operation across all chars in the string
        if (logger.isDebugEnabled()) {
            logger.debug("Going to score word: " + str);
        }
        return str.trim().toUpperCase().chars().map(operand -> operand - BASELINE_ALPHABET).sum();
    }

}
