package com.vgb.namecoding;

import com.vgb.namecoding.service.ScoreComputationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class NameCodingLibraryApplicationTests {

	@Autowired
	ScoreComputationService scoreComputationService;

	@Test
	void testFoo() throws  Exception {

		scoreComputationService.compute(new File("/home/vinayb/Downloads/sample-large.txt").toURI().toURL());

	}

	@Test
	void contextLoads() {
	}


}
