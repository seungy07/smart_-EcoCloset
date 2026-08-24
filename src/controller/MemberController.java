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
   public boolean login(String m_id, String m_pwd){
     MemberDto memberDto = memberDao.login(m_id, m_pwd);
     if(memberDto == null){
          return false;
     }
     loginMember = memberDto;
     return true;
   }

   // [4] 로그아웃
   public void logout(){
     loginMember = null;
   }

   // [5] 현재 로그인 회원 반환
   public MemberDto getLoginMember(){
     return loginMember;
   }

   // [6] 회원탈퇴
   public boolean memberDelete(){
     if(loginMember == null){
          return false;
     }
     int m_no = loginMember.getM_no();
     boolean result = memberDao.memberDelete(m_no);

     if(result == true){
          loginMember = null;
     }
     return result;
   }

   // [7] Eco Level 계산
   public String ecoLevelCheck(int ecoPoint){
     if(ecoPoint < 100){
          return "LV.1 씨앗";
        }
          else if(ecoPoint < 250){
            return "Lv.2 새싹";
        }
        else if(ecoPoint < 500){
            return "Lv.3 나무";
        }
        else if(ecoPoint < 800){
            return "Lv.4 큰 나무";
        }
        else{
            return "Lv.5 숲";
   }
}

   // 8. Eco Point 조회
   public int ecoPoint(){
     if(loginMember == null){
          return 0;
     }
     int m_no = loginMember.getM_no();
     return memberDao.ecoPoint(m_no);
   }
}
