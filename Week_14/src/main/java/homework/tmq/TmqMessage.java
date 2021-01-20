package homework.tmq;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@AllArgsConstructor
@Data
public class TmqMessage<T> {

    private HashMap<String,Object> headers;

    private T body;

}
