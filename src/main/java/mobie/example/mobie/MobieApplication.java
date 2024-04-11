package mobie.example.mobie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableCaching
public class MobieApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MobieApplication.class, args);
	}
	
	@Bean
	public Cloudinary cloudinary() {
		Cloudinary c = new Cloudinary(ObjectUtils.asMap(
				
				"cloud_name", "dulramupj",
				"api_key", "854114746725916",
				"api_secret", "rzpp70b3NRu8KCT0LCieJYXeAJ0",
				"secure",true
				));
		
		return c;
	}

}
