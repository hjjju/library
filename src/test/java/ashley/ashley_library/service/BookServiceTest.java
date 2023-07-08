package ashley.ashley_library.service;

import ashley.ashley_library.domain.Book;
import ashley.ashley_library.domain.Member;
import ashley.ashley_library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//@Transactional
class BookServiceTest {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;


    @Test
    public void  save (){
        for (int i = 0; i <40; i++) {
            Book b = new Book();
            b.setTitle("스프링" +i);
            b.setIsbn("978892555"+i);
            bookRepository.save(b);
        }



    }

}