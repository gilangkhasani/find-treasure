package sg.fwd.techTest.be.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import sg.fwd.techTest.be.web.jsonview.Views;

public class AjaxResponseBody {

    @JsonView(Views.Public.class)
    String msg;
    @JsonView(Views.Public.class)
    String code;
    @JsonView(Views.Public.class)
    Point result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Point getResult() {
        return result;
    }

    public void setResult(Point result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AjaxResponseResult [msg=" + msg + ", code=" + code + ", result=" + result + "]";
    }

}
