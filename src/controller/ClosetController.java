package controller;

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
    
    

}
