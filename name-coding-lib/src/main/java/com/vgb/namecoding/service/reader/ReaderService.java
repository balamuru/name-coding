package com.vgb.namecoding.service.reader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;

public interface ReaderService {
    Collection<String> read(URL url) throws IOException, URISyntaxException;
}
