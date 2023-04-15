package org.example.shopex;

import java.util.ArrayList;
import java.util.Arrays;

public class Product {

    public Long productId;
    public String productName;
    public String productCategory;
    public Boolean productOptionTF;     //옵션유무
    public ArrayList<ProductOption> productOptionList; //옵션    // 일반옵션 흰색 L 100개, +100원 | 일반옵션 흰색 XL 100개, +500원. | 추가옵션 악세사리a 100개, +1000원 | 추가옵션 악세사리b 100개, 1500원
    public String productImage;
    public String productAnnounce;

    public Long productBasePrice;           //상품가격
    public Long productDeliveryPrice;   //상품 배송비



    //public Long productStock;  //재고. 상품옵션에서 결정.

    public Product(Long productId, String productName, String productCategory, Boolean productOptionTF, ArrayList<ProductOption> productOptionList, String productImage, String productAnnounce, Long productBasePrice, Long productDeliveryPrice) {

        if(productOptionTF==true){
            this.productId = productId;
            this.productName = productName;
            this.productCategory = productCategory;
            this.productOptionTF = productOptionTF;
            this.productOptionList = new ArrayList<ProductOption>();
            this.productAnnounce = productAnnounce;
            this.productBasePrice = productBasePrice;
            this.productDeliveryPrice = productDeliveryPrice;

        }else{
            this.productId = productId;
            this.productName = productName;
            this.productCategory = productCategory;
            this.productOptionTF = productOptionTF;
            this.productOptionList = null;
            this.productImage = productImage;
            this.productAnnounce = productAnnounce;
            this.productBasePrice = productBasePrice;
            this.productDeliveryPrice = productDeliveryPrice;
        }
    }

    public Product(Long productId, String productName, String productCategory, Boolean productOptionTF, String productImage, String productAnnounce, Long productBasePrice, Long productDeliveryPrice){
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productOptionTF = productOptionTF;
        if(productOptionTF==true) {
            this.productOptionList = new ArrayList<ProductOption>();
        }else{
            this.productOptionList = null;
        }
        this.productImage = productImage;
        this.productAnnounce = productAnnounce;
        this.productBasePrice = productBasePrice;
        this.productDeliveryPrice = productDeliveryPrice;

    }
/*
    public static void main(String[] args) {

        //상품옵션이 없는 경우.
     Product02 clothes01NoOpt = new Product02(1L, "스웨터01", "옷/상의", false, null, "이미지", "상품설명", 10000L, 2500L);

     User user01 = new User("abcId","abcPw","abcName","abcEmail","abcPhone","abcAddress","abcAddressDetail","abcAddressPostCode");
     user01.buy(clothes01NoOpt, false, 3L, "진행 중"); // a상품을 3개 구매, 옵션없음.

        //상품옵션이 있는 경우.
        Product02 clothes02Opt = new Product02(2L, "스웨터02", "옷/상의", true, "이미지","상품설명", 10000L, 2500L);
        clothes02Opt.addOptionInit("일반옵션", "흰색", "L", 100L, 100L, "이미지URL");
        clothes02Opt.addOptionInit("일반옵션", "흰색", "XL", 100L, 500L,"이미지URL");
        clothes02Opt.addOptionInit("추가옵션", "악세사리a", "", 100L, 1000L,"이미지URL");
        clothes02Opt.addOptionInit("추가옵션", "악세사리b", "", 100L, 1500L,"이미지URL");


        user01.buy(clothes02Opt, true, "일반옵션","흰색","L",3L, "진행 중"); // a상품, 일반옵션 흰색 L 3개 구매.


    }

 */

    public void addOptionInit(String [] optionNameArr, Long stockCount, Long price, String optionImage) {
        // TODO Auto-generated method stub
        ProductOption po = new ProductOption( optionNameArr, stockCount, price, optionImage);
        this.productOptionList.add(po);

    }


    public Long findOptionPrice(String[] strings) {
// TODO Auto-generated method stub
        Long price = 0L;
        for(ProductOption po : productOptionList) {
            if(Arrays.equals(po.optionNameArray,strings)) {
                price = po.optionPrice;
            }
        }
        return price;
    }
}
