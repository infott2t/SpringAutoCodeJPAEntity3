package org.example.shopex.cmd;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MenuTablingCmdSys {

    public String cmdStr;
    public String[] menuStrsArr;
    public int menuInt;

    public String adviserName;
    public LocalDateTime loginTime;
    //public String pw; //암호화 필요.
    public String loginText;

    MenuTablingCmdSys(){

        adviserName = null; //시스템 부팅시, 로그인 관리자값을 null로 만들어 준다.
        loginText = null; //시스템 부팅시, 로그인 접속시간을 null로 만들어 준다.

        menuStrsArr = new String[10];
        menuStrsArr[1] = "Task, CommandSystem 생성\n명령어 API 1 ---------------\n상담 서비스, 로그인 해주세요.\n Y/N\n? ";
        //menuStrsArr[2] = "1.회원가입\n2.로그인\n3.로그아웃\n4.회원정보수정\n";
        menuStrsArr[3] = "상담사님의 이름을 입력해주세요. Now time, '홍길동'.\n? ";
        menuStrsArr[4] = "자격인증번호를 입력해주세요. Now time, '1234567'.\n? ";
        menuStrsArr[5] = "(개발관련 메뉴 TDD)\n1.회원가입\n2.로그인\n3.로그아웃\n4.회원정보수정\n--------------------------\n(전화상담업무)\n5.회원확인\n6.A/S관련 상담기록\n? ";

    }



    MenuTablingCmdSys(String str){
        this.cmdStr = str;
    }

    void menuInput(String str){
        this.cmdStr = str;
    }

    void menuStrings(int a)
    {
        //System.out.println("0. exit");
        if(loginText == null){
            System.out.print(menuStrsArr[a]);
        }else{
            System.out.print(loginText + menuStrsArr[a]);
        }
        
        this.menuInt = a;
    }

    int menuCmdStr(String str){
        if(menuInt==1){
            if("Y".equals(str) || "y".equals(str)){
                //관리자의 ID와 암호가 맞는지 체크 필요.
                return 3;
            }else{
                return 1;
            }
        }

        if(menuInt==3){
            // findAll을 사용해서, 관리자 아이디들을 불러온다음, adminId의 값과 맞는지 비교해야됨.
            if("홍길동".equals(str)){
                this.adviserName = "홍길동";
                return 4;
            }else{
                return 1;
            }
        }
        if(menuInt==4){
            if("1234567".equals(str)){

                this.loginTime = LocalDateTime.now();
                //자격인증번호가 일치했으므로, 로그인 시간을 기록. 해당 아이디에 접속시간 기록.
                this.loginText = "Task----------------------\n 상담사 : "+ adviserName + " 님 \n로그인  접속 시간:"+ loginTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))+"\n"+"--------------------------\n";
                
                return 5;
            }else{
                return 1;
            }
        }
        if(menuInt==5){
            return 5;
            //if문을 사용해서, 메뉴  menuStrsArr[5] = "1.회원가입\n2.로그인\n3.로그아웃\n4.회원정보수정\n"; 를 처리해주면 됨. 1일때, 2일때... str과 값이 같은지 비교.
            /*
            if("1".equals(str)){

            }else if("2".equals(str)){

            }else if("3".equals(str)){

            }else if("4".equals(str)){

            }else if("5".equals(str)){

            }else if("6".equals(str)){

            }else{
                return 5; // 값 입력이 1~4가 아닌 경우, 자기자신. menuStrsArr[5]값으로 다시 menuInt 5값을 불러온다. menuStrsArr[menuInt]로 되어있음.
            }
            */



        }
        return -1;
    }
}
