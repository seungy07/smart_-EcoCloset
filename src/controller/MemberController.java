package controller;

import model.dao.MemberDao;
import model.dto.MemberDto;

public class MemberController {
    // 싱글톤
   private MemberController(){}

   private static final MemberController instance =new MemberController();

   public static MemberController getInstance(){
        return instance;
   }

   // DAO 객체
   private MemberDao memberDao = MemberDao.getInstance();

   // 현재 로그인한 회원
   private MemberDto loginMember = null;

   // [1] 아이디 유효성 검사
   public boolean inCheck(String m_id){
    return memberDao.inCheck(m_id);
   }

   // [2] 회원가입
   public boolean signUp(String m_id, String m_pwd){
    // 아이디 중복검사
    boolean check = memberDao.inCheck(m_id);

    // 이미 아이디가 존재할 때
    if(check == false){ return false;}

    MemberDto memberDto = new MemberDto(m_id, m_pwd);
    return memberDao.signUp(memberDto);
   }

   // [3] 로그인
   
}