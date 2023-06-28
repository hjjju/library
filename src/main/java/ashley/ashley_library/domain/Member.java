package ashley.ashley_library.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //jpa가 관리하는 entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db가 아이디를 자동으로 생성해주는것
    private Long id; //아이디식별자,시스템이정하는 아이디

    private String name;

    private int phone;



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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }


}
