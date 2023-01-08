package com.sagor.configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class BeanConfiguration {

	// modelMapper er jonno bean configuration class lage
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Resource getResource() {
		return new Resource() {

			@Override
			public InputStream getInputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long lastModified() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public URL getURL() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public URI getURI() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getFilename() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public File getFile() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean exists() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Resource createRelative(String relativePath) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long contentLength() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}

	// ei bean ta ke ProductServiceImp class e inject korte hobe bean ta ke pete
	// gele

}
