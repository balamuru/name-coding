package com.vgb.namecoding.service.reader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;

/**
 * Reads name information from a url
 */
public interface ReaderService {

    /**
     * Reads information from a url
     * @param url url address
     * @return a collection of strings
     * @throws IOException if exception encountered
     * @throws URISyntaxException if exception encountered
     */
    Collection<String> read(URL url) throws IOException, URISyntaxException;
}
