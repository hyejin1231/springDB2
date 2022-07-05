package hello.itemservice.domain;

import lombok.Data;

/**
 * 상품 객체를 나타내는 Item
 */
@Data
public class Item {

    private Long id; // 기본 키

    private String itemName; // 상품명
    private Integer price; // 상품 가격
    private Integer quantity; // 수량

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
