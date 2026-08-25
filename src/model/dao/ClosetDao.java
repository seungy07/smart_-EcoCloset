package model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import model.dto.ClosetDto;

public class ClosetDao extends BaseDao{
    private ClosetDao(){}
    private static final ClosetDao instance = new ClosetDao();
    public static ClosetDao getInstance(){return instance;}

    // 의류 등록
    public boolean clothesAdd(ClosetDto closetDto){
        try{
            String sql = "Insert into clothes(m_no, c_no, cl_color, cl_name) values(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, closetDto.getM_no());
            ps.setInt(2, closetDto.getC_no());
            ps.setString(3, closetDto.getCl_color());
            ps.setString(4, closetDto.getCl_name());
            int r = ps.executeUpdate();
            if( r==1 ){ return true; }

        }catch(SQLException e){System.out.println("연동실패"+e);}
        return false;
    }
    
    public ArrayList<ClosetDto> clothesPrintAll(int m_no){
        ArrayList<ClosetDto> list = new ArrayList<>();

        try{
            String sql = "select * from clothes where m_no = ? and re_type is null";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, m_no);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ClosetDto dto = new ClosetDto();
                dto.setCl_no(rs.getInt("cl_no"));
                // dto.setM_no(rs.getInt("m_no"));  // 회원번호는 생략
                dto.setC_no(rs.getInt("c_no"));
                dto.setCl_color(rs.getString("cl_color"));
                dto.setCl_name(rs.getString("cl_name"));

                list.add(dto);
            }
        }catch(SQLException e){System.out.println(e);}
        return list;
    }

    // 의류 개별 조회
    public ClosetDto clothesPrint(int m_no, int cl_no){
        try{
            String sql = "select cl_no, c_no, cl_color, cl_name from clothes where m_no = ? and cl_no = ? and re_type is null";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, m_no);
            ps.setInt(2, cl_no);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ClosetDto dto = new ClosetDto();
                dto.setCl_no(rs.getInt("cl_no"));
                dto.setC_no(rs.getInt("c_no"));
                dto.setCl_color(rs.getString("cl_color"));
                dto.setCl_name(rs.getString("cl_name"));
                return dto;
            }
        }catch(SQLException e){System.out.println(e);}
        return null;
    }

    // 의류 삭제 ( 행 삭제 )
    public boolean clothesDelete(int m_no, int cl_no){
        try{
            String sql = "delete from clothes where m_no =? and cl_no =? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, m_no);
            ps.setInt(2, cl_no);
            int r = ps.executeUpdate();
            if(r==1){return true;}
        }catch(SQLException e){System.out.println(e);}
        return false;
    }

    // 의류번호 검사(옷장에 존재여부) 
    public boolean clothesNoCheck(int m_no, int cl_no){
        try{
            String sql = "select cl_no from clothes where m_no =? and cl_no=? and re_type is null";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, m_no);
            ps.setInt(2, cl_no);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){return true;}
        }catch(SQLException e){System.out.println(e);}
        return false;
    }



    

}
