package ashley.ashley_library.repository;


import ashley.ashley_library.domain.Borrow;
import ashley.ashley_library.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {


    //findByName치면 자동으로뜸
    @Override
    Optional<Member> findByName(String name);

    @Override
    Member save(Member member);

    @Override
    List<Member> findAll();

    @Override
    Optional<Member> findById(Long aLong);

//    @Override
//    Page<Member> findAll(Pageable pageable);

}
