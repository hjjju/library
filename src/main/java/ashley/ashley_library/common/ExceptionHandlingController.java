package ashley.ashley_library.common;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class ExceptionHandlingController implements ErrorController {

    private final String ERROR_404_PAGE_PATH = "/error/404";
    private final String ERROR_500_PAGE_PATH = "/error/500";
    private final String ERROR_ETC_PAGE_PATH = "/error/error";
    private final String ERROR_DUPLICATE_NUM_PAGE_PATH = "/error/duplicateNum";
    private final String ERROR_DUPLICATE_BOOK_PAGE_PATH = "/error/duplicateBook";

    @RequestMapping(value = "/error")
    public String handleError(HttpServletRequest request, Model model) {

        // 에러 코드를 획득한다.
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // 에러 코드에 대한 상태 정보
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));

        if (status != null) {
            // HttpStatus와 비교해 페이지 분기를 나누기 위한 변수
            int statusCode = Integer.valueOf(status.toString());

            // 로그로 상태값을 기록 및 출력
//            logger.info("httpStatus : " + statusCode);

            // 404 error
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // 에러 페이지에 표시할 정보
                model.addAttribute("code", status.toString());
                model.addAttribute("msg", httpStatus.getReasonPhrase());
                model.addAttribute("timestamp", new Date());
                return ERROR_DUPLICATE_NUM_PAGE_PATH;
            }

            // 500 error
            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                // 서버에 대한 에러이기 때문에 사용자에게 정보를 제공하지 않는다.
                return ERROR_500_PAGE_PATH;
            }
        }

        // 정의한 에러 외 모든 에러는 error/error 페이지로 보낸다.
        return ERROR_ETC_PAGE_PATH;
    }

    public String getErrorPath() {
        return "/error";
    }

}

