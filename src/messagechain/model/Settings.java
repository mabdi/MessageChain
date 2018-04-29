package messagechain.model;

/**
 * Created by User on 4/29/2018.
 */
public class Settings {
    public static final String LOCAL_IDENTIFIER = "\u00A7" + "var@locals" + "\u00A7";
    public static final String LOCAL_PATTERN = "\u00A7" + "(\\w+)@locals" + "\u00A7";
    public static final String GLOBAL_IDENTIFIER = "\u00A7" + "var@globals" + "\u00A7";
    public static final String GLOBAL_PATTERN = "\u00A7" + "(\\w+)@globals" + "\u00A7";
    public static final String PARAM_IDENTIFIER = "\u00A7" + "var@params" + "\u00A7";
    public static final String PARAM_PATTERN = "\u00A7" + "(\\w+)@params" + "\u00A7";
    public static final String SECTION_CHAR = "\u00A7";


    // TODO make a list of popular sessionIds, show as a comboBox
    public static final String SESSION_COOKIENAME = "JSESSIONID";
}
