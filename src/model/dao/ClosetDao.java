package model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.dto.ClosetDto;

public class ClosetDao extends BaseDao{
    private ClosetDao(){super();}
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

    

}
