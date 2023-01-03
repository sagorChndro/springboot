package com.sagor.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

	// modelMapper er jonno bean configuration class lage
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	// ei bean ta ke ProductServiceImp class e inject korte hobe bean ta ke pete
	// gele

}
