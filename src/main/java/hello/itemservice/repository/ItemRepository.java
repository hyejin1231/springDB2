package hello.itemservice.repository;

import hello.itemservice.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto updateParam);

    Optional<Item> findById(Long id); // 한 건 조회

    List<Item> findAll(ItemSearchCond cond); // 전체 조회 (검색 조건이 들어감)

}
