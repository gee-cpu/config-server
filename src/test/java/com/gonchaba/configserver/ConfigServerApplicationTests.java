package com.gonchaba.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.profiles.active=test",

})
public class ConfigServerApplicationTests {

	@Test
	void contextLoads() {
	}

}
