package ashley.ashley_library.service;

import ashley.ashley_library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ashley.ashley_library.repository.MemberRepository;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    private final BookRepository bookRepository;


    @Autowired
    public SpringConfig(MemberRepository memberRepository, BookRepository bookRepository) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public BookService bookService() {
        return new BookService(bookRepository);
    }


}
