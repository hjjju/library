package ashley.ashley_library.service;

import ashley.ashley_library.domain.Borrow;
import ashley.ashley_library.domain.Member;
import ashley.ashley_library.repository.BorrowRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class BorrowService {

    private final BorrowRepository borrowRepository;


    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public Long save(Borrow borrow) {
        borrowRepository.save(borrow);
        return borrow.getBrId();
    }

    public void updateCheckOut(Borrow borrow) {
        borrowRepository.updateCheckOut(borrow);
    }


    public Borrow findByBookCode(Long id) {
        return borrowRepository.findByBookCode(id);
    }

    public Borrow findTopByBookCodeOrderByBrDateDesc(Long id) {
        return borrowRepository.findTopByBookCodeOrderByBrDateDesc(id);
    }

    public Page<Borrow> borrowList(Pageable pageable, int pageNo) {
        //given
        pageable = PageRequest.of(pageNo, 10, Sort.by(Sort.Direction.DESC, "brId"));

        //when
        Page<Borrow> result = borrowRepository.findAll(pageable);

        //then
        return result;
    }
}
