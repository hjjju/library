package ashley.ashley_library.domain;

import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class NaverResultVo {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Book> items;
}