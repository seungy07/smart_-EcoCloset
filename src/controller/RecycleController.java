package controller;

import java.util.ArrayList;

import model.dao.RecycleDao;
import model.dto.RecycleDto;;

public class RecycleController {
    private RecycleController(){};
    private static final RecycleController instance = new RecycleController();
    public static RecycleController getInstance() { return instance; }
    private RecycleDao rd = RecycleDao.getInstance();


    public ArrayList<RecycleDto> unusedReport(){
        ArrayList<RecycleDto> result = rd.unusedReport();

        return result;
    }

    public boolean reCycleAdd(int ch_no, int caseNum, RecycleDto dto){
        boolean result = rd.reCycleAdd(ch_no, caseNum, dto);

        return result;
    }
}
