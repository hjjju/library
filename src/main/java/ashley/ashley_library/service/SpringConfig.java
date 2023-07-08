package ashley.ashley_library.service;


import ashley.ashley_library.repository.BookRepository;
import ashley.ashley_library.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ashley.ashley_library.repository.MemberRepository;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    private final BookRepository bookRepository;

    private final BorrowRepository borrowRepository;




    @Autowired
    public SpringConfig(MemberRepository memberRepository, BookRepository bookRepository, BorrowRepository checkOutRepository, BorrowRepository borrowRepository) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public BookService bookService() {
        return new BookService(bookRepository);
    }

    @Bean
    public BorrowService borrowService() {
        return new BorrowService(borrowRepository);
    }
}
