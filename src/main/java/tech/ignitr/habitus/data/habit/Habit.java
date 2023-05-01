package tech.ignitr.habitus.data.habit;

import jakarta.persistence.*;
import tech.ignitr.habitus.data.user.User;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="habits")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false)
    private UUID id;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String tag;

    @Column(nullable = false, name="max_quantity")
    private Integer maxQuantity;

    @Column(nullable = false)
    @Enumerated(value=EnumType.STRING)
    private Frequency frequency;

    @Column(nullable = false, name="current_quantity")
    private Integer currentQuantity;

    @Column(name="date_done")
    @Temporal(TemporalType.DATE)
    private LocalDate date_done;

    @Column(nullable = false)
    private boolean done;

    public Habit( User user, String tag, Integer maxQuantity) {
        this.user = user;
        this.tag = tag;
        this.maxQuantity = maxQuantity;
        this.done=false;
        this.currentQuantity=0;
    }

    protected Habit(){};

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public UUID getId() {
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDate getDate_done() {
        return date_done;
    }

    public void setDate_done(LocalDate date_done) {
        this.date_done = date_done;
    }
}
