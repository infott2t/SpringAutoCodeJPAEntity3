package org.example.shopex.cmd;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MenuTablingCmdSys {

    public String cmdStr;
    public String[] menuStrsArr;
    public int menuInt;

    public String adminId;
    public LocalDateTime loginTime;
    //public String pw; //암호화 필요.
    public String loginText;

    MenuTablingCmdSys(){
        
        adminId = null; //시스템 부팅시, 로그인 관리자값을 null로 만들어 준다.
        loginText = null; //시스템 부팅시, 로그인 접속시간을 null로 만들어 준다.

        menuStrsArr = new String[10];
        menuStrsArr[1] = "Task, CommandSystem 생성\n명령어 API 1 ---------------\n관리자 로그인 해주세요.\n Y/N\n";
        //menuStrsArr[2] = "1.회원가입\n2.로그인\n3.로그아웃\n4.회원정보수정\n";
        menuStrsArr[3] = "ID를 입력해주세요. Now time, 'admin'.\n";
        menuStrsArr[4] = "암호를 입력해주세요. Now time, '1234'.\n";
        menuStrsArr[5] = "1.회원가입\n2.로그인\n3.로그아웃\n4.회원정보수정\n";
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
            // findAll을 사용해서, 관리자 아이디들을 불러온다음, adminId의 값과 맞는지를 비교가 필요.
            if("admin".equals(str)){
               
                return 4;
            }else{
                return 1;
            }
        }
        if(menuInt==4){
            if("1234".equals(str)){
                this.adminId = "admin";
                this.loginTime = LocalDateTime.now();
                //암호가 일치했으므로, 로그인 시간을 기록. 해당 아이디에 접속시간 기록.
                this.loginText = "Task-- 관리자 로그인:"+ adminId + "\n 접속 시간:"+ loginTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))+"\n"+"--------------------------\n";
                
                return 5;
            }else{
                return 1;
            }
        }
        if(menuInt==5){
            return 5;
        }
        return -1;
    }
}
