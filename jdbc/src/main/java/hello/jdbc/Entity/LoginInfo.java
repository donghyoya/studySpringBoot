package hello.jdbc.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
public class LoginInfo {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "pwd",unique = true)
    private String pwd;


}
