package ashley.ashley_library.api;


import ashley.ashley_library.domain.Book;
import ashley.ashley_library.domain.NaverResultVo;
import ashley.ashley_library.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@Controller
public class NaverBookSearchApi {


    //9791189909307
    public static Book requestBook(String isbn, Model model) {
        String clientId = "fD8hokF01qU0BSC3Ei6Q";
        String clientSecret = "QPnTRFmsXh";


        //String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text;    // JSON 결과
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", isbn)
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();


        // Spring 요청 제공 클래스
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build();


        // Spring 제공 restTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        // JSON 파싱 (Json 문자열을 객체로 만듦, 문서화)
        ObjectMapper om = new ObjectMapper();
        NaverResultVo resultVO = null;


        Book book = null;
        try {
            resultVO = om.readValue(resp.getBody(), NaverResultVo.class);
            List<Book> books = resultVO.getItems();    // books를 list.html에 출력 -> model 선언

            book = new Book();

            for (Book b : books) {
                model.addAttribute("book", b);
                book = b;
            }

            model.addAttribute("books", books);

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        return "book/bookRegistration";
        return book;
    }



}

