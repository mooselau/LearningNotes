package demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class JustResponse<T> {
    private Error error;
    private T result;

    /**
     * Construct one common response.
     * @param result
     */
    public JustResponse(T result) {
        this.result = result;
    }

    /**
     * Construct one error response.
     * @param errCode
     * @param errMsg
     */
    public JustResponse(String errCode, String errMsg) {
        this.error = new Error(errCode, errMsg);
    }

    @Data
    @AllArgsConstructor
    private class Error {
        private String code;
        private String message;
    }
}
