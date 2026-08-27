package model.dto;

public class RecycleDto {
    int no; // 의류 번호
    String name; // 의류 이름
    int wearCount; // 입은 횟수
    String date; // 마지막 착용일
    int unUsedDays; // 안입은 일 수

    // 2. 의류 순환 리포트를 위해 사용할 멤버변수
    int clothesSum; // 현재 보유 의류 총합(벌)
    int wearCountInMonth; // 이번 달 동안 착용한 횟수(회)
    int unUsedClothesSum; // 90일 이상 미착용한 의류 총합(벌)

    int clothesDonation; // 기부한 옷 총합(벌)
    int clothesShare; // 나눔한 옷 총합(벌)
    int usedClothesSale; // 중고판매한 옷 총합(벌)
    int wastedClothes;   // 폐기한 옷 총합(벌)
    int totalRecycleSum; // 총 순환 의류(벌)

    String mostClothes; // 가장 많이 입은 옷의 이름
    int mostClothesCount; // 가장 많이 입은 옷의 '입은 횟수'

    String oldestUnusedClothes; // 가장 오래 안 입은 옷의 이름
    int oldestUnusedDays; // 가장 오래 입지 입은 옷의 입지 않은 기간

    



    // 기본 Getter and Setter들
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

    public int getClothesSum() {
        return clothesSum;
    }

    public void setClothesSum(int clothesSum) {
        this.clothesSum = clothesSum;
    }

    public int getWearCountInMonth() {
        return wearCountInMonth;
    }

    public void setWearCountInMonth(int wearCountInMonth) {
        this.wearCountInMonth = wearCountInMonth;
    }

    public int getUnUsedClothesSum() {
        return unUsedClothesSum;
    }

    public void setUnUsedClothesSum(int unUsedClothesSum) {
        this.unUsedClothesSum = unUsedClothesSum;
    }

    public int getClothesDonation() {
        return clothesDonation;
    }

    public void setClothesDonation(int clothesDonation) {
        this.clothesDonation = clothesDonation;
    }

    public int getClothesShare() {
        return clothesShare;
    }

    public void setClothesShare(int clothesShare) {
        this.clothesShare = clothesShare;
    }

    public int getUsedClothesSale() {
        return usedClothesSale;
    }

    public void setUsedClothesSale(int usedClothesSale) {
        this.usedClothesSale = usedClothesSale;
    }

    public int getWastedClothes() {
        return wastedClothes;
    }

    public void setWastedClothes(int wastedClothes) {
        this.wastedClothes = wastedClothes;
    }

    public String getMostClothes() {
        return mostClothes;
    }

    public void setMostClothes(String mostClothes) {
        this.mostClothes = mostClothes;
    }

    public int getMostClothesCount() {
        return mostClothesCount;
    }

    public void setMostClothesCount(int mostClothesCount) {
        this.mostClothesCount = mostClothesCount;
    }

    public String getOldestUnusedClothes() {
        return oldestUnusedClothes;
    }

    public void setOldestUnusedClothes(String oldestUnusedClothes) {
        this.oldestUnusedClothes = oldestUnusedClothes;
    }

    public int getOldestUnusedDays() {
        return oldestUnusedDays;
    }

    public void setOldestUnusedDays(int oldestUnusedDays) {
        this.oldestUnusedDays = oldestUnusedDays;
    }

    // 총 의류 순환 계산용, 기부+나눔+중고판매를 더함
    public int getTotalRecycleSum() {
        return clothesDonation + clothesShare + usedClothesSale + wastedClothes;
    }

    @Override
    public String toString() {
        return "RecycleDto [no=" + no + ", name=" + name + ", wearCount=" + wearCount + ", date=" + date
                + ", unUsedDays=" + unUsedDays + ", clothesSum=" + clothesSum + ", wearCountInMonth=" + wearCountInMonth
                + ", unUsedClothesSum=" + unUsedClothesSum + ", clothesDonation=" + clothesDonation + ", clothesShare="
                + clothesShare + ", usedClothesSale=" + usedClothesSale + ", wastedClothes=" + wastedClothes
                + ", totalRecycleSum=" + totalRecycleSum + ", mostClothes=" + mostClothes + ", mostClothesCount="
                + mostClothesCount + ", oldestUnusedClothes=" + oldestUnusedClothes + ", oldestUnusedDays="
                + oldestUnusedDays + "]";
    }


}
