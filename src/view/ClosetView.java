

package view;

import java.util.Scanner;

import java.util.InputMismatchException;
import controller.ClosetController;
import controller.MemberController;
import model.dto.ClosetDto;

// 추가 및 수정 사항들
/* 현재 로그인된 회원 번호를 가져와서 해야함.  현재 테스트용도로 1로 설정이 되어있음 주석 부분 확인*** 
   메인 메뉴에서 다른 페이지들 넘어가는 부분 메소드  */

public class ClosetView {
    private ClosetView(){}
    private static final ClosetView instance = new ClosetView();
    public static ClosetView getInstance(){ return instance; }
    private ClosetController cl_c = ClosetController.getInstance();
    private Scanner scan = new Scanner(System.in);

    // 메인 메뉴
    public void main_menu(){
        while(true){
            try{
                System.out.println("===========================");
                System.out.println("          메인 메뉴 ");
                System.out.println("===========================");
                System.out.println(" ");
                System.out.println("1. 내 옷장 관리 ");
                System.out.println("2. 코디 추천받기 ");
                System.out.println("3. 의류 활용도 분석 ");
                System.out.println("4. 미착용 의류 관리 ");
                System.out.println("5. 마이 의류 리포트 ");
                System.out.println(" ");
                System.out.println("0. 로그 아웃 ");
                System.out.println(" ");
                System.out.print("선택>>>");  
                int ch = scan.nextInt();

                if(ch==1){ my_closet(); }  // 1.내 옷장
                else if(ch==2){} // 2. 코디 추천
                else if(ch==3){} // 3. 의류 활용도
                else if(ch==4){} // 4. 미착용 의류
                else if(ch==5){} // 5. 마이 의류 관리
                else if(ch==0){MemberController.getInstance().logout();  MemberView.getInstance().run();}  // 0. 로그아웃

            }catch(InputMismatchException e){ scan = new Scanner(System.in); System.out.println("정수만 입력해주세요. " + e);}
        }
    }
    // 내 옷장 관리
    public void my_closet(){
        System.out.println("==============================");
        System.out.println("            내 옷장");
        System.out.println("==============================");
        System.out.println(" ");
        System.out.println("1. 의류 등록 ");
        System.out.println("2. 카테고리별 의류 조회 및 삭제");
        System.out.println(" " );
        System.out.println("0. 뒤로가기 ");
        System.out.println("");
        System.out.print("선택>>> "); int ch = scan.nextInt();

        if(ch==1){ clothesAdd(); }  // 의류 등록
        else if(ch==2){}  // 카테고리별 의류 조회 및 삭제 페이지
        else if(ch==0){ main_menu(); }  // 뒤로가기 메인 메뉴
        else{System.out.println("다시 입력 해주세요."); my_closet(); }
    }



    // 의류 등록
    public void clothesAdd(){
        System.out.println("=============================");
        System.out.println("            의류 등록  ");
        System.out.println("=============================");
        System.out.println(" ");
        System.out.println("(상의 1,하의 2,아우터 3,신발 4), \n(계절 ss: 1 fw: 2, all: 3), 0 , (상세 유형) ");
        System.out.println("상세 유형: \n1101 반팔티 | 1102 나시 | 1201 긴팔티 | 1202 니트 | 1301 셔츠\n"
        + "           2101 반바지 | 2301 긴바지 | 2302 치마\n"
        + "           3201 패딩 | 3202 코트 | 3203 가디건\n"
        + "           4101 샌들 | 4201 구두 | 4301 운동화");

        System.out.println("");
        System.out.print("카테고리 번호: ");  int c_no = scan.nextInt();
        System.out.println(" ");
        System.out.println("색상> white / black / gray  / beige \nnavy / skyblue / pink  / yellow \nred / blue / brown / khaki ");
        System.out.print("색상: ");     String cl_color = scan.next();
        System.out.println("");
        System.out.print("의류 이름: ");  String cl_name = scan.next();

        // 현재 로그인한 회원번호
         int m_no = MemberController.getInstance()
                                    .getLoginMember()
                                    .getM_no();
        ClosetDto closetDto = new ClosetDto();  // 입력받은 의류 정보를 저장할 객체 생성                                      // 입력받은 의류 정보를 저장할 객체 생성
    
        closetDto.setM_no(m_no); // 임시 회원 번호(테스트용) 추후 로그인한 정보에서 회원번호 호출해서 불러옴
        closetDto.setC_no(c_no);
        closetDto.setCl_color(cl_color);
        closetDto.setCl_name(cl_name);

        boolean result = cl_c.clothesAdd(closetDto);
        if(result){System.out.println("의류 등록 성공");}
        else{System.out.println("의류 등록 실패");}
    }

    
    

}
