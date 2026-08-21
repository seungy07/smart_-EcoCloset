package model.dao;

public class ClosetDao {
    private ClosetDao(){}
    private static final ClosetDao instance = new ClosetDao();
    public static ClosetDao getInstance(){return instance;}

}
