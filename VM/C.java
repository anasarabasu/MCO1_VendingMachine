package VM;


/**
 * Utility class containing constants used throughout the vending machine
 * application, including formatting values, ANSI color codes, and supported
 * bill and coin denominations.
 */
public final class C {


    /**
     * Default width used when printing horizontal separator lines.
     */
    public static final int LINE = 55;



    /**
     * ANSI escape code that resets the console text formatting.
     */
    public static final String DEF = "\u001B[0m";
    
    /**
     * ANSI escape code for red console text.
     */
    public static final String RED = "\u001B[31m";

    /**
     * ANSI escape code for yellow console text.
     */
    public static final String YEL = "\u001B[33m";


    
    /**
     * Philippine bill and coin denominations supported by the vending machine.
     * <p>
     * The array is ordered from highest to lowest denomination. Index 0 is
     * unused as a placeholder.
     * The duplicate {@code 20} represents the transition between bills and
     * coins.
     */
    public static final double BILLS[] = new double[]{
        0,
        1000, 500, 200, 100, 50, 20,
        20, 10, 5, 1,
        0.25, 0.10, 0.05, 0.01
    };


}