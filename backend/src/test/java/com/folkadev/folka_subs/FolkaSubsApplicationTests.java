package com.folkadev.folka_subs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class FolkaSubsApplicationTests {

	@Test
	void contextLoads() {
	}

}
