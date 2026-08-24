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
            String sql = "select\r\n" + //
                                "cl.cl_no AS \"의류번호\",\r\n" + //
                                "cl.cl_name AS '의류이름', \r\n" + //
                                "MAX(w.w_context) AS '마지막 착용',\r\n" + //
                                "DATEDIFF(CURDATE(), MAX(w.w_context)) AS '미착용일'\r\n" + //
                                "from users u inner join clothes cl on u.m_no = cl.m_no\r\n" + //
                                "join wearlog w on cl.cl_no = w.cl_no\r\n" + //
                                "WHERE u.m_no = ?\r\n" + //
                                "GROUP BY u.m_no, cl.cl_no, cl.cl_name\r\n" + //
                                "HAVING DATEDIFF(CURDATE(), MAX(w.w_context)) >= 90;";

            // 1-2. 연동된 데이터베이스에 SQL 기재하기
            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-3. 와일드카드에 유저번호(m_no) 가져오기
            // ps.setint(1, );

            // 1-4. 기재된 SQL을 실행하기
            ResultSet rs = ps.executeQuery();

            // 1-5. SQL 결과 가져오기(테이블 형태로 반환)
            while (rs.next()) {// 각 레코드마다 순회
                RecycleDto recycleDto = new RecycleDto();
                recycleDto.setNo(rs.getInt("의류번호"));
                recycleDto.setName(rs.getString("의류이름"));
                recycleDto.setWearCount(rs.getInt("착용횟수"));
                recycleDto.setDate(rs.getString("마지막 착용"));
                recycleDto.setUnUsedDays(rs.getInt("미착용일"));

                list.add(recycleDto); // 각 레코드들 RecycleDto 배열에 추가
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return list; // 레코드 담은 배열 반환
    }
}
