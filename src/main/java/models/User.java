package models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(unique=true)
    private String login;
    private String password;
    private String role;
    public User(){

    }
    public User(long id, String name,String login, String password,String role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        }

    public User(String name,String login, String password) {
        this.setId(-1);
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = "user";
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLogin() {
            return login;
        }
    public String getPassword() {
            return password;
        }
    public String getName() {
            return name;
        }
    public long getId() {
            return id;
        }
    public String getRole() {
        return role;
    }


}
