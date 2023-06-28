package ashley.ashley_library.controller;

import ashley.ashley_library.domain.Book;
import ashley.ashley_library.domain.NaverResultVo;
import ashley.ashley_library.service.BookService;
import ashley.ashley_library.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groovyjarjarantlr4.v4.misc.FrequencySet;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.datatransfer.Clipboard;
import java.net.URI;
import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    //생성자로 연결해줌
//    @Autowired//생성자에 autowired 스프링컨테이너에 있는 memberServcie랑 연결해줌


    @GetMapping("/book/checkOut")
    public String bookLent() {
        return "/book/checkOut";
    }

    @GetMapping("/book/return")
    public String bookReturn() {
        return "/book/return";
    }

    @GetMapping("/book/bookRegistration")
    public String bookRegistration() {
        return "/book/bookRegistration";
    }

    
    //책 저장용
    @PostMapping("/book/bookRegistration")
    public String save(Book book){

        System.out.println("bookSave " + book.toString());
        bookService.save(book);



        return "redirect:/";
    }

    @GetMapping("/book/bookList")
    public String bookList(Model model,  @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo, @PageableDefault(sort = "title",direction = Sort.Direction.ASC) Pageable pageable) {

//        List<Book> books = bookService.findAll();

        pageNo = (pageNo == 0) ? 0 : (pageNo - 1);

        Page<Book> bookList = bookService.bookList(pageable, pageNo);


        //클라이언트 페이지에서 받은 pageNo과 실제접근 페이지는 다름, page객체는 페이지가 0부터 시작

        int nowPage = bookList.getPageable().getPageNumber() + 1; // pageable이 가지고 있는 페이지는 0부터 시작하기때문에 1을 더함
        int startPage = Math.max(nowPage - 4, 1); // 1보다 작은 경우는 1을 반환
        int endPage = Math.min(nowPage + 9, bookList.getTotalPages()); // 전체 페이지보다 많은 경우는 전체 페이지를 반환

        model.addAttribute("bookList", bookList);
       /* model.addAttribute("books", bookService.findAll(pageable));*/
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("pagNum", pageNo);
        model.addAttribute("totalPage",bookList.getTotalPages());
        model.addAttribute("hasPrev", bookList.hasPrevious());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("hasNext", bookList.hasNext());
        return "/book/bookList";
    }


    @GetMapping("/book/isbnSearch")
    @ResponseBody
    public Book searchBook(Model model, @RequestParam String isbn) {

        Book book = new Book();
        book = bookService.findByIsbn(isbn, model);


        return book;

    }

    @GetMapping("/book/bookDetail")
    public String bookDetail(Model model,Long id){
        model.addAttribute("bookDetail", bookService.bookDetail(id));

        return "book/bookDetail";
    }


}



