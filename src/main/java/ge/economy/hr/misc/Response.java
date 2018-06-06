
package ge.economy.hr.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ucha
 */
public class Response {

    private boolean valid = true;
    private String message;
    private Map<String, Object> data;

    public Response(boolean valid, String message, Map<String, Object> data) {
        this.valid = valid;
        this.data = data;
        this.message = message;
    }

    public static Response success() {
        return new Response();
    }

    public static Response error(String message) {
        Response r = new Response();
        r.setValid(false);
        r.setMessage(message);
        return r;
    }

    public Response() {
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Builder {

        private boolean valid = true;
        private String message;
        private final Map<String, Object> data = new HashMap<>();

        public Builder setValid(boolean valid) {
            this.valid = valid;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder putData(String key, Object value) {
            data.put(key, value);
            return this;
        }

        public Response build() {
            return new Response(valid, message, data);
        }

        public static Response simpleBuild(String dataKey, Object data) {
            return new Builder().putData(dataKey, data).build();
        }
    }
}