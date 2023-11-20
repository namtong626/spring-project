package springbootproject.springboot.helpers;

public class Common {
    private Common() {
    }

    public static void dump(Object variable) {
        System.err.println("Variable value: " + variable);
        throw new RuntimeException("Execution stopped.");
    }
}
