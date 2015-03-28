package nasimeshomal;

/**
 * Created by ma.ramezani on 8/24/2014.
 */
public class Encoding {
    private int id;
    private String value;
    private String displayValue;

    public Encoding()
    {

    }

    public Encoding(int id,String value,String displayValue)
    {
        this.setId(id);
        this.setValue(value);
        this.setDisplayValue(displayValue);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

}
