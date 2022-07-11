package hello.itemservice.repository.mybatis;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * MyBatis 매핑 XML을 호출해주는 매퍼 인터페이스이다.
 * @Mapper 애노테이션을 붙여줘야 MyBatis에서 인식할 수 있다.
 * 이 인터페이스의 메서드를 호출하면 xml의 해당 SQL을 실행하고 결과를 돌려준다.
 */
@Mapper
public interface ItemMapper {

    void save(Item item);

    // 파라미터가 두개가 넘어가는 경우에는 @Param을 넣어줘야 한다.
    void update(@Param("id") Long id, @Param("updateParam") ItemUpdateDto updateParam);

    Optional<Item> findById(Long id);

    List<Item> findAll(ItemSearchCond itemSearch);

}
