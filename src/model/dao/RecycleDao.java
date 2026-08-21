package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.RecycleDto;

public class RecycleDao extends BaseDao{
    private RecycleDao(){};
    private static final RecycleDao instance = new RecycleDao();
    public static RecycleDao getInstance() { return instance; }

    public ArrayList<RecycleDto> unusedReport(){
        ArrayList<RecycleDto> list = new ArrayList<>();
        try {
            // 1-1. SQL 작성
            String sql = "select * from clothes";

            // 1-2. 연동된 데이터베이스에 SQL 기재하기
            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-3. 기재된 SQL을 실행하기
            ResultSet rs = ps.executeQuery();

            // 1-4. SQL 결과 가져오기(테이블 형태로 반환)
            while (rs.next()) {// 각 레코드마다 순회
                RecycleDto recycleDto = new RecycleDto();
                recycleDto.setNo(rs.getInt("cl_no"));
                recycleDto.setName(rs.getString("cl_name"));
                recycleDto.setDate(rs.getString("w_context"));
                //추가로, 몇일 안입었는지를 어떻게 보여줄까를 고민해야 한다.

                list.add(recycleDto); // 각 레코드들 RecycleDto 배열에 추가
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return list; // 레코드 담은 배열 반환
    }
}
