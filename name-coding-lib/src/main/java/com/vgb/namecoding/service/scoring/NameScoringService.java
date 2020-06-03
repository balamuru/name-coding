package com.vgb.namecoding.service.scoring;

/**
 * Calculates the score of an individual string entry
 */
public interface NameScoringService {

    /**
     * Scores a string
     * @param str input data
     * @return score
     */
    int score(String str);
}
