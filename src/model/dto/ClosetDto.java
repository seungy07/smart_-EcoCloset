package model.dto;

public class ClosetDto {
    private int cl_no;
    private int m_no;
    private int c_no;
    private String cl_color;
    private String cl_name;
    private String re_type;

    public ClosetDto(){}
    
    public ClosetDto(int cl_no, int m_no, int c_no, String cl_color, String cl_name, String re_type) {
        this.cl_no = cl_no;
        this.m_no = m_no;
        this.c_no = c_no;
        this.cl_color = cl_color;
        this.cl_name = cl_name;
        this.re_type = re_type;
    }





    public int getCl_no() {
        return cl_no;
    }
    public void setCl_no(int cl_no) {
        this.cl_no = cl_no;
    }
    public int getM_no() {
        return m_no;
    }
    public void setM_no(int m_no) {
        this.m_no = m_no;
    }
    public int getC_no() {
        return c_no;
    }
    public void setC_no(int c_no) {
        this.c_no = c_no;
    }
    public String getCl_color() {
        return cl_color;
    }
    public void setCl_color(String cl_color) {
        this.cl_color = cl_color;
    }
    public String getCl_name() {
        return cl_name;
    }
    public void setCl_name(String cl_name) {
        this.cl_name = cl_name;
    }
    public String getRe_type() {
        return re_type;
    }
    public void setRe_type(String re_type) {
        this.re_type = re_type;
    }

    


}
