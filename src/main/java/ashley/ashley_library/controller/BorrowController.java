package ashley.ashley_library.controller;

import ashley.ashley_library.domain.Book;
import ashley.ashley_library.domain.Borrow;
import ashley.ashley_library.domain.Member;
import ashley.ashley_library.service.BookService;
import ashley.ashley_library.service.BorrowService;
import ashley.ashley_library.service.MemberService;
import org.apache.catalina.util.ToStringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Controller
public class BorrowController {

    private BorrowService borrowService;
    private BookService bookService;
    private MemberService memberService;

    public BorrowController(BorrowService borrowService, BookService bookService, MemberService memberService) {
        this.borrowService = borrowService;
        this.bookService = bookService;
        this.memberService = memberService;
    }


    //책대여
    @PostMapping("/borrow/checkOut")
    public String bookLent(Book book, Member member) {
        System.out.println("getIsbn " + book.getIsbn());
        System.out.println("번호: " + member.getPhone());

        //책정보가져오기
        Book bInfo = bookService.findByIsbn(book.getIsbn());

        System.out.println(bInfo.toString());

        //회원정보 가져오기

        Member mInfo = memberService.findByPhone(member.getPhone());

        System.out.println("mInfo: " + mInfo.toString());

        Borrow borrow = new Borrow();

        borrow.setBrName(mInfo.getName());
        borrow.setbMemberId(mInfo.getId());
        borrow.setBookCode(bInfo.getId());
        borrow.setBookTitle(bInfo.getTitle());


        LocalDate now = LocalDate.now();

        System.out.println("now " + now);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedNow = now.format(formatter);

        System.out.println(formattedNow);

        borrow.setBrDate(formattedNow);
        borrow.setBrExpDate(String.valueOf(now.plusDays(7)));


        System.out.println(borrow.toString());
        borrow.setBrStatus("C".trim());


        //책대여정보 save

        borrowService.save(borrow);


        return "/book/checkOut";
    }

    //책반납처리
    @PostMapping("borrow/returnBook")
    public String returnBook(Book book) {

        Book book1 = bookService.findByIsbn(book.getIsbn());
        System.out.println("book1 " + book1.toString());

        Borrow borrow = new Borrow();


        borrow = borrowService.findTopByBookCodeOrderByBrDateDesc(book1.getId());

        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedNow = now.format(formatter);

        borrow.setReturnDate(formattedNow);
        borrow.setBrStatus("R"); //return

        borrowService.save(borrow);

        return "redirect:/";//메인으로 돌려보냄
    }

    @GetMapping("/members/monthlyCheckOut")
    public String monthlyCheckOut(Model model, @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo, @PageableDefault(page = 0, size = 10, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {

        //클라이언트 페이지에서 받은 pageNo과 실제접근 페이지는 다름, page객체는 페이지가 0부터 시작
        pageNo = (pageNo == 0) ? 0 : (pageNo - 1);


        Page<Borrow> borrowList = borrowService.borrowList(pageable, pageNo);


        //클라이언트 페이지에서 받은 pageNo과 실제접근 페이지는 다름, page객체는 페이지가 0부터 시작

        int nowPage = borrowList.getPageable().getPageNumber() + 1; // pageable이 가지고 있는 페이지는 0부터 시작하기때문에 1을 더함
        int startPage = Math.max(nowPage - 4, 1); // 1보다 작은 경우는 1을 반환
//        int endPage = Math.min(nowPage + 9, bookList.getTotalPages()); // 전체 페이지보다 많은 경우는 전체 페이지를 반환
        int endPage = Math.min(borrowList.getTotalPages(), borrowList.getPageable().getPageNumber() + 4);

        model.addAttribute("borrowList", borrowList);
        /* model.addAttribute("books", bookService.findAll(pageable));*/
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("pagNum", pageNo);
        model.addAttribute("totalPage", borrowList.getTotalPages());
        model.addAttribute("hasPrev", borrowList.hasPrevious());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("hasNext", borrowList.hasNext());


        return "/members/monthlyCheckOut";
    }

}
