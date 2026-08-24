package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.dto.MemberDto;

public class MemberDao extends BaseDao{
    private MemberDao(){}
    private static final MemberDao instance = new MemberDao();
    public static MemberDao getInstance(){
        return instance;
    }

    // 1. 아이디 유효성 검사
    public boolean inCheck(String m_id){

        try{
            String sql = "select * from users where m_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, m_id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return false;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return true;
    }

    // 2. 회원가입
    public boolean signUp(MemberDto memberDto){
        try{
            String sql = "insert into users(m_id, m_pwd) values(?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, memberDto.getM_id());
            ps.setString(2, memberDto.getM_pwd());

            int result = ps.executeUpdate();

            if(result == 1){
                return true;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    // 3. 로그인
    public MemberDto login(String m_id, String m_pwd){
        try{
            String sql = "select * from users where m_id = ? and m_pwd = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, m_id);
            ps.setString(2, m_pwd);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                MemberDto memberDto = new MemberDto(
                    rs.getInt("m_no"),
                    rs.getString("m_id"),
                    rs.getString("m_pwd")
                );
                return memberDto;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
// 4. 회원탈퇴
public boolean memberDelete(int m_no){
    try{
        String sql = "delete from users where m_no = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setInt(1, m_no);

        int result = ps.executeUpdate();

        if( result == 1){
            return true;
        }
    }catch(Exception e){
        System.out.println(e);
    }
    return false;
    }
 
}
