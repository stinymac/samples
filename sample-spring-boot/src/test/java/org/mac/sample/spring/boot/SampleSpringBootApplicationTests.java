package org.mac.sample.spring.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mac.sample.spring.boot.config.propertiesbind.bean.PersonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleSpringBootApplicationTests {
	@Autowired
	private PersonProperties user;


	@Test
	public void contextLoads() {
		System.err.println(user);
	}

}
