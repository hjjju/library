package ashley.ashley_library.service;

import ashley.ashley_library.domain.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;







    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");
        

        //when

        Long join = memberService.join(member); //return 저장한 아이디가튀어나옴


        //then
//        Assertions.assertThat()


    }

    @Test
    void findMembers() {
        //given

        //when

        //then

    }

    @Test
    void findOne() {
        //given

        //when

        //then

    }
}