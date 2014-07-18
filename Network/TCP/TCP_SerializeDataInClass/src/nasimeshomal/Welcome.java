package nasimeshomal;

import java.io.Serializable;

/**
 * Created by Mahmood on 7/18/2014.
 */
public class Welcome implements Serializable{
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Welcome(String name)
    {
        this.message=String.format("Hello %s",name);
    }
}
