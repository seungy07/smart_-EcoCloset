package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.RecycleController;
import model.dto.RecycleDto;

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
                int ch = scanner.nextInt();
                
                if (ch == 0) {
                    break;
                }

                for(RecycleDto dto : result){
                    if (dto.getNo() == ch) {
                        recycleAdd(ch, dto);
                        break;
                    }
                }
                

            } catch (InputMismatchException e) {
                scanner = new Scanner(System.in, "EUC-KR");
                System.out.println("> 유효하지 않은 입력입니다.  " + e);
            }
        }
    }

    // 기부or중고거래 처리 메소드 -> clothes 테이블에서 삭제, 
    public void recycleAdd(int ch, RecycleDto dto){
        while (true) {
            try {
                System.out.println();
                System.out.println();
                System.out.printf("[%s]", dto.getName()); // 관리하려는 의류 이름 불러와야 함
                System.out.println();
                System.out.println("착용횟수 - " + dto.getWearCount() + "회"); // DTO에서 꺼내옴
                System.out.println("마지막 착용 - " + dto.getDate());
                System.out.println("미착용 기간 - " + dto.getUnusedDays() + "일");
                System.out.println();
                System.out.println("이 옷을 어떻게 관리하시겠습니까?");
                System.out.println("1. 계속 보관 2. 기부 3. 나눔 4. 중고판매 5. 의류 폐기");

            } catch (InputMismatchException e) {
                scanner = new Scanner(System.in, "EUC-KR");
                System.out.println("> 유효하지 않은 입력입니다.  " + e);
            }
        }
    }
}
