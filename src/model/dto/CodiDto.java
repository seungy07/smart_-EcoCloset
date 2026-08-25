package model.dto;

import java.util.List;

// 1개 클래스 - 1개 용도 를 권장
// 문법적으로 문제없이 작동은 한다.
public class CodiDto {
    // 1. 추천 결과 담을 매개변수
    private CodiDto outer;
    private CodiDto top;
    private CodiDto bottom;
    private CodiDto shoes;

    // 2. 의류별 정보 매개변수
    private int clNo;
    private int cNo;
    private String clName;
    private String clColor;
    private int wearCount;
    private String lastWearDate;

    // 3. 착용 기록
    private String wearDate;
    private List<Integer> clNoList;

    // 기본 생성자
    public CodiDto() {}

    // 추천 조합 생성자
    public CodiDto(CodiDto outer, CodiDto top, CodiDto bottom, CodiDto shoes) {
        this.outer = outer;
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
    }

    // 필터링 의류 생성자
    public CodiDto(int clNo, int cNo, String clName, String clColor, int wearCount, String lastWearDate) {
        this.clNo = clNo;
        this.cNo = cNo;
        this.clName = clName;
        this.clColor = clColor;
        this.wearCount = wearCount;
        this.lastWearDate = lastWearDate;
    }

    // 착용 기록 생성자
    public CodiDto(String wearDate, List<Integer> clNoList) {
        this.wearDate = wearDate;
        this.clNoList = clNoList;
    }

    public CodiDto getOuter() { return outer; }
    public void setOuter(CodiDto outer) { this.outer = outer; }

    public CodiDto getTop() { return top; }
    public void setTop(CodiDto top) { this.top = top; }

    public CodiDto getBottom() { return bottom; }
    public void setBottom(CodiDto bottom) { this.bottom = bottom; }

    public CodiDto getShoes() { return shoes; }
    public void setShoes(CodiDto shoes) { this.shoes = shoes; }

    public int getClNo() { return clNo; }
    public void setClNo(int clNo) { this.clNo = clNo; }

    public int getCNo() { return cNo; }
    public void setCNo(int cNo) { this.cNo = cNo; }

    public String getClName() { return clName; }
    public void setClName(String clName) { this.clName = clName; }

    public String getClColor() { return clColor; }
    public void setClColor(String clColor) { this.clColor = clColor; }

    public int getWearCount() { return wearCount; }
    public void setWearCount(int wearCount) { this.wearCount = wearCount; }

    public String getLastWearDate() { return lastWearDate; }
    public void setLastWearDate(String lastWearDate) { this.lastWearDate = lastWearDate; }

    public String getWearDate() { return wearDate; }
    public void setWearDate(String wearDate) { this.wearDate = wearDate; }

    public List<Integer> getClNoList() { return clNoList; }
    public void setClNoList(List<Integer> clNoList) { this.clNoList = clNoList; }
}