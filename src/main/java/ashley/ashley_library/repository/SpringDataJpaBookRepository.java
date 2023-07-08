package ashley.ashley_library.repository;


import ashley.ashley_library.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaBookRepository extends JpaRepository<Book, Long>, BookRepository {


    @Override
    Optional<Book> findByTitle(String title);

    @Override
    Book save(Book book);

    @Override
    Page<Book> findAll(Pageable pageable);

    @Override
    Optional<Book> findById(Long aLong);

    @Override
    Book findByIsbn(String isbn);




}
