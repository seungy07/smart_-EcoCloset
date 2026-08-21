package controller;

import model.dao.ClosetDao;

public class ClosetController {
    private ClosetController(){}
    private static final ClosetController instance = new ClosetController();
    public static ClosetController getInstance(){return instance;}
    private ClosetDao cl_d = ClosetDao.getInstance();

}
