package controller;

import java.util.ArrayList;

import model.dao.RecycleDao;
import model.dto.MemberDto;
import model.dto.RecycleDto;;

public class RecycleController {
    private RecycleController(){};
    private static final RecycleController instance = new RecycleController();
    public static RecycleController getInstance() { return instance; }
    private RecycleDao rd = RecycleDao.getInstance();

    // 현재 로그인한 회원 번호 가져오기
    private int getLoginM_no(){
        MemberDto loginM_no =
            MemberController.getInstance().getLoginMember();
        // 로그인 상태가 아니면 등록 불가
        if(loginM_no == null){
            return -1;
        }
        return loginM_no.getM_no();

    }

    public ArrayList<RecycleDto> unusedReport(){
        int m_no = getLoginM_no();
        if(m_no == -1){return new ArrayList<>();}
        ArrayList<RecycleDto> result = rd.unusedReport(m_no);

        return result;
    }

    public boolean reCycleAdd(int ch_no, int caseNum, RecycleDto dto){
        int m_no = getLoginM_no();
        if(m_no == -1){return false;}
        boolean result = rd.reCycleAdd(ch_no, caseNum, dto, m_no);

        return result;
    }

    public ArrayList<RecycleDto> findMaxWearCount(){
        int m_no = getLoginM_no();
        if(m_no == -1){return new ArrayList<>();}
        ArrayList<RecycleDto> result = rd.findMaxWearCount(m_no);

        return result;
    }

    public ArrayList<RecycleDto> findMinWearCount() {
        int m_no = getLoginM_no();
        if(m_no == -1){return new ArrayList<>();}
        ArrayList<RecycleDto> result = rd.findMinWearCount(m_no);

        return result;
    }

    public void recyclePrint(){
        
    }
}
