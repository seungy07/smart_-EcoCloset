package model.dto;
import java.util.List;

public class CodiDto {
    private SeasonClothesDto outer;
    private SeasonClothesDto top;
    private SeasonClothesDto bottom;
    private SeasonClothesDto shoes;

    public CodiDto() {}

    public CodiDto(SeasonClothesDto outer, SeasonClothesDto top, SeasonClothesDto bottom, SeasonClothesDto shoes) {
        this.outer = outer;
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
    }

    public SeasonClothesDto getOuter() { return outer; }
    public void setOuter(SeasonClothesDto outer) { this.outer = outer; }

    public SeasonClothesDto getTop() { return top; }
    public void setTop(SeasonClothesDto top) { this.top = top; }

    public SeasonClothesDto getBottom() { return bottom; }
    public void setBottom(SeasonClothesDto bottom) { this.bottom = bottom; }

    public SeasonClothesDto getShoes() { return shoes; }
    public void setShoes(SeasonClothesDto shoes) { this.shoes = shoes; }
}

public class SeasonClothesDto {
    private int clNo;
    private int cNo;
    private String clName;
    private String clColor;
    private int wearCount;
    private String lastWearDate;

    public SeasonClothesDto() {}

    public SeasonClothesDto(int clNo, int cNo, String clName, String clColor, int wearCount, String lastWearDate) {
        this.clNo = clNo;
        this.cNo = cNo;
        this.clName = clName;
        this.clColor = clColor;
        this.wearCount = wearCount;
        this.lastWearDate = lastWearDate;
    }

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
}

public class WearDto {
    private String date;
    private List<Integer> clNoList;

    public WearDto() {}

    public WearDto(String date, List<Integer> clNoList) {
        this.date = date;
        this.clNoList = clNoList;
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public List<Integer> getClNoList() { return clNoList; }
    public void setClNoList(List<Integer> clNoList) { this.clNoList = clNoList; }
}

