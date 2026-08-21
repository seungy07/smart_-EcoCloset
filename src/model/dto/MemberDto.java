package model.dto;

public class MemberDto {
    
    private int m_no;
    private String m_id;
    private String m_pwd;

    // 기본 생성자
    public MemberDto(){}

    // 전체 생성자
    public MemberDto(int m_no, String m_id, String m_pwd){
        this.m_no = m_no;
        this.m_id = m_id;
        this.m_pwd = m_pwd;
    }
    // 회원가입용 생성자
    public MemberDto(String m_id, String m_pwd){
        this.m_id = m_id;
        this.m_pwd = m_pwd;
    }
    // 회원번호
    public int getM_no(){
        return m_no;
    }
    public void setM_no(int m_no){
        this.m_no = m_no;
    }
    // 회원 아이디
    public String getM_id(){
        return m_id;
    }
    public void setM_id(String m_id){
        this.m_id = m_id;
    }
    // 회원 비번
    public String getM_pwd(){
        return m_pwd;
    }
    public void setM_pwd(String m_pwd){
        this.m_pwd=m_pwd;
    }
    @Override
    public String toString(){
        return "MemberDto [m_no" +m_no
        +", m_id=" + m_id 
        +", m_pwd="+m_pwd + "]";
    }
}
