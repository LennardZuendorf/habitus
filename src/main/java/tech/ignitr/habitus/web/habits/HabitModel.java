package tech.ignitr.habitus.web.habits;

import lombok.Getter;
import tech.ignitr.habitus.data.habits.Frequency;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class HabitModel {

    private UUID id;
    private UUID userId;
    private String tag;
    private Integer maxQuantity;
    private Frequency frequency;
    private Integer currentQuantity;
    private LocalDateTime check;
    private boolean done;

}
