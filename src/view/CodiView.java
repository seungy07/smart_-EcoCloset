package view;

import controller.CodiController;
import model.dto.CodiDto;
import model.dto.SeasonClothesDto;
import model.dto.WearDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CodiView {
    private static CodiView instance = new CodiView();
    private CodiView() {}
    public static CodiView getInstance() { return instance; }

    private Scanner scanner = new Scanner(System.in);

    private void recommendView() {
        while (true) {
            System.out.println("\n==================================================");
            System.out.println("               [ 코디 추천 메뉴 ]                  ");
            System.out.println("==================================================");
        // 1. 계절 필터링 의류 조회
        ArrayList<SeasonClothesDto> seasonClothes = CodiController.getInstance().seasonSearch(loginMNo);

        // 2. 코디 추천 목록 생성
        ArrayList<CodiDto> recommendations = CodiController.getInstance().outfitRecommend(seasonClothes);

        if (recommendations.isEmpty()) {
            System.out.println("(안내) 조건에 맞는 코디 추천 조합이 없습니다.");
            return;
        }

        int index = 0;
        while (index < recommendations.size()) {
            CodiDto codi = recommendations.get(index);
            printLookbook(codi);

            System.out.println("\n1. 이 코디 착용하기 (착용 횟수 +1)");
            System.out.println("2. 다른 코디 추천받기");
            System.out.println("3. 메인 메뉴로 이동");
            System.out.print("선택 >> ");
            int select = scanner.nextInt();
            scanner.nextLine();

            if (select == 1) {
                ArrayList<Integer> usedClNos = new ArrayList<>();
                if (codi.getOuter() != null) usedClNos.add(codi.getOuter().getClNo());
                if (codi.getTop() != null) usedClNos.add(codi.getTop().getClNo());
                if (codi.getBottom() != null) usedClNos.add(codi.getBottom().getClNo());
                if (codi.getShoes() != null) usedClNos.add(codi.getShoes().getClNo());

                WearDto wearDto = new WearDto(LocalDate.now().toString(), usedClNos);
                boolean result = CodiController.getInstance().wearAdd(wearDto);

                if (result) {
                    System.out.println("(안내) 선택한 의류들의 착용 횟수가 1회 증가했습니다!");
                } else {
                    System.out.println("(오류) 착용 기록 등록 실패.");
                }
                break;
            } else if (select == 2) {
                index++;
                if (index >= recommendations.size()) {
                    System.out.println("(안내) 더 이상 추천할 코디가 없습니다.");
                    break;
                }
            } else {
                break;
            }
        }
    }

    private void printLookbook(CodiDto codi) {
        System.out.println("==================================================");
        System.out.println("[ 👕 오늘 날씨에 어울리는 추천 코디 👕 ]");
        System.out.println();
        System.out.println("추천 룩: [ " + codi.getLookName() + " ]");
        System.out.println("- 아우터: " + (codi.getOuter() != null ? codi.getOuter().getClName() : "없음"));
        System.out.println("- 상의  : " + (codi.getTop() != null ? codi.getTop().getClName() : "없음"));
        System.out.println("- 하의  : " + (codi.getBottom() != null ? codi.getBottom().getClName() : "없음"));
        System.out.println("- 신발  : " + (codi.getShoes() != null ? codi.getShoes().getClName() : "없음"));
        System.out.println();
        System.out.println("[ 🎨 OUTFIT LOOKBOOK ]");
        System.out.println();
        System.out.println("      .---.  .---.      ");
        System.out.println("     /   / \\  \\    <-- [아우터/상의] " + 
                           (codi.getOuter() != null ? codi.getOuter().getClName() + " + " : "") + 
                           (codi.getTop() != null ? codi.getTop().getClName() : ""));
        System.out.println("    /  /  | |  \\   ");
        System.out.println("   (__(   | |   )__)");
        System.out.println("       |__|__|      ");
        System.out.println("       |  |  |      <-- [하의] " + (codi.getBottom() != null ? codi.getBottom().getClName() : ""));
        System.out.println("       |  |  |      ");
        System.out.println("       |__|__|      ");
        System.out.println("      /===//===\\     <-- [신발] " + (codi.getShoes() != null ? codi.getShoes().getClName() : ""));
        System.out.println("      \\___\\\\___/    ");
        System.out.println("==================================================");
    }
}