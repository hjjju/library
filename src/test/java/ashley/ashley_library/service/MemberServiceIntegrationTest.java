package ashley.ashley_library.service;

import ashley.ashley_library.domain.Member;
import ashley.ashley_library.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional  //테스트끝나면 rollback해줌
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveID = memberService.join(member); //return 저장한 아이디가튀어나옴


        //then
        Member findMember = memberService.findOne(saveID).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());


    }

    @Test
    public void 중복회원예외() {
        //given
        Member member1 = new Member();
        member1.setName("sp1");


        Member member2 = new Member();
        member2.setName("sp1");

        //when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

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

    @Test
    public void paging() {

//        given
//        Member m1 = new Member();
//        m1.setName("member1");
//        Member m2 = new Member();
//        m2.setName("member2");
//        Member n3 = new Member();
//        n3.setName("member3");
//        Member m4 = new Member();
//        m4.setName("member4");
//        Member m5 = new Member();
//        m5.setName("member5");

        for (int i = 1; i < 20; i++) {
            Member m = new Member();
            m.setName("test" + i);
            m.setPhone("01022323"+i);
            memberRepository.save(m);
        }

        Pageable pageable = PageRequest.of(0, 10);
        //when

        Page<Member> result = memberRepository.findAll(pageable);


        //then


        System.out.println("result: " + result);

        System.out.println("total Pages : " + result.getTotalPages()); //총페이지수
        System.out.println("total Counts: " + result.getTotalElements()); //토탈 데이터수
        System.out.println("Page Number: " + result.getNumber()); //현재 페이지 번호 ( 0 부터시작)
        System.out.println("result.getSize() = " + result.getSize()); // 페이지당 데이터개수
        System.out.println("result.hasNext() = " + result.hasNext()); //다음페이지 존재여부
        System.out.println("result.isFirst() = " + result.isFirst()); //시작페이지(0)여부


    }


}