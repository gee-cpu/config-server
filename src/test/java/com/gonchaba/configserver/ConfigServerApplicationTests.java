package com.gonchaba.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(properties = {
		"spring.profiles.active=test",

})
@TestPropertySource(locations = "classpath:application-test.yaml")

public class ConfigServerApplicationTests {

	@Test
	void contextLoads() {
	}

}
