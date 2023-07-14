package ashley.ashley_library.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity //jpa가 관리하는 entity
@Table(name = "borrow")

public class Borrow {

    @Id
    @Column(name = "br_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BORROW_SEQ_GENERATOR") //db가 아이디를 자동으로 생성해주는것
    @SequenceGenerator(name = "BORROW_SEQ_GENERATOR", sequenceName = "BORROW_SEQ", initialValue = 1, allocationSize = 1)
    private Long brId;
    @Column(name = "book_code")
    private Long bookCode;

    @Column(name = "book_title")
    private String bookTitle;

    @GeneratedValue
    @Column(name = "b_member_id")
    private Long bMemberId;


    @Column(name = "br_date")
    private String brDate;

    @Column(name = "br_exp_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime brExpDate;

    @Column(name = "br_status")
    private String brStatus;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "br_name")
    private String brName;

    @Column(name = "br_delay" )
    private int brDelay;

    public int getBrDelay() {
        return brDelay;
    }

    public void setBrDelay(int brDelay) {
        this.brDelay = brDelay;
    }

    public Long getBrId() {
        return brId;
    }

    public void setBrId(Long brId) {
        this.brId = brId;
    }

    public Long getBookCode() {
        return bookCode;
    }

    public void setBookCode(Long bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Long getbMemberId() {
        return bMemberId;
    }

    public void setbMemberId(Long bMemberId) {
        this.bMemberId = bMemberId;
    }

    public String getBrDate() {
        return brDate;
    }

    public void setBrDate(String brDate) {
        this.brDate = brDate;
    }

    public LocalDateTime getBrExpDate() {
        return brExpDate;
    }

    public void setBrExpDate(LocalDateTime brExpDate) {
        this.brExpDate = brExpDate;
    }

    public String getBrStatus() {
        return brStatus;
    }

    public void setBrStatus(String brStatus) {
        this.brStatus = brStatus;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getBrName() {
        return brName;
    }

    public void setBrName(String brName) {
        this.brName = brName;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "brId=" + brId +
                ", bookCode=" + bookCode +
                ", bookTitle='" + bookTitle + '\'' +
                ", bMemberId=" + bMemberId +
                ", brDate=" + brDate +
                ", brExpDate=" + brExpDate +
                ", brStatus='" + brStatus + '\'' +
                ", returnDate=" + returnDate +
                ", brName='" + brName + '\'' +
                '}';
    }
}
