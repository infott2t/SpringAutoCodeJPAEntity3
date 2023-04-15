package org.example.shopex.cmd;

import java.util.Scanner;

public class CommandSystem {

    public String[] scStrArr;

    public MenuTablingCmdSys menuTablingCmdSys;


    public String commandSystem;


    public CommandSystem(){

        scStrArr = new String[10]; //커멘드 입력의 배열.  

/* 
        System.out.println("Task, CommandSystem 생성");
        System.out.println("명령어 API 1--------------------");
        System.out.println("관리자 로그인해주세요.");
        System.out.println("Y/N");

        ////////// Let's making.... Good developing~. Funny time. Thank you.
        System.out.println("1.회원가입"); // User user01 = new User();
        System.out.println("2.로그인");   // user01.login()
        System.out.println("3.로그아웃");   //user01.logout()
        System.out.println("4.회원정보수정");
        ///////////
*/
        
        MenuTablingCmdSys menuTabStr = new MenuTablingCmdSys();

        int menuInt = 1;
      

        Scanner sc = new Scanner(System.in);
        String numStr;
        
        do {
            menuTabStr.menuStrings(menuInt);
             
            numStr = sc.nextLine();
            if(sc.equals(null)){
                menuTabStr.menuStrings(menuInt);
            }else{
                menuInt = menuTabStr.menuCmdStr(numStr);
            }

        } while (menuInt > 0);

        
        System.out.println("--접속 종료. 로그인 해주세요.");
        
        
       


        
       

    }

    public static void main(String[] args) {
        new CommandSystem();
    }
}
