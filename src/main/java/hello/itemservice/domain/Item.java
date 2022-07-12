package hello.itemservice.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 상품 객체를 나타내는 Item
 */
@Data
@Entity // JPA가 사용하는 객체라는 뜻
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 값을 넣어주는 옵션
    private Long id; // 기본 키

    @Column(name = "item_name", length = 10)
    private String itemName; // 상품명

    private Integer price; // 상품 가격
    private Integer quantity; // 수량

    // JPA는 public 또는 protected 기본 생성자가 필수로 있어야 한다. -> 프록시 기술 같은 것을 사용하기 위해서...
    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
