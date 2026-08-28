package controller;

import java.util.ArrayList;

import model.dao.ClosetDao;
import model.dto.ClosetDto;
import model.dto.MemberDto;

public class ClosetController {
    private ClosetController(){}
    private static final ClosetController instance = new ClosetController();
    public static ClosetController getInstance(){return instance;}
    private ClosetDao cl_d = ClosetDao.getInstance();

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

    // 의류 등록
    public boolean clothesAdd(ClosetDto closetDto){
        // 현재 로그인한 회원번호
        int m_no = getLoginM_no();
        if(m_no == -1){return false;}
        closetDto.setM_no(m_no);
        
        if(closetDto.getM_no() <= 0){ // 회원 번호가 있는지 검사
            return false; }
        if(closetDto.getC_no() <= 0){ // 카테고리번호가 있는지 검사
            return false;}
        if(closetDto.getCl_color() == null || closetDto.getCl_color().isBlank()){ // 색상이 공백인지 검사
            return false;}
        return cl_d.clothesAdd(closetDto);
    }
    
    // 의류 전체 조회
    public ArrayList<ClosetDto> clothesPrintAll(){
        int m_no = getLoginM_no();
        if(m_no == -1){
        return new ArrayList<>();}
        ArrayList<ClosetDto> result = cl_d.clothesPrintAll(m_no);
        return result; 
    }

    // 의류 개별 조회 
    public ClosetDto clothesPrint(int cl_no){
        int m_no = getLoginM_no();
        if(m_no == -1){
            return null;}

        ClosetDto result = cl_d.clothesPrint(m_no, cl_no);
        return result;
    }

    // 의류 삭제 ( 개별 조회 페이지 -> 삭제 )
    public boolean clothesDelete(int cl_no){
        int m_no = getLoginM_no();
        if(m_no == -1){return false;}
        boolean result = cl_d.clothesDelete(m_no,cl_no);
        return result;
    }

    // 의류번호 검사(옷장에 존재여부) 
    public boolean clothesNoCheck(int cl_no){
        int m_no = getLoginM_no();
        if(m_no <= 0){return false;} // 회원 번호는 1,2,3.... 
        boolean result = cl_d.clothesNoCheck(m_no,cl_no);
        return result;
    }
}
