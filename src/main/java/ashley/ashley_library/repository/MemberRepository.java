package ashley.ashley_library.repository;

import ashley.ashley_library.domain.Borrow;
import ashley.ashley_library.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id); // 수행결과가 null인경우 처리

    Optional<Member> findByName(String name);

    List<Member> findAll();

    Page<Member> findAll(Pageable pageable);


    Optional<Member> findByPhone(String phone);

}
