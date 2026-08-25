package controller;

import java.util.ArrayList;

import model.dao.ClosetDao;
import model.dto.ClosetDto;

public class ClosetController {
    private ClosetController(){}
    private static final ClosetController instance = new ClosetController();
    public static ClosetController getInstance(){return instance;}
    private ClosetDao cl_d = ClosetDao.getInstance();

    // 의류 등록
    public boolean clothesAdd(ClosetDto closetDto){

        if(closetDto.getM_no() <= 0){ // 회원 번호가 있는지 검사
            return false; 
        }
        if(closetDto.getC_no() <= 0){ // 카테고리번호가 있는지 검사
            return false;
        }
        if(closetDto.getCl_color() == null || closetDto.getCl_color().isBlank()){ // 색상이 공백인지 검사
            return false;
        }
        return cl_d.clothesAdd(closetDto);
    }
    
    // 의류 전체 조회
    public ArrayList<ClosetDto> clothesPrintAll(int m_no){
        ArrayList<ClosetDto> result = cl_d.clothesPrintAll(m_no);
        return result; 
    }

    // 의류 개별 조회 
    public ClosetDto clothesPrint(int m_no, int cl_no){
        ClosetDto result = cl_d.clothesPrint(m_no, cl_no);
        return result;
    }

    // 의류 삭제 ( 개별 조회 페이지 -> 삭제 )
    public boolean clothesDelete(int m_no, int cl_no){
        boolean result = cl_d.clothesDelete(m_no,cl_no);
        return result;
    }

    // 의류번호 검사(옷장에 존재여부) 
    public boolean clothesNoCheck(int m_no, int cl_no){
        boolean result = cl_d.clothesNoCheck(m_no,cl_no);
        return result;
    }
}
