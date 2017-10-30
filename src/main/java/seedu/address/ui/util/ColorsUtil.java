package seedu.address.ui.util;

//@@author jo-lyn
/**
 * A utility class for colors used in UI.
 */
public class ColorsUtil {
    public static final String COLORS_RED = "#d06651";
    public static final String COLORS_YELLOW = "#f1c40f";
    public static final String COLORS_BLUE = "#3498db";
    public static final String COLORS_TEAL = "#1abc9c";
    public static final String COLORS_GREEN = "#2ecc71";
    public static final String COLORS_PURPLE = "#9b59b6";

    private ColorsUtil() {} // prevents instantiation

    public static String[] getColors() {
        return new String[] { COLORS_RED, COLORS_YELLOW, COLORS_BLUE, COLORS_TEAL, COLORS_GREEN, COLORS_PURPLE };
    }
}
