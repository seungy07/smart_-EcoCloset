package model.dao;

import model.dto.CodiDto.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CodiDao extends BaseDao {
    private static CodiDao instance = new CodiDao();
    private CodiDao() {}
    public static CodiDao getInstance() { return instance; }

    // 1. 계절별 의류 조회 (현재 월 및 로그인한 사용자 m_no 기준)
    // 카테고리 규칙: (c_no / 100) % 10 -> 1: SS, 2: FW, 3: ALL
    public ArrayList<SeasonClothesDto> seasonSearch(int mNo, int targetSeason) {
        ArrayList<SeasonClothesDto> list = new ArrayList<>();
        // 착용 횟수 적은 순(COUNT(w.w_no) ASC)으로 정렬
        String sql = "SELECT c.cl_no, c.c_no, c.cl_name, c.cl_color, " +
                     "COUNT(w.w_no) AS wear_count, MAX(w.w_context) AS last_date " +
                     "FROM clothes c " +
                     "LEFT JOIN wearLog w ON c.cl_no = w.cl_no " +
                     "WHERE c.m_no = ? AND c.re_type IS NULL " +
                     "  AND (MOD(TRUNCATE(c.c_no / 100, 0), 10) = ? OR MOD(TRUNCATE(c.c_no / 100, 0), 10) = 3) " +
                     "GROUP BY c.cl_no, c.c_no, c.cl_name, c.cl_color " +
                     "ORDER BY wear_count ASC";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mNo);
            ps.setInt(2, targetSeason);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SeasonClothesDto dto = new SeasonClothesDto(
                        rs.getInt("cl_no"),
                        rs.getInt("c_no"),
                        rs.getString("cl_name"),
                        rs.getString("cl_color"),
                        rs.getInt("wear_count"),
                        rs.getString("last_date")
                );
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. 착용 기록 등록
    public boolean wearAdd(WearLogDto wearDto) {
        String sql = "INSERT INTO wearLog(cl_no, w_context) VALUES (?, ?)";
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int clNo : wearDto.getClNoList()) {
                ps.setInt(1, clNo);
                ps.setString(2, wearDto.getDate());
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
            return true;
        } catch (Exception e) {
            try { conn.rollback(); } catch (Exception rollbackEx) {}
            e.printStackTrace();
        }
        return false;
    }

    // 3. 마지막 착용일 조회
    public String wearPrintAll(int clNo) {
        String sql = "SELECT MAX(w_context) FROM wearLog WHERE cl_no = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, clNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4. 총 착용 횟수 조회
    public int wearCountPrint(int clNo) {
        String sql = "SELECT COUNT(*) FROM wearLog WHERE cl_no = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, clNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}