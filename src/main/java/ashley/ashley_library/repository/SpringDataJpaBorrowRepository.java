package ashley.ashley_library.repository;

import ashley.ashley_library.domain.Borrow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaBorrowRepository extends JpaRepository<Borrow, Long>, BorrowRepository {

    @Override
    Borrow save(Borrow checkOut);

    @Override
    @Modifying
    @Query(value = "UPDATE BORROW b set b.status = :br_status, b.return_date = :return_date where b.br_id = :email", nativeQuery = true)
    void updateCheckOut(Borrow borrow);


//    @Override
//    Long findByIdOrderByDateTimeDesc(Long id);


    @Override
    Borrow findByBookCode(Long id);

    @Override
    Borrow findTopByBookCodeOrderByBrDateDesc(Long id);

    @Override
    Page<Borrow> findAll(Pageable pageable);
}
