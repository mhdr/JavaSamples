package nasimeshomal;

import javax.crypto.EncryptedPrivateKeyInfo;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by ma.ramezani on 8/24/2014.
 */
public class EncodingList extends ArrayList<Encoding>{
    public EncodingList()
    {
        this.add(new Encoding(1,"UTF-8","UTF-8"));
        this.add(new Encoding(2,"Windows-1256","Arabic (Windows-1256)"));

        this.sort(new Comparator<Encoding>() {
            @Override
            public int compare(Encoding o1, Encoding o2) {
                return o1.getDisplayValue().compareTo(o2.getDisplayValue());
            }
        });
    }

    public static Encoding FindByDiplayValue(String value)
    {
        EncodingList encodingList=new EncodingList();
        for (Encoding encoding:encodingList)
        {
            if (encoding.getDisplayValue()==value)
            {
                return encoding;
            }
        }

        return null;
    }

    public static Encoding FindByValue(String value)
    {
        EncodingList encodingList=new EncodingList();
        for (Encoding encoding:encodingList)
        {
            if (encoding.getValue()==value)
            {
                return encoding;
            }
        }

        return null;
    }

}
