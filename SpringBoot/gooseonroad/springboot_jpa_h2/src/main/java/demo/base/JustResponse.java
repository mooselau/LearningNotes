package demo.base;

import lombok.Data;

@Data
public class JustResponse<T> {
    private T result;

    public JustResponse(T result) {
        this.result = result;
    }
}
