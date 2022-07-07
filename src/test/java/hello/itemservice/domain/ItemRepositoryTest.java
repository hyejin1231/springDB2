package hello.itemservice.domain;

import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import hello.itemservice.repository.memory.MemoryItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 테스트에서 중요한 점은 격리성이다!!
 */
@SpringBootTest //@SpringBootTest는 @SpringBootApplication 를 찾아서 설정으로 사용한다.
class ItemRepositoryTest {

    /**
     * 테스트를 할 때에는 인터페이스를 테스트 하는 것이 좋다.
     * 왜냐하면 인터페이스를 대상으로 테스트하면 향후 다른 구현체로 변경되었을 때 해당 구현체가 잘 동작하는지 같은 테스트로 편리하게 검증할 수 있다.
     */
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PlatformTransactionManager transactionManager; // 트랜잭션 매니저는 스프링부트가 자동으로 빈으로 등록해준다!
    TransactionStatus status;

    @BeforeEach
    void beforeEach() {
        // 트랜잭션 시작
         status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    /**
     * afterEach()
     * : 테스트는 서로 영향을 주면 안되기 때문에 각각의 테스트가 끝나고 나면 저장한 데이터를 제거해야한다.
     * afterEach는 실행이 끝나는 시점에 호출된다.
     *
     */
    @AfterEach
    void afterEach() {
        //MemoryItemRepository 의 경우 제한적으로 사용 -> itemRepository에는 clearStore() 가 없기 때문이다.
        if (itemRepository instanceof MemoryItemRepository) {
            ((MemoryItemRepository) itemRepository).clearStore();
        }

        // 트랜잭션 롤백 (테스트 끝나고나면 ROLLBACK
        transactionManager.rollback(status);
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId()).get();
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        ItemUpdateDto updateParam = new ItemUpdateDto("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        //then
        Item findItem = itemRepository.findById(itemId).get();
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

    @Test
    void findItems() {
        //given
        Item item1 = new Item("itemA-1", 10000, 10);
        Item item2 = new Item("itemA-2", 20000, 20);
        Item item3 = new Item("itemB-1", 30000, 30);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

        //둘 다 없음 검증
        test(null, null, item1, item2, item3);
        test("", null, item1, item2, item3);

        //itemName 검증
        test("itemA", null, item1, item2);
        test("temA", null, item1, item2); // 부분검색도 되야 한다!
        test("itemB", null, item3);

        //maxPrice 검증
        test(null, 10000, item1);

        //둘 다 있음 검증
        test("itemA", 10000, item1);
    }

    void test(String itemName, Integer maxPrice, Item... items) {
        List<Item> result = itemRepository.findAll(new ItemSearchCond(itemName, maxPrice));
        assertThat(result).containsExactly(items); // containsExactly : 순서도 다 맞아야 한다!!
    }
}
