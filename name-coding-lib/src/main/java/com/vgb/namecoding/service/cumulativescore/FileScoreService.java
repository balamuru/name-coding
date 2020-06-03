package com.vgb.namecoding.service.cumulativescore;

import java.net.URL;

/**
 * Calculate the total score of all entries in a file
 */
public interface FileScoreService {
    /**
     * Calculates the total cumulative score of all entries in a file
     * @param url file url
     * @return total score
     * @throws Exception if an error is encountered
     */
    long compute(URL url) throws Exception;
}
