package ashley.ashley_library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @GetMapping("/book/checkOut")
    public String bookLent(){
        return "/book/checkOut";
    }
    @GetMapping("/book/return")
    public String bookReturn(){
        return "/book/return";
    }

    @GetMapping("/book/bookRegistration")
    public String bookRegistration(){
        return "/book/bookRegistration";
    }
    @GetMapping("/book/bookList")
    public String bookList(){
        return "/book/bookList";
    }



}

