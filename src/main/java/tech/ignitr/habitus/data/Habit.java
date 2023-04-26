package tech.ignitr.habitus.data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String tag;

    @Column(nullable = false, name="max_quantity")
    private Integer maxQuantity;

    @Column(nullable = false, name="current_quantity")
    private Integer currentQuantity;

    @Column(nullable = true)
    private LocalDateTime check;

    @Column(nullable = false)
    private boolean done;

    public Habit(Long id, User user, String tag, Integer maxQuantity) {
        this.id = id;
        this.user = user;
        this.tag = tag;
        this.maxQuantity = maxQuantity;
        this.done=false;
        this.currentQuantity=0;
    }

    public Habit() {

    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public LocalDateTime getCheck() {
        return check;
    }

    public void setCheck(LocalDateTime check) {
        this.check = check;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
