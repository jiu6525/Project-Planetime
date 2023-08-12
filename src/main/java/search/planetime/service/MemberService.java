package search.planetime.service;

import search.planetime.domain.Member;
import search.planetime.domain.findId;
import search.planetime.memberDTO.MemberDTO;

import java.util.List;

public interface MemberService {
    void join(MemberDTO memberDTO);
    String findId(String name, String email);
}