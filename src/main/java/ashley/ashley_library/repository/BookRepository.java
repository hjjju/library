package ashley.ashley_library.repository;

import ashley.ashley_library.domain.Book;
import ashley.ashley_library.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> findByTitle(String title);

    Page<Book> findAll(Pageable pageable);

    Optional<Book> findById(Long id);


}
