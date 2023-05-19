package com.example.farhad.utilsdemo;

import java.beans.PropertyDescriptor;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.SerializationUtils;
import org.springframework.util.SystemPropertyUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Search *Utils.java to find utils class
 */
@Slf4j
@SpringBootApplication
public class Application {


	@Bean 
	ApplicationListener<ApplicationReadyEvent> ready(DomainClass domainObject) {
		return event -> {
			Assert.notNull(domainObject.getListOfMap(), "the listOfMap can not be null!!");

			beanUtiles(domainObject);
			classUtils(DomainClass.class);
			systemPropertyUtils();
			fileCopyUtils();
			aop(domainObject);
			reflection();
			assertTest();
			collections();
			serialize();
		};
	}


	private void beanUtiles(DomainClass domainObject) {
		PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(domainObject.getClass());
		for (PropertyDescriptor propertyDescriptor : descriptors) {
			log.info("PropertyDescriptor: {}", propertyDescriptor.getName());
		}

	}

	private void classUtils(Class<?> clazz) {
		Constructor<?> clazzConstructor = ClassUtils.getConstructorIfAvailable(clazz);
		log.info("{} Constructor: {}", clazz.getSimpleName(), clazzConstructor);
		try {
			log.info("newinstance of {} : {}", clazz, clazzConstructor.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void systemPropertyUtils() {
		String resolvedText = SystemPropertyUtils.resolvePlaceholders("Home directory is: ${user.home}");
		log.info("Resolved text: {}", resolvedText);

		log.info("Resolved text: {}", SystemPropertyUtils.resolvePlaceholders("Home directory is: ${user.home11111}", true));
	}

	private void fileCopyUtils() {
		Resource resource =  new ClassPathResource("/application.properties");
		try (Reader reader = new InputStreamReader(resource.getInputStream())) {
			String contents = FileCopyUtils.copyToString(reader);
			log.info("application.properties: {}", contents);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void aop(DomainClass domainObject) {
		Class<?> targetClazz = AopUtils.getTargetClass(domainObject);
		log.info("Class<?> : {}", targetClazz);
		log.info("Is AOP proxy? {}", AopUtils.isAopProxy(domainObject));
		log.info("Is CGlib proxy? {}", AopUtils.isCglibProxy(domainObject));
		
	}

	private void reflection() {

		ReflectionUtils.doWithFields(DomainClass.class, field -> log.info("field: {}",field.toString()));
		ReflectionUtils.doWithMethods(DomainClass.class, method -> log.info("method: {}", method.toString()));
		
		Field listOfMap = ReflectionUtils.findField(DomainClass.class, "listOfMap");
		log.info("{}", Objects.requireNonNull(listOfMap).toString());

		ResolvableType resolvableType =  ResolvableType.forField(listOfMap);
		log.info("{}", resolvableType.toString());
	}

	private void assertTest() {
		int var = 10;
		Assert.state(var ==10 , () -> "var must be 10 , but was " + var);
		Assert.hasText("have text", "Should be non-null, non-empty String");

	}

	private void collections() {
		Collection<String> names = Arrays.asList("A", "DF", "CG");
		boolean contents = CollectionUtils.containsAny(names, Arrays.asList("DF"));
		Assert.state(contents, () -> "one or more names must be present in list:  " + names.toString());

		// contents = CollectionUtils.containsAny(names, Arrays.asList("DFB"));
		// Assert.state(contents, () -> "one or more names must be present in list:  " + names.toString());
	}

	private void serialize() {
		PojoClass pojo = new PojoClass(10, "Pojo class");
		byte[] serializedBytes =  SerializationUtils.serialize(pojo);
		PojoClass deserializedPojo = (PojoClass) SerializationUtils.deserialize(serializedBytes);
		Assert.state(pojo.getId() == deserializedPojo.getId() && pojo.getText().equals(deserializedPojo.getText()), 
									() -> "Class " + pojo.getClass().getSimpleName() + "  did not serialized correctly!!!!!!");

	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}
