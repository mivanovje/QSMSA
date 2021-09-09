package mgivanov.qsmsa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "greeting")
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String content;


    public Greeting() {
    }


    public Greeting(String content) {
        this.content = content;
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Greeting greeting = (Greeting) o;
        return id == greeting.id && Objects.equals(content, greeting.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }
}
