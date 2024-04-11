package mobie.example.mobie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@SpringBootApplication
public class MobieApplication {

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
