package com.vgb.namecoding.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;

public interface NameReaderService {
    Collection<String> read(URL url) throws IOException, URISyntaxException;
}
