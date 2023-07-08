package ashley.ashley_library;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = {"ashley.ashley_library.mapper"})
@SpringBootApplication
public class AshleyLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AshleyLibraryApplication.class, args);
	}

}
