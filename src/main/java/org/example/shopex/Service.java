package org.example.shopex;

import java.util.ArrayList;
import java.util.Arrays;

public class Service {

    public Product product;
    public User user;
    public ArrayList<Cart> cartList;

    public static void main(String[] args) {

        //옵션이 없는 상품을 만듬. 고기01 | 식품/육류 | 옵션없음 | 이미지 | 상품설명 | 10000 | 2500
       Product meat01_NoOpt = new Product(1L, "고기01", "식품/육류", false, "상품이미지1", "상품설명1", 10000L, 2500L);

       //옵션이 있는 상품. 스웨터01 | 의류/패션 | 옵션있음 | 이미지 | 상품설명 | 10000 | 2500
        Product clothes01_Opt = new Product(2L, "스웨터01", "의류/패션", true, "상품이미지2", "상품설명2", 10000L, 2500L);

        clothes01_Opt.addOptionInit(new String[]{"일반옵션","흰색", "L"}, 100L, 100L, "이미지URL");
        clothes01_Opt.addOptionInit(new String[]{"일반옵션", "흰색", "XL"}, 100L, 500L,"이미지URL");
        clothes01_Opt.addOptionInit(new String[]{"추가옵션","악세사리a",""}, 100L, 1000L,"이미지URL");
        clothes01_Opt.addOptionInit( new String[]{"추가옵션","악세사리b", ""}, 100L, 1500L,"이미지URL");




       //사용자 1.
       User user01 = new User();
       //userRole과 userName을 가지는 User클래스.
        user01.inputUserRolePRN("guest");

       //사용자 1의 장바구니 생성.
       ArrayList<Cart> user01_CartList = new ArrayList<Cart>();
       user01.cart = user01_CartList;

       //구매 결과 List의 생성.
        ArrayList<BuyResult> user01_BuyResult = new ArrayList<BuyResult>();


       //Task1. 사용자 1이 옵션이 없는 상품을 장바구니에 담는다.
       //사용자 1이 옵션이 없는 상품을 장바구니에 담음.  meat01_NoOpt상품을 3개 구매, 옵션없음.
        Cart cart = new Cart(meat01_NoOpt, false, 3L, "진행 중", meat01_NoOpt.productDeliveryPrice);
        user01_CartList.add(cart);

        //Buy. 총가격의 표시. 진행 중.3
        buyPrint(user01_BuyResult, user01, meat01_NoOpt, false, null, 3L, "진행 중", cart);
        //배송비 포함, 총 결제금액 출력.
        buyPrint2(user01_BuyResult, user01,cart);

       // 사용자가 구매 진행중. 표시.
       // statusPrint(user01);

        //Task2. 사용자 1이 옵션이 있는 상품을 장바구니에 담는다.
        //사용자 1이 옵션이 있는 상품을 장바구니에 담음. clothes01_Opt상품을 2개 구매, 옵션있음, 옵션은 일반옵션, 흰색, L
        Cart cart2 = new Cart(clothes01_Opt, true, "진행 중", clothes01_Opt.productDeliveryPrice);
        cart2.addOption(new String[]{"일반옵션","흰색", "L"}, 2L);
        cart2.addOption(new String[]{"추가옵션","악세사리a", ""}, 5L);
        user01_CartList.add(cart2);

        //사용자의 구매 진행 중 상품의 가격을 표시.
        Long counting = cart2.countingOption(); //옵션의 갯수를 계산.
        for(int i =0 ; i< counting; i++){
            String[] optionStr = cart2.optionNameArray.get(i);
            Long optionCount = cart2.optionCountArray.get(i);
            buyPrint(user01_BuyResult, user01, clothes01_Opt, true, optionStr, optionCount, "진행 중",cart2);
        }
        buyPrint2(user01_BuyResult, user01, cart2);

        //옵션 가격 찾기.
        //Long optionPrice = clothes01_Opt.findOptionPrice(new String[]{"일반옵션","흰색", "L"});


        //Buy. 총가격의 표시. 진행 중.
       // buyPrint(user01, clothes01_Opt, true,new String[]{"일반옵션","흰색", "L"}, 2L, "진행 중");






    }

    private static void buyPrint2(ArrayList<BuyResult> user01_BuyResult, User user01, Cart cart) {
        Long productPrices = 0L;
        Long deliveryPrice = 0L;
        Long productId = cart.product.productId;
        Long nn = 0L;
        for(int i=0; i< user01_BuyResult.size(); i++){
            BuyResult buyResult = user01_BuyResult.get(i);
            if(buyResult.productId ==cart.product.productId) {
                        productId = buyResult.productId;
                        productPrices = productPrices + buyResult.totalPrice;

                        deliveryPrice = cart.deliveryPrice;
                        nn = nn+1;

            }
        }
        System.out.println("제품 ProductId, " + productId );
        System.out.println("동일 상품의 갯수, "+ nn + " 개");
        System.out.println("제품 가격 합계, " + productPrices + "원");
        System.out.println("배송비,        " + deliveryPrice + "원");
        System.out.println("결제 금액 " +productPrices +" + "+ deliveryPrice +" = "+ (productPrices+deliveryPrice)+ "원");

    }

