package controller;

import model.dao.CodiDao;
import model.dto.CodiDto;
import model.dto.SeasonClothesDto;
import model.dto.WearDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodiController {
    private static CodiController instance = new CodiController();
    private CodiController() {}
    public static CodiController getInstance() { return instance; }

    // 무채색 리스트
    private final List<String> NEUTRAL_COLORS = Arrays.asList("white", "black", "gray");

    // 계절 매칭 검사 (3~9월: SS(1), 10~2월: FW(2))
    public ArrayList<SeasonClothesDto> seasonSearch(int mNo) {
        int month = LocalDate.now().getMonthValue();
        int season = (month >= 3 && month <= 9) ? 1 : 2; // 1: SS, 2: FW
        return CodiDao.getInstance().seasonSearch(mNo, season);
    }

    // 코디 추천 로직
    public ArrayList<CodiDto> outfitRecommend(ArrayList<SeasonClothesDto> clothesList, String preferredColor) {
        ArrayList<CodiDto> recommendations = new ArrayList<>();

        // 카테고리별 분리 (1000: 상의, 2000: 하의, 3000: 아우터, 4000: 신발)
        List<SeasonClothesDto> tops = new ArrayList<>();
        List<SeasonClothesDto> bottoms = new ArrayList<>();
        List<SeasonClothesDto> outers = new ArrayList<>();
        List<SeasonClothesDto> shoes = new ArrayList<>();

        for (SeasonClothesDto c : clothesList) {
            int mainCategory = c.getCNo() / 1000;
            if (mainCategory == 1) tops.add(c);
            else if (mainCategory == 2) bottoms.add(c);
            else if (mainCategory == 3) outers.add(c);
            else if (mainCategory == 4) shoes.add(c);
        }

        // 착용 횟수 적은 순 + 색상 매칭 추천 생성
        for (SeasonClothesDto top : tops) {
            for (SeasonClothesDto bottom : bottoms) {
                // 색상 매칭 조건 검사
                if (isColorMatch(top.getClColor(), bottom.getClColor(), preferredColor)) {
                    SeasonClothesDto outer = outers.isEmpty() ? null : outers.get(0);
                    SeasonClothesDto shoe = shoes.isEmpty() ? null : shoes.get(0);

                    CodiDto codi = new CodiDto(outer, top, bottom, shoe, "미니멀 데일리 룩");
                    recommendations.add(codi);

                    if (recommendations.size() >= 3) break; // 최대 3개 추천
                }
            }
            if (recommendations.size() >= 3) break;
        }

        return recommendations;
    }

    // 색상 조합 규칙
    private boolean isColorMatch(String topColor, String bottomColor, String preferredColor) {
        topColor = topColor.toLowerCase();
        bottomColor = bottomColor.toLowerCase();

        // 사용자 선호 색상 조건
        if (preferredColor != null && !preferredColor.isEmpty() && !preferredColor.equals("all")) {
            if (!topColor.equals(preferredColor) && !bottomColor.equals(preferredColor)) {
                return false;
            }
        }

        // 1. 무채색(White, Black, Gray)은 어떤 색상과도 조합 가능
        if (NEUTRAL_COLORS.contains(topColor) || NEUTRAL_COLORS.contains(bottomColor)) {
            return true;
        }

        // 상의와 하의 중 하나가 베이지이고 다른 하나가 옐로우인 경우 검사
        boolean isTopBeige = topColor.equals("beige");
        boolean isBottomBeige = bottomColor.equals("beige");
        boolean isTopYellow = topColor.equals("yellow");
        boolean isBottomYellow = bottomColor.equals("yellow");

        // 2. 베이지: yellow 제외 모든 색과 조합 가능
        if (isTopBeige || isBottomBeige) {
            if (isTopYellow || isBottomYellow) {
                return false; // 베이지 + 옐로우 조합 불가
            }
            return true;
        }

        // 3. yellow / skyblue / pink / red / blue 는 navy / brown / khaki 만 가능
        List<String> pointColors = Arrays.asList("yellow", "skyblue", "pink", "red", "blue");
        List<String> matchBaseColors = Arrays.asList("navy", "brown", "khaki");

        if (pointColors.contains(topColor)) {
            return matchBaseColors.contains(bottomColor);
        }

        if (pointColors.contains(bottomColor)) {
            return matchBaseColors.contains(topColor);
        }

        return false;
    }

    // 착용 기록 등록
    public boolean wearAdd(WearDto wearDto) {
        return CodiDao.getInstance().wearAdd(wearDto);
    }

    // 마지막 착용 기록 조회
    public String wearPrintAll(int clNo) {
        return CodiDao.getInstance().wearPrintAll(clNo);
    }

    // 착용 횟수 조회
    public int wearCountPrint(int clNo) {
        return CodiDao.getInstance().wearCountPrint(clNo);
    }
}