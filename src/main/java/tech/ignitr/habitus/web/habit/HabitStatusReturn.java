package tech.ignitr.habitus.web.habit;

import org.springframework.http.HttpStatus;
import tech.ignitr.habitus.data.habit.Habit;

public class HabitStatusReturn {

    Habit response;
    HttpStatus status;

    public HabitStatusReturn(Habit response, HttpStatus status){
        this.response = response;
        this.status = status;
    }

    public Habit getResponse() {
        return response;
    }
    public void setResponse(Habit response) {
        this.response = response;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
