package view;

import java.util.Scanner;
import controller.MemberController;

public class MemberView {
    private MemberView(){}

    private static final MemberView instance = new MemberView();
    public static MemberView getInstance(){
        return instance;
    }

    private Scanner scan = new Scanner(System.in);
    // Controller 객체
    private MemberController memberController
            = MemberController.getInstance();

    public void run(){
        while (true) {
            System.out.println("=====================================");
            System.out.println("              EcoCloset");
            System.out.println("     스마트 옷장 & 자원 순환 시스템");
            System.out.println("=====================================");
            System.out.println("       의류를 더 오래, 더 가치 있게");
            System.out.println("-------------------------------------");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 프로그램 종료");
            System.out.println("=====================================");
            System.out.print("선택 >> ");

            int ch = scan.nextInt();

            if(ch == 1){
                login(); 
            }
            else if( ch == 2){
                signUp();
            }
            else if( ch == 3){System.out.println("프로그램을 종료합니다."); return;} 
            else{{System.out.println("[경고] 잘못된 번호입니다.");}}
        }

    }

    // 로그인
    public void login(){
        
        System.out.println("=====================================");
        System.out.println("               로그인");
        System.out.println("=====================================");

        System.out.print("아이디 >> ");
        String m_id = scan.next();

        System.out.print("비밀번호 >> ");
        String m_pwd = scan.next();

        boolean result = memberController.login(m_id, m_pwd);
        if( result == true){
            System.out.println("[안내] 로그인에 성공했습니다.");
            System.out.println(
                memberController.getLoginMember().getM_no() +"님 환영합니다."
            );

            // 로그인 성공 후 메인화면 이동

            ClosetView.getInstance().main_menu();
        }else{
            System.out.println("[경고] 아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    // 회원가입
     public void signUp(){

        System.out.println("=====================================");
        System.out.println("              회원가입");
        System.out.println("=====================================");

        System.out.print("아이디 >> ");
        String m_id = scan.next();

        // 아이디 중복검사
        boolean check = memberController.inCheck(m_id);
        if(check == false){
            System.out.println("[경고] 이미 사용중인 아이디입니다.");
            return;
        }

        System.out.print("비밀번호 >> ");
        String m_pwd = scan.next();
        boolean result = memberController.signUp(m_id, m_pwd);

        if(result == true){
            System.out.println("[안내] 회원가입이 완료되었습니다.");
        }else{System.out.println("[경고] 회원가입에 실패했습니다.");}

}
}
