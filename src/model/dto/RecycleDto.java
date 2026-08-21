package model.dto;

public class RecycleDto {
    int no;
    String name;
    String date;
    int days;
    
    public RecycleDto(){}
    public RecycleDto(int no, String name, String date, int days) {
        this.no = no;
        this.name = name;
        this.date = date;
        this.days = days;
    }
    
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
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getDays() {
        return days;
    }
    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "RecycleDto [no=" + no + ", name=" + name + ", date=" + date + ", days=" + days + "]";
    };
}
