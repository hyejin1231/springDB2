package hello.itemservice;

import hello.itemservice.config.*;
import hello.itemservice.repository.ItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

//@Import(MemoryConfig.class) // MemoryConfig 파일을 설정 파일로 사용
//@Import(JdbcTemplateV1Config.class)
//@Import(JdbcTemplateV2Config.class)
@Import({JdbcTemplateV3Config.class})
@SpringBootApplication(scanBasePackages = "hello.itemservice.web") // 컨트롤러만 컴포넌트 스캔하도록
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	@Bean
	@Profile("local") // local이라는 프로필이 사용되는 경우에만 testDateInit 스프링 빈 등록
	public TestDataInit testDataInit(ItemRepository itemRepository) {
		return new TestDataInit(itemRepository);
	}

}

