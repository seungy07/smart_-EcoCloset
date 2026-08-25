package model.dto;

public class RecycleDto {
    int no; // 의류 번호
    String name; // 의류 이름
    int wearCount; // 입은 횟수
    String date; // 마지막 착용일
    int unUsedDays; // 안입은 일 수
    int clothesSum; // 현재 보유 의류 총합
    int unUsedClothesSum; // 90일 이상 미착용한 의류 총합
    int clothesDonation; // 기부한 옷 총합
    int clothesShare; // 나눔한 옷 총합
    int usedClothesSale; //중고판매한 옷 총합
    

    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getWearCount() {
        return wearCount;
    }
    public void setWearCount(int wearCount) {
        this.wearCount = wearCount;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getUnUsedDays() {
        return unUsedDays;
    }
    public void setUnUsedDays(int unUsedDays) {
        this.unUsedDays = unUsedDays;
    }
    
    @Override
    public String toString() {
        return "RecycleDto [no=" + no + ", name=" + name + ", wearCount=" + wearCount + ", date=" + date
                + ", unUsedDays=" + unUsedDays + "]";
    }

}
