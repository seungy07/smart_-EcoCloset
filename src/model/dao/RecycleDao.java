package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.MemberController;
import model.dto.RecycleDto;

public class RecycleDao extends BaseDao{

    private RecycleDao(){};
    private static final RecycleDao instance = new RecycleDao();
    public static RecycleDao getInstance() { return instance; }

    public ArrayList<RecycleDto> unusedReport(int m_no){
        ArrayList<RecycleDto> list = new ArrayList<>();
        try {
            // 1-1. SQL 작성
            String sql = "SELECT " + 
             "    cl.cl_no AS '의류번호', " + 
             "    cl.cl_name AS '의류이름', " + 
             "    COUNT(w.w_no) AS '착용횟수', " + 
             "    MAX(w.w_context) AS '마지막 착용', " + 
             "    IFNULL(DATEDIFF(CURDATE(), MAX(w.w_context)), -1) AS '미착용일' " +
             "FROM users u INNER JOIN clothes cl ON u.m_no = cl.m_no " + 
             "JOIN wearlog w ON cl.cl_no = w.cl_no " + 
             "WHERE u.m_no = ? AND (cl.re_type IS NULL OR cl.re_type = '') " + 
             "GROUP BY u.m_no, cl.cl_no, cl.cl_name " + 
             "HAVING DATEDIFF(CURDATE(), MAX(w.w_context)) >= 90;";
            // 1-2. 연동된 데이터베이스에 SQL 기재하기
            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-3. 와일드카드에 유저번호(m_no) 가져오기
            ps.setInt(1, m_no);

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

    public boolean reCycleAdd(int ch_no, int caseNum, RecycleDto dto, int m_no){
        boolean result = false;
        String reTypeStr = ""; // 어떻게 처리하는지를 담은 문자열(caseNum에 따라 달라짐)
        try {
            // 2-1. 처리 유형에 따른 문자열 지정
            if (caseNum == 1) {
                System.out.println("> 해당 의류를 계속 보관합니다.");
                return false;
            } else if (caseNum == 2) {
                reTypeStr = "기부";
            } else if (caseNum == 3) {
                reTypeStr = "나눔";
            } else if (caseNum == 4) {
                reTypeStr = "중고판매";
            } else if (caseNum == 5) {
                reTypeStr = "폐기";
            } else {
                System.out.println(">유효하지 않은 입력으로 인해 처리가 중단되었습니다.");
                return result;
            }

            // 2-2. SQL 작성
            String sql = "UPDATE clothes SET re_type = ? WHERE m_no = ? and cl_no = ?";
            // 2-2. 연동된 DB에 SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);

            // 2-3. 와일드카드에 선택한 의류번호 넣기(cl_no)
            ps.setString(1, reTypeStr);
            ps.setInt(2, m_no);
            ps.setInt(3, ch_no);

            // 2-4. 기재된 SQL 실행
            // executeUpdate()를 조건문 안에다만 작성해도, 일단 조건문을 평가하기 위해 메서드를 무조건 실행하므로 실제로도 작동함
            if (ps.executeUpdate() > 0) { 
                result = true;
                System.out.println("해당 의류를 ["+reTypeStr+"] 처리합니다.");
            }
        } catch (Exception e) {
            System.out.println("처리 중 오류가 발생했습니다." + e);
        }

        return result;
    }

    public ArrayList<RecycleDto> findMaxWearCount(int m_no){
        ArrayList<RecycleDto> result = new ArrayList<>();
        try {
            // 1-1. SQL 작성
            String sql = "SELECT \r\n" + //
                                "    cl.cl_no AS '의류번호',\r\n" + //
                                "    cl.cl_name AS '의류이름',\r\n" + //
                                "    COUNT(w.w_no) AS '착용횟수'\r\n" + //
                                "FROM users u\r\n" + //
                                "INNER JOIN clothes cl ON u.m_no = cl.m_no\r\n" + //
                                "JOIN wearlog w ON cl.cl_no = w.cl_no\r\n" + //
                                "WHERE u.m_no = ?\r\n" + //
                                "GROUP BY cl.cl_no, cl.cl_name\r\n" + //
                                "ORDER BY 착용횟수 DESC\r\n" + //
                                "LIMIT 3;";
            // 1-2. 연동된 데이터베이스에 SQL 기재하기
            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-3. 와일드카드에 유저번호(m_no) 가져오기
            ps.setInt(1, m_no);

            // 1-4. 기재된 SQL을 실행하기
            ResultSet rs = ps.executeQuery();

            // 1-5. SQL 결과 가져오기(테이블 형태로 반환)
            while (rs.next()) {// 각 레코드마다 순회
                RecycleDto recycleDto = new RecycleDto();
                recycleDto.setNo(rs.getInt("의류번호"));
                recycleDto.setName(rs.getString("의류이름"));
                recycleDto.setWearCount(rs.getInt("착용횟수"));

                result.add(recycleDto); // 각 레코드들 RecycleDto 배열에 추가
            }
        } catch (SQLException e) {
            System.out.println(e);
        }


        return result;
    }

    public ArrayList<RecycleDto> findMinWearCount(int m_no) {
        ArrayList<RecycleDto> result = new ArrayList<>();
        try {
            // 1-1. SQL 작성
            String sql = "SELECT \r\n" + //
                    "    cl.cl_no AS '의류번호',\r\n" + //
                    "    cl.cl_name AS '의류이름',\r\n" + //
                    "    COUNT(w.w_no) AS '착용횟수'\r\n" + //
                    "FROM users u\r\n" + //
                    "INNER JOIN clothes cl ON u.m_no = cl.m_no\r\n" + //
                    "LEFT JOIN wearlog w ON cl.cl_no = w.cl_no\r\n" + //
                    "WHERE u.m_no = ? AND (cl.re_type IS NULL OR cl.re_type = '')\r\n" + //
                    "GROUP BY cl.cl_no, cl.cl_name\r\n" + //
                    "ORDER BY 착용횟수 ASC\r\n" + //
                    "LIMIT 3;";
            // 1-2. 연동된 데이터베이스에 SQL 기재하기
            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-3. 와일드카드에 유저번호(m_no) 가져오기
            ps.setInt(1, m_no);

            // 1-4. 기재된 SQL을 실행하기
            ResultSet rs = ps.executeQuery();

            // 1-5. SQL 결과 가져오기(테이블 형태로 반환)
            while (rs.next()) {// 각 레코드마다 순회
                RecycleDto recycleDto = new RecycleDto();
                recycleDto.setNo(rs.getInt("의류번호"));
                recycleDto.setName(rs.getString("의류이름"));
                recycleDto.setWearCount(rs.getInt("착용횟수"));

                result.add(recycleDto); // 각 레코드들 RecycleDto 배열에 추가
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return result;
    }

    public RecycleDto recyclePrint(int m_no){
        // 결과물을 담아서 보낼 DTO -> 한 유저의 종합 순환 리포트 == 레코드 단 하나
        RecycleDto dto = new RecycleDto();

        // ps와 rs 변수를 미리 선언해 둠(재사용을 해야 하므로 null로 선언.)
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // [1] 현재 보유, 기부, 나눔, 중고판매 의류 총합 =======================================
            // 1-1. sql 작성
            String sql1 = "SELECT " +
                    "    COUNT(DISTINCT IF(re_type IS NULL OR re_type = '', cl_name, NULL)) AS '보유총합', " +
                    "    COUNT(IF(re_type = '기부', 1, NULL)) AS '기부총합', " +
                    "    COUNT(IF(re_type = '나눔', 1, NULL)) AS '나눔총합', " +
                    "    COUNT(IF(re_type = '중고판매', 1, NULL)) AS '중고판매총합' " +
                    "FROM clothes WHERE m_no = ?";

            // 1-2 sql 기재
            ps = conn.prepareStatement(sql1);

            // 1-3 매개변수(유저번호) 대입
            ps.setInt(1, m_no);

            // 1-4. SQL 실행
            rs = ps.executeQuery();

            // 1-5. SQL 실행결과 DTO에 옮기기
            if (rs.next()) {
                dto.setClothesSum(rs.getInt("보유총합"));
                dto.setClothesDonation(rs.getInt("기부총합"));
                dto.setClothesShare(rs.getInt("나눔총합"));
                dto.setUsedClothesSale(rs.getInt("중고판매총합"));
            }
            
            // 1-6. 자원 해제하기(메모리 누수 방지 차원)
            if (rs != null) rs.close(); 
            if (ps != null) ps.close();

            // [2] 이번 달 착용 횟수(회)=======================================================
            // 2.1 SQL 작성
            String sql2 = "SELECT COUNT(*) AS '이번달착용' FROM wearlog w " +
                    "JOIN clothes cl ON w.cl_no = cl.cl_no " +
                    "WHERE cl.m_no = ? AND YEAR(w.w_context) = YEAR(CURDATE()) AND MONTH(w.w_context) = MONTH(CURDATE())";

            // 2-2. SQL 기재
            ps = conn.prepareStatement(sql2);

            // 2-3. 매개변수 대입(유저번호)
            ps.setInt(1, m_no);

            // 2-4. SQL 실행
            rs = ps.executeQuery();

            // 2-5. SQL 결과 dto에 담기
            if (rs.next()) {
                dto.setWearCountInMonth(rs.getInt("이번달착용"));
            }

            // 2-6. 자원 해제
            if (rs != null) { rs.close(); } if (ps != null) { ps.close(); }

            // [3] 90일 이상 미착용 의류 총 개수)=======================================================
            // 3-1. SQL 작성
            String sql3 = "SELECT COUNT(*) AS '미착용총합' FROM (" +
                    "    SELECT cl.cl_no FROM clothes cl JOIN wearlog w ON cl.cl_no = w.cl_no " +
                    "    WHERE cl.m_no = ? AND (cl.re_type IS NULL OR cl.re_type = '') " +
                    "    GROUP BY cl.cl_no HAVING DATEDIFF(CURDATE(), MAX(w.w_context)) >= 90" +
                    ") AS sub";

            // 3-2. SQL 기재
            ps = conn.prepareStatement(sql3);

            // 3-3. 매개변수 대입(유저번호)
            ps.setInt(1, m_no);

            // 3-4. SQL 실행
            rs = ps.executeQuery();

            // 3-5. SQL 결과 dto에 옮김
            if (rs.next()) {
                dto.setUnUsedClothesSum(rs.getInt("미착용총합"));
            }

            // 3-6. 자원 해제
            if (rs != null) rs.close(); if (ps != null) ps.close();

            // [4] 가장 많이 입은 옷 이름과 그 옷을 입은 횟수 가져오기)=======================================================
            // 4-1. SQL 작성
            String sql4 = "SELECT cl.cl_name AS '의류이름', COUNT(w.w_no) AS '착용횟수' " +
                    "FROM clothes cl JOIN wearlog w ON cl.cl_no = w.cl_no " +
                    "WHERE cl.m_no = ? " +
                    "GROUP BY cl.cl_no, cl.cl_name ORDER BY 착용횟수 DESC LIMIT 1";
            
            // 4-2. SQL 기재
            ps = conn.prepareStatement(sql4);

            // 4-3. 유저번호 대입
            ps.setInt(1, m_no);

            // 4-4. SQL 실행
            rs = ps.executeQuery();

            // 4-5. 실행 결과 dto에 옮김
            while (rs.next()) {
                dto.setMostClothes(rs.getString("의류이름"));
                dto.setMostClothesCount(rs.getInt("착용횟수"));
            }

            // 4-6. 자원 해제
            if( rs != null ) { rs.close(); } if( ps != null ) { ps.close(); }

            // [5] 가장 오래 입지 않은 옷 이름과 그 옷을 방치한 기간(일) 가져오기)=======================================================
            // 5-1. SQL 작성
            String sql5 = "SELECT cl.cl_name AS '의류이름', DATEDIFF(CURDATE(), MAX(w.w_context)) AS '미착용일수' " +
                    "FROM clothes cl JOIN wearlog w ON cl.cl_no = w.cl_no " +
                    "WHERE cl.m_no = ? AND (cl.re_type IS NULL OR cl.re_type = '') " +
                    "GROUP BY cl.cl_no, cl.cl_name ORDER BY 미착용일수 DESC LIMIT 1";
            
            // 5-2. SQL 기재
            ps = conn.prepareStatement(sql5);

            // 5-3. 유저번호 대입
            ps.setInt(1, m_no);
            
            // 5-4 SQL 실행
            rs = ps.executeQuery();

            // 5-5. 실행 결과 dto에 옮기기
            while (rs.next()) {
                dto.setOldestUnusedClothes(rs.getString("의류이름"));
                dto.setOldestUnusedDays(rs.getInt("미착용일수"));
            }
        } catch (SQLException e) {
            System.out.println("순환 리포트 생성 중 오류 발생\n" + e);
        } finally {
            // 맨 마지막에 자원 해제
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return dto;
    }
}
