package hello.itemservice.repository.jpa;

import hello.itemservice.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 스프링 데이터 JPA
 * : JPA를 편리하게 사용할 수 있도록 도와주는 라이브러리이다.
 * 공통 인터페이스 기능
 * : JpaRepository 인터페이스만 상속받으면 스프링 데이터 JPA가 프록시 기술을 사용해서 구현 클래스를 만들어준다.
 * 쿼리 메서드 기능
 * : 메서드 이름을 분석해서 쿼리를 자동으로 만들고 실행해주는 기능 제공
 */
public interface SpringDataJpaItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemNameLike(String itemName);

    List<Item> findByPriceLessThanEqual(Integer price);

    // 쿼리 메서드(아래 메서드와 같은 기능 수행)
    // 이렇게 작성해두면 스프링 데이터 JPA는 메서드 이름을 분석해서 필요한 JPQL을 만들고 실행해준다.
    List<Item> findByItemNameLikeAndPriceLessThanEqual(String itemName, Integer price);

    // 쿼리 직접 실행
    // 직접 실행할 때는 파라미터를 명시적으로 바인딩해야 하는데, @Param("") 애노테이션을 사용하고,
    // 애노테이션의 값에 파라미터 이름을 주면 된다.
    @Query("select i from Item i where i.itemName like :itemName and i.price <= :price")
    List<Item> findItems(@Param("itemName") String itemName, @Param("price") Integer price);
}
