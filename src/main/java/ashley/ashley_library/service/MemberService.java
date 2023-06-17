package ashley.ashley_library.service;


import ashley.ashley_library.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.SpringTransactionAnnotationParser;
import org.springframework.transaction.annotation.Transactional;
import ashley.ashley_library.repository.MemberRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

//test단축키 ctrl+ shfift + t
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원가입
     * */
    public Long join(Member member) {

        //같은이름 + 생년월일이 있는지 중복회원
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체회원조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(long memberId){
        return memberRepository.findById(memberId);
    }



    public Page<Member> memberList(Pageable pageable,int pageNum) {
        //given
        pageable = PageRequest.of(pageNum, 10,Sort.by(Sort.Direction.ASC,"name"));
        //when

        Page<Member> result = memberRepository.findAll(pageable);

        //then
        return result;

    }
//    public Page<Member> memberList(Pageable pageable) {
//
//        int page = pageable.getPageNumber(); //page 위치에 있는 값은 0부터 시작한다.
//        System.out.println("page " +page);
//        int pageLimit = 10; //한페이지에 보여줄 글 개수
//        Page<Member> result = memberRepository.findAll(PageRequest.of(page,pageLimit,Sort.by(Sort.Direction.ASC,"name")));
//
//        //목록
//
//        //then
//        return result;
//
//    }

}
