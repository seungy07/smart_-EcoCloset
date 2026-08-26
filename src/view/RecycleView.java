package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.MemberController;
import controller.RecycleController;
import model.dto.RecycleDto;
    // 로그인한 회원 번호 확인용
    
public class RecycleView {
    
    private RecycleView() {}; // 1.
    private static final RecycleView instance = new RecycleView(); // 2.
    public static RecycleView getInstance() { return instance; } // 3.

    private RecycleController rc = RecycleController.getInstance();

    Scanner scanner = new Scanner(System.in);

    // 장기 미착용 의류 목록 출력하는 부분
    public void unusedReport(){
        while (true) {
            try {
                System.out.println("----------------------------------------");
                System.out.println("\t장기 미착용 의류");
                System.out.println("----------------------------------------");
                System.out.println();
                System.out.println("- 최근 90일 이상 착용하지 않은 옷");
                System.out.println();
                System.out.println("[번호] - [의류] - [마지막 착용] - [미착용 기간]");

                // 장기 미착용 의류 출력하는 부분
                ArrayList<RecycleDto> result = rc.unusedReport();
                
                for(RecycleDto dto : result){
                    System.out.println("  " + dto.getNo() + " - " + dto.getName() + " - " + dto.getDate() + " - " + dto.getUnUsedDays()+"일");
                }

                // 의류 번호를 선택할 부분
                System.out.println();
                System.out.print("관리할 의류 번호 선택(뒤로가기: 0) >> ");
                int ch_no = scanner.nextInt();
                
                if (ch_no == 0) {
                    break;
                }

                for(RecycleDto dto : result){
                    if (dto.getNo() == ch_no) {
                        reCycleAdd(ch_no, dto);
                        break;
                    }
                }
                

            } catch (InputMismatchException e) {
                scanner = new Scanner(System.in, "EUC-KR");
                System.out.println("> 유효하지 않은 입력입니다.  " + e);
            }

        }
    }

    // 기부or중고거래 처리 메소드 -> clothes 테이블에서 삭제 X
    public void reCycleAdd(int ch_no, RecycleDto dto){
        while (true) {
            try {
                System.out.println();
                System.out.println();
                System.out.printf("[%s]", dto.getName()); // 관리하려는 의류 이름 불러와야 함
                System.out.println();
                System.out.println("착용횟수 - " + dto.getWearCount() + "회"); // DTO에서 꺼내옴
                System.out.println("마지막 착용 - " + dto.getDate());
                System.out.println("미착용 기간 - " + dto.getUnUsedDays() + "일");
                System.out.println();
                System.out.println("> 이 옷을 어떻게 관리하시겠습니까?");
                System.out.print("> 1. 계속 보관 2. 기부 3. 나눔 4. 중고판매 5. 의류 폐기: ");
                int caseNum = scanner.nextInt();
                boolean result = rc.reCycleAdd(ch_no, caseNum, dto);

                if (result) {
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>");
                    break;
                }

            } catch (InputMismatchException e) {
                scanner = new Scanner(System.in, "EUC-KR");
                System.out.println("> 유효하지 않은 입력입니다.  " + e);
            }
        }
    }

    public void usageReport(){
        System.out.println("====================================");
        System.out.println("    의류 활용도 분석(누적 횟수");
        System.out.println("====================================");
        System.out.println();
        System.out.println("1. 가장 많이 입은 옷");
        System.out.println("2. 가장 적게 입은 옷");
        System.out.println("3. 장기 미착용 의류");
        while (true) {
            System.out.print("선택(뒤로가기: 0)>> "); int ch = scanner.nextInt();

            if (ch == 1) {
                ArrayList<RecycleDto> result = rc.findMaxWearCount();
                System.out.println();
                System.out.println("[가장 많이 입은 옷]");
                System.out.println("---------------------------------");
                for(int i = 0; i <= 2; i++){
                    System.out.println((i+1) + "위 " + result.get(i).getName() + " - " +result.get(i).getWearCount()+"회");
                }
                System.out.println();

                break;
            } else if (ch == 2) {
                ArrayList<RecycleDto> result = rc.findMinWearCount();
                System.out.println();
                System.out.println("[가장 적게 입은 옷]");
                System.out.println("---------------------------------");
                for (int i = 0; i <= 2; i++) {
                    System.out.println(
                            (i + 1) + "위 " + result.get(i).getName() + " - " + result.get(i).getWearCount() + "회");
                }
                System.out.println();

                break;
            } else if (ch == 3) {
                unusedReport();
                break;
            } else if (ch == 0) {
                break;
            } 
        }
    }

    public void recyclePrint(){
        System.out.println("--------------------------------------------------");
        System.out.println( "          ClosetMate 의류 순환 리포트");
        System.out.println("--------------------------------------------------");
        System.out.println("");


        RecycleDto result = rc.recyclePrint();

        System.out.println("현재 보유 의류: " + result.getClothesSum() + "벌");
        System.out.println("이번 달 착용: " + result.getWearCountInMonth() + "회");
        System.out.println("90일 이상 미착용: " + result.getUnUsedClothesSum() + "벌");
        System.out.println();
        System.out.println("[의류 순환 현황]");
        System.out.println();
        System.out.println();
        System.out.println("기부: " + result.getClothesDonation() + "회");
        System.out.println("나눔: " + result.getClothesShare() + "회");
        System.out.println("중고판매: " + result.getUsedClothesSale() + "회");
        System.out.println();
        System.out.println("총 순환 의류: " + result.getTotalRecycleSum() + "벌");
        System.out.println();
        System.out.println("가장 많이 입은 옷");
        System.out.println("- "+ result.getMostClothes() + " " + result.getMostClothesCount() + "회" );
        System.out.println();
        System.out.println("가장 오래 입지 않은 옷");
        System.out.println("- " + result.getOldestUnusedClothes() + " " + result.getOldestUnusedDays() + "일");
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("※ Eco DashBoard를 보시겠습니까?");
        System.out.println(" 0 - [아니오] , 1 - [네]");
        while (true) {
            System.out.print("> 입력: "); int ch = scanner.nextInt();

            if (ch == 0) {
                break;
            } else if (ch == 1) {
                // 에코 대시보드 넘어가기 메서드 불러올 것.
                break;
            }
        }

        




        
        
    }
}
