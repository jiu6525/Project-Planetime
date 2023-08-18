package search.planetime.service;

import search.planetime.DTO.MailDTO;
import search.planetime.DTO.MemberDTO;

public interface MemberService {
    void join(MemberDTO memberDTO);
    String findId(String name, String email);
    int findPwd(MailDTO mailDTO);
}