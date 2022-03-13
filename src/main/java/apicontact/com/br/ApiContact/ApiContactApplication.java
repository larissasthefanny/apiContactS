package apicontact.com.br.ApiContact;

import jdk.jfr.Description;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Description("crud")
public class ApiContactApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiContactApplication.class, args);
	}

}
