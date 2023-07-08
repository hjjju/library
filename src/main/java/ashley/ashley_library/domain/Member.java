package ashley.ashley_library.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity //jpa가 관리하는 entity
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEM_SEQ_GENERATOR") //db가 아이디를 자동으로 생성해주는것
    @SequenceGenerator(name = "MEM_SEQ_GENERATOR", sequenceName = "MEM_SEQ", initialValue = 1, allocationSize = 1)
    private Long id; //아이디식별자,시스템이정하는 아이디

    private String name;

    private String phone;


    @ManyToMany
    @JoinTable(name = "MEMBER_BORROW",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "B_MEMBER_ID")
    )
    private List<Borrow> borrowList = new ArrayList<Borrow>();

    public List<Borrow> getBorrowList() {
        return borrowList;
    }

    public void setBorrowList(List<Borrow> borrowList) {
        this.borrowList = borrowList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
