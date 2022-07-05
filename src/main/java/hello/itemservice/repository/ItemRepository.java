package hello.itemservice.repository;

import hello.itemservice.domain.Item;

import java.util.List;
import java.util.Optional;

/**
 * Repository 에서 ItemUpdateDto, ItemSearchCond 를 사용하고 있기 때문에
 * Repository 패키지 안에 넣는것이 이번 경우에는 맞다.
 * Dto를 제공하는 마지막 단이 어딘가? 로 구분하면 되는데 애매하면 그냥 패키지를 따로 만들어서 사용하면 된다.
 */
public interface ItemRepository {

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto updateParam);

    Optional<Item> findById(Long id); // 한 건 조회

    List<Item> findAll(ItemSearchCond cond); // 전체 조회 (검색 조건이 들어감)

}
