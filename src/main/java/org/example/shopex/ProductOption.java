package org.example.shopex;

public class ProductOption {

    public Long productId;
    public String productOptionName;
    public String[] optionNameArray;

    public Long optionPrice;
    public Long stockCount;
    public String optionImage;

    public ProductOption(long l, String 스웨터01, String s, String 이미지, String 상품설명, long l1, long l2) {
    }






    public ProductOption(String[] optionNameArray, Long stockCount, Long price, String optionImage) {
        this.optionNameArray = optionNameArray;
        this.stockCount = stockCount;
        this.optionPrice = price;
        this.optionImage = optionImage;
    }


    public void addOptionInit(String optStr, String optName, String optName2, long stockCount, long price) {

    }
}
