package tech.ignitr.habitus.web.habit;

import org.springframework.http.HttpStatus;
import tech.ignitr.habitus.data.habit.Habit;

import java.util.List;

public class HabitListStatusReturn {

        List<Habit> response;
        HttpStatus status;

        public HabitListStatusReturn(List <Habit> response, HttpStatus status){
            this.response = response;
            this.status = status;
        }

        public List <Habit> getResponse() {
            return response;
        }
        public void setResponse(List <Habit> response) {
            this.response = response;
        }
        public HttpStatus getStatus() {
            return status;
        }
        public void setStatus(HttpStatus status) {
            this.status = status;
        }
}
