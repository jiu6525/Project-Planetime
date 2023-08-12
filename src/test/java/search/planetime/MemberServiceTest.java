package search.planetime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberServiceTest {

    @Test
    @Commit
    public void 회원가입() throws Exception{
        //given
//        Member member = new Member();
//        member.setMemberId("Kang");
//        member.setMemberPwd("Kang123");
//        member.setName("1");
//        member.setEmail("2");
//        member.setPhone("3");
//        member.setSex(Sex.MAN);
//        member.setSignDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));

//        //when
//        Long saveId = memberService.join(member);
//
////        then
//        assertEquals(member, memberRepository.findOne(saveId));
    }

//    @Test(expected = IllegalStateException.class)
//    public void 중복_회원_예외() throws Exception{
//        //given
//        Member member1 = new Member();
//        member1.setUsername("kim");
//
//        Member member2 = new Member();
//        member2.setUsername("kim");
//
//        //when
//        memberService.join(member1);
//        memberService.join(member2);
//
//        //then
//        fail("예외가 발생해야 한다.");
//    }


}
