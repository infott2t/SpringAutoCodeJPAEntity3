package org.example.shopex;

import java.util.ArrayList;

public class User {

    public String userRole; // advance, guest, seller, admin
    public String userName;
    public String userId;

    public ArrayList<Cart> cart;

    public User(){
        this.cart = new ArrayList<Cart>();
    }

    public void buy(Product clothes02Opt, boolean b, long l) {
    }

    public void buy(Product clothes02Opt, boolean b, String 일반옵션, String 흰색, String l, long l1, String 진행_중) {

    }

    public String inputUserRolePRN(String userRole){


        if(("admin".equals(userRole)) || ("seller".equals(userRole)) || ("guest".equals(userRole)) || ("advance".equals(userRole))){

            this.userRole = userRole;

        }else{
            System.out.println("Task, 유저권한 입력오류. \n 입력방법. userRole, 'admin', 'seller', 'guest', 'advance' 중 하나를 입력하세요.");

        }
        return "Task, 유저 권한 입력 성공. User, "+this+"\n Input User Role: " + userRole;
    }
}
