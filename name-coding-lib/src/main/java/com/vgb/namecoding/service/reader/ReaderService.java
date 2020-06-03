package com.vgb.namecoding.service.reader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * Reads name information from a url and returns a list of names
 */
public interface ReaderService {

    /**
     * Reads information from a url
     * @param url url address
     * @return a list of strings
     * @throws IOException if exception encountered
     * @throws URISyntaxException if exception encountered
     */
    List<String> read(URL url) throws IOException, URISyntaxException;
}
