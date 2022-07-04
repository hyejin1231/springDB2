package hello.itemservice.repository;

import lombok.Data;

// 검색 조건
@Data
public class ItemSearchCond {

    private String itemName; // 상품명
    private Integer maxPrice; // 최대 가격

    public ItemSearchCond() {
    }

    public ItemSearchCond(String itemName, Integer maxPrice) {
        this.itemName = itemName;
        this.maxPrice = maxPrice;
    }
}