    private static void buyPrint(ArrayList<BuyResult> user01_BuyResult, User user01, Product clothes01_opt, Boolean b, String[] strings, Long count, String status, Cart cart) {

        if(b) {
            System.out.println("============================================");
            System.out.println("Service Task. User buy status:  " + status);
            System.out.println("사용자 : " + user01.userId);
            System.out.println("상품명 : " + clothes01_opt.productName);
            System.out.println("옵션 : " + (b ? "옵션있음" : "옵션없음"));
            System.out.println("옵션명 : " + Arrays.toString(strings) + "  수량 : " + count);
            //가격 계산.
            Long optionPrice = clothes01_opt.findOptionPrice(strings);
            Long productPrices = (clothes01_opt.productBasePrice + optionPrice) * count;
            if(strings[0].equals("추가옵션")){
                System.out.println("제품 기본가격 : " + "0 원");
                System.out.println("옵션 가격: " + optionPrice + " 원");
                System.out.println("제품 가격: " + optionPrice + " 원");
                System.out.println("제품*수량: " + optionPrice * count + " 원");
                System.out.println("배송비 : 동일 상품 합산 출력. 아래 출력됨.");
                System.out.println("상품 결제금액 : " + optionPrice * count + " 원");
            }else{
                System.out.println("제품 기본가격 : " + clothes01_opt.productBasePrice + " 원");
                System.out.println("옵션 가격: " + optionPrice + " 원");
                System.out.println("제품 가격: " + (clothes01_opt.productBasePrice + optionPrice) + " 원");
                System.out.println("제품*수량: " + (clothes01_opt.productBasePrice + optionPrice) * count + " 원");
                System.out.println("배송비 : 동일 상품 합산 출력. 아래 출력됨.");
                System.out.println("상품 결제금액 : " + productPrices + " 원");
            }



            //System.out.println("배송비 : " + clothes01_opt.productDeliveryPrice + " 원");
            //System.out.println("총할인가 : " + clothes01_opt.discountPrice * l);

            System.out.println("상태 : " + status);
            System.out.println("============================================");

            if(strings[0].equals("추가옵션")){
                user01_BuyResult.add(new BuyResult(user01, clothes01_opt.productId, status, user01.userId, clothes01_opt.productName, b, strings, count, clothes01_opt.productBasePrice, optionPrice, clothes01_opt.productDeliveryPrice, optionPrice * count));
            }else{
                user01_BuyResult.add(new BuyResult(user01, clothes01_opt.productId, status, user01.userId, clothes01_opt.productName, b, strings, count, clothes01_opt.productBasePrice, optionPrice, clothes01_opt.productDeliveryPrice, productPrices));
            }

        }else{
            System.out.println("============================================");
            System.out.println("Service Task. User buy status:  " + status);
            System.out.println("사용자 : " + user01.userId);
            System.out.println("상품명 : " + clothes01_opt.productName);
            System.out.println("옵션 : " + (b ? "옵션있음" : "옵션없음"));
            System.out.println("옵션명 : --- " );
            System.out.println("수량 : " + count);
            //가격 계산.
            //Long optionPrice = clothes01_opt.findOptionPrice(strings);
            Long productPrices = (clothes01_opt.productBasePrice  * count);
            System.out.println("제품 기본가격 : " + clothes01_opt.productBasePrice + " 원");
            System.out.println("옵션 가격: " + " 0 원");
            System.out.println("제품 가격: " + clothes01_opt.productBasePrice +  " 원");
            System.out.println("제품*수량: " + productPrices + " 원");
            //System.out.println("배송비 : " + clothes01_opt.productDeliveryPrice + " 원");
            //System.out.println("총할인가 : " + clothes01_opt.discountPrice * l);
            System.out.println("상품 결제금액 : " + productPrices + " 원");
            System.out.println("상태 : " + status);
            System.out.println("============================================");
            user01_BuyResult.add(new BuyResult(user01, clothes01_opt.productId, status, user01.userId, clothes01_opt.productName, b, strings, count, clothes01_opt.productBasePrice, 0L, clothes01_opt.productDeliveryPrice, productPrices));
        }
    }

    private static void statusPrint(User user01) {
        System.out.println("-----* UserStatusPrint service Task start print-----");
        System.out.println(user01.userId + " 사용자가 카트에 담은 제품, 옵션, 수량, 가격, 배송비, 총합계를 표시.");
        System.out.println(user01.cart);
        System.out.println("-----* UserStatusPrint service Task end   print-----");
        System.out.println();
    }


    public static void buyPrint(User user, Product a, boolean options, Long productCount, String status) {
        if (options == true) {
            System.out.println("-----* BuyPrint service start print-----");
            System.out.println("상품명 : " + a.productName + " 옵션 : " + a.productOptionList + " 기본 가격 :" + a.productBasePrice + " 수량 : " + productCount + " 배송비: " + a.productDeliveryPrice);
            System.out.println("총 가격 : " + ((a.productBasePrice * productCount) + a.productDeliveryPrice));
            System.out.println("구매자 : " + user.userName + " 구매 상태: " + status);
            System.out.println("-----* BuyPrint service end   print-----");
            System.out.println();
        } else {
            if (a.productOptionTF == false) {

                System.out.println("-----* BuyPrint service start print-----");
                System.out.println("상품명 : " + a.productName + " 옵션 : 없음 " + " 기본 가격 :" + a.productBasePrice + " 수량 : " + productCount+ " 배송비: " + a.productDeliveryPrice);
                System.out.println("총 가격 : " + ((a.productBasePrice * productCount) + a.productDeliveryPrice));
                System.out.println("구매자 : " + user.userName + " 구매 상태: " + status);
                System.out.println("-----* BuyPrint service end   print-----");
                System.out.println();
            }
        }
    }
}
