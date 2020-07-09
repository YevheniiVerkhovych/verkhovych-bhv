package my.task.test.config;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("my.task.test")
public class DataAppConfig implements WebMvcConfigurer {

//	@Bean
//	public Map<String, String> myDataSource() {
//
//		Map<String, String> myMap = new HashMap<>();
//
//		return myMap;
//	}	
}









