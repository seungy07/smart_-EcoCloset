package model.dto;

public class RecycleDto {
    int no;
    String name;
    int wearCount;
    String date;
    int unUsedDays;

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
