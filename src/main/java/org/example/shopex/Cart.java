package org.example.shopex;

import java.util.ArrayList;

public class Cart {


    public Product product;
    public Boolean optionTF;

    public ArrayList<String[]> optionNameArray;
    public ArrayList<Long> optionCountArray;
    public Long count;
    public Long price;
    public Long deliveryPrice;
    public String status;

    public ArrayList<ProductOption> productOptionList;


    public Cart(){

    }

       public Cart(Product product, Boolean optionTF, Long count, Long price, String status) {
            this.product = product;
            this.optionTF = optionTF;
            this.count = count;
            this.price = price;
            this.status = status;
        }

    public Cart(Product product, boolean optionTF, long count, String status) {
        this.product = product;
        this.optionTF = optionTF;
        this.count = count;
        this.price = product.productBasePrice;
        this.status = status;
    }

    public Cart(Product product, boolean optionTF, String status) {
        this.product = product;
        this.optionTF = optionTF;
        this.optionNameArray = new ArrayList<String[]>();
        this.optionCountArray = new ArrayList<Long>();
        this.count = 1L;
        this.price = product.productBasePrice;
        this.status = status;
    }

    public Cart(Product meat01_noOpt, boolean optionTF, long count, String status, Long productDeliveryPrice) {
        this.product = meat01_noOpt;
        this.optionTF = optionTF;
        this.count = count;
        this.price = meat01_noOpt.productBasePrice;
        this.deliveryPrice = productDeliveryPrice;
        this.status = status;
    }

    public Cart(Product clothes01_opt, boolean optionTF, String 진행_중, Long productDeliveryPrice) {
        this.product = clothes01_opt;
        this.optionTF = optionTF;
        this.optionNameArray = new ArrayList<String[]>();
        this.optionCountArray = new ArrayList<Long>();
        this.count = 1L;
        this.price = clothes01_opt.productBasePrice;
        this.deliveryPrice = productDeliveryPrice;
        this.status = status;
    }


    @Override
    public String toString() {
       return "카트 정보, Cart{" +
                "product=" + product +
                ", optionTF=" + optionTF +
                ", count=" + count +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }



    public void addOption(String[] strings, String l, long l1) {
    }



    public void addOption(String[] strings, Long count) {
        this.optionNameArray.add(strings);
        this.optionCountArray.add(count);
    }

    public Long countingOption() {
        return Long.valueOf(this.optionNameArray.size());
    }
}
