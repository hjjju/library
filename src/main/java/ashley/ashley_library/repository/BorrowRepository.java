package ashley.ashley_library.repository;

import ashley.ashley_library.domain.Borrow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BorrowRepository {

    Borrow save(Borrow checkOut);

    void updateCheckOut(Borrow borrow);

    Borrow findByBookCode(Long id);

    Borrow findTopByBookCodeOrderByBrDateDesc(Long id);

    Page<Borrow> findAll(Pageable pageable);


//    Long findByIdOrderByDateTimeDesc(Long id);
}
