package org.mac.sample.spring.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mac.sample.spring.boot.autoconfig.propertiesbind.bean.PersonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleSpringBootApplicationTests {
	@Autowired
	private PersonProperties user;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void contextLoads() {
		System.err.println(user);
	}

	@Test
	public void testMultipleConfigWaysToImportBeans() {
		System.err.println("applicationContext.containsBean(\"simpleService\"):"+applicationContext.containsBean("simpleService"));
		System.err.println("applicationContext.containsBean(\"simpleComponent\"):"+applicationContext.containsBean("simpleComponent"));
	}

}
