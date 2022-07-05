package hello.itemservice.repository;

import lombok.Data;

/**
 * Dto(Data Transfer Object)
 * 데이터 전송 객체
 * 기능이 없고 데이터 전달만을 목적으로 사용되는 객체
 * -> 그렇다고 기능이 있으면 안되는 것은 아니다.
 */
@Data
public class ItemUpdateDto {
    private String itemName;
    private Integer price;
    private Integer quantity;

    public ItemUpdateDto() {
    }

    public ItemUpdateDto(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
