package ashley.ashley_library.service;

import ashley.ashley_library.api.NaverBookSearchApi;
import ashley.ashley_library.domain.Book;
import ashley.ashley_library.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public static Book findByIsbn(String isbn, Model model) {
        return NaverBookSearchApi.requestBook(isbn, model);
    }

    public Long save(Book book){
        validateDuplicateBook(book);
        bookRepository.save(book);
        return book.getId();
    }

    private void validateDuplicateBook(Book book) {
        bookRepository.findByTitle(book.getTitle()).ifPresent(m -> {
            throw new IllegalStateException("이미 등록된 책입니다.");
        });
    }

    public Page<Book> findAll(Pageable pageable){
        return  bookRepository.findAll(pageable);

    }

    public Book bookDetail(Long id) {

        Book book = bookRepository.findById(id).get();
        return book;
    }

    public Page<Book> bookList(Pageable pageable, int pageNum) {

        pageable = PageRequest.of(pageNum, 10, Sort.by(Sort.Direction.ASC, "title"));

        Page<Book> result = bookRepository.findAll(pageable);
        return result;
     }
}
