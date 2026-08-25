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

    public boolean reCycleAdd(int ch_no, int caseNum, RecycleDto dto, int m_no){
        boolean result = rd.reCycleAdd(ch_no, caseNum, dto, m_no);

        return result;
    }

    public ArrayList<RecycleDto> findMaxWearCount(){
        ArrayList<RecycleDto> result = rd.findMaxWearCount();

        return result;
    }

    public ArrayList<RecycleDto> findMinWearCount() {
        ArrayList<RecycleDto> result = rd.findMinWearCount();

        return result;
    }
}
