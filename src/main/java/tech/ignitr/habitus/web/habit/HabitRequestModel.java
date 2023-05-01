package tech.ignitr.habitus.web.habit;

import tech.ignitr.habitus.data.habit.Frequency;

import java.time.LocalDateTime;

public class HabitRequestModel {

    private Long id;
    private Long userId;
    private String tag;
    private Integer maxQuantity;
    private Frequency frequency;
    private Integer currentQuantity;
    private LocalDateTime check;
    private boolean done;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTag() {
        return tag;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public LocalDateTime getCheck() {
        return check;
    }

    public boolean isDone() {
        return done;
    }
}
