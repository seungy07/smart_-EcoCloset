package view;

import controller.CodiController;
import model.dto.CodiDto;
import model.dto.MemberDto;
import controller.MemberController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CodiView {
    private static CodiView instance = new CodiView();
    private CodiView() {}
    public static CodiView getInstance() {
        return instance;
    }

    private Scanner scanner = new Scanner(System.in);

    CodiController cc = CodiController.getInstance();


    public void recommendView(int loginMNo) {
        while (true) {
            
            System.out.println("\n==================================================");
            System.out.println("               [ ⭐ 코디 추천 메뉴 ⭐]                  ");
            System.out.println("==================================================");

            CodiDto codi = null;

            // 1. Controller 및 DB 호출 예외 처리
            try {
                ArrayList<CodiDto> seasonClothes = cc.seasonSearch(loginMNo);
                codi = cc.outfitRecommend(seasonClothes, "all");
            } catch (Exception e) {
                System.out.println("(오류) 데이터 불러오는 중 시스템에러 발생.");
                return;
            }

            if (codi == null) {
                System.out.println("(안내) 조건에 맞는 코디 없음...");
                return;
            }

            // 2. 추천 코디 출력
            printLookbook(codi);

            System.out.println("\n1. 이 코디 착용하기");
            System.out.println("2. 다른 코디 추천받기");
            System.out.println("0. 메인 메뉴로 이동");
            System.out.print("선택 >> ");

            int select = -1;
            try {
                select = scanner.nextInt();
                scanner.nextLine(); // 버퍼 비우기
            } catch (Exception e) {
                System.out.println("(경고) 숫자만 입력 가능합니다.");
                scanner.nextLine(); // 버퍼 비우기
                continue;
            }

            if (select == 1) {
                ArrayList<Integer> usedClNos = new ArrayList<>();
                if (codi.getOuter() != null)
                    usedClNos.add(codi.getOuter().getClNo());
                if (codi.getTop() != null) 
                    usedClNos.add(codi.getTop().getClNo());
                if (codi.getBottom() != null) 
                    usedClNos.add(codi.getBottom().getClNo());
                if (codi.getShoes() != null) 
                    usedClNos.add(codi.getShoes().getClNo());

                CodiDto wearDto = new CodiDto(LocalDate.now().toString(), usedClNos);

                try {
                    boolean result = cc.wearAdd(wearDto);
                    if (result) {
                        System.out.println("(안내) 의류 착용 횟수 + 1회 증가!");
                    } else {
                        System.out.println("(오류) 착용 기록 등록 실패.");
                    }
                } catch (Exception e) {
                    System.out.println("(오류) 착용 기록 저장 중 오류 발생.");
                }
                return; // 착용 완료 후 메인 메뉴로 복귀
            } 
            else if (select == 2) {
                System.out.println("(안내) 다른 코디를 탐색 중...");
                // 루프 재실행 -> 새로운 무작위 코디 추출
            } 
            else if (select == 0) {
                System.out.println("(안내) 메인 메뉴로 이동합니다.");
                return;
            } 
            else {
                System.out.println("(경고) 올바른 번호를 입력해주세요.");
            }
        }
    }

    private void printLookbook(CodiDto codi) {
        System.out.println("==================================================");
        System.out.println("[ 👕 오늘의 추천 코디 👕 ]");
        System.out.println();
        System.out.println("- 아우터: " + (codi.getOuter() != null ? codi.getOuter().getClName() : "없음"));
        System.out.println("- 상의  : " + (codi.getTop() != null ? codi.getTop().getClName() : "없음"));
        System.out.println("- 하의  : " + (codi.getBottom() != null ? codi.getBottom().getClName() : "없음"));
        System.out.println("- 신발  : " + (codi.getShoes() != null ? codi.getShoes().getClName() : "없음"));
        System.out.println();
        System.out.println("==================================================");
    }
}