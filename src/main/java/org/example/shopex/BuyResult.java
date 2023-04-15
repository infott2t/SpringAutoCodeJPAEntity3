package org.example.shopex;

public class BuyResult {

    public User user;
    public Long productId;
    public String status;
    public String userId;
    public String productName;
    public Boolean optionTF;
    public String[] optionNameArray;
    public Long count;
    public Long productBasePrice;
    public Long optionPrice;
    public Long productDeliveryPrice;
    public Long totalPrice;

    public BuyResult(User user01) {
    this.user = user01;
    }

    public BuyResult(User user01, Long productId, String status, String userId, String productName, Boolean b, String[] strings, Long count, Long productBasePrice, Long optionPrice, Long productDeliveryPrice, Long totalPrice) {
        this.user = user01;
        this.productId = productId;
        this.status = status;
        this.userId = userId;
        this.productName = productName;
        this.optionTF = b;
        this.optionNameArray = strings;
        this.count = count;
        this.productBasePrice = productBasePrice;
        this.optionPrice = optionPrice;
        this.productDeliveryPrice = productDeliveryPrice;
        this.totalPrice = totalPrice;

    }
}
