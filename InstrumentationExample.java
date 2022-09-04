import com.baeldung.objectsize.InstrumentationAgent;
import java.util.ArrayList;
import java.util.List;

public class InstrumentationExample {

    public static void printObjectSize(String title, Object object) {
        System.out.printf("[%-25s] Object type: %-50s, size: %-3d bytes\n",
            title,
            object.getClass(),
            InstrumentationAgent.getObjectSize(object)
        );
    }

    public static void main(String[] arguments) {
        char primitiveCharacter = 'b';
        Character character = 'a';
        String emptyString = "";
        String string = "Estimating Object Size Using Instrumentation";
        String[] stringArray = { emptyString, string, "com.baeldung" };
        String[] anotherStringArray = new String[100];
        List<String> stringList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder(100);
        int maxIntPrimitive = Integer.MAX_VALUE;
        int minIntPrimitive = Integer.MIN_VALUE;
        Integer maxInteger = Integer.MAX_VALUE;
        Integer minInteger = Integer.MIN_VALUE;
        long zeroLong = 0L;
        double zeroDouble = 0.0;
        boolean falseBoolean = false;
        Object object = new Object();

        class EmptyClass {
        }
        EmptyClass emptyClass = new EmptyClass();

        class StringClass {
            public String s;
        }
        StringClass stringClass = new StringClass();

        printObjectSize("primitive char", primitiveCharacter);
        printObjectSize("Character", character);
        printObjectSize("emptyString", emptyString);
        printObjectSize("string", string);
        printObjectSize("string[3]", stringArray);
        printObjectSize("string[100]", anotherStringArray);
        printObjectSize("List<String>", stringList);
        printObjectSize("StringBuilder(100)", stringBuilder);
        printObjectSize("(int)Integer.MAX_VALUE", maxIntPrimitive);
        printObjectSize("(int)Integer.MIN_VALUE", minIntPrimitive);
        printObjectSize("Integer.MAX_VALUE", maxInteger);
        printObjectSize("Integer.MIN_VALUE", minInteger);
        printObjectSize("0L", zeroLong);
        printObjectSize("0.0", zeroDouble);
        printObjectSize("false", falseBoolean);
        printObjectSize("Enum", Day.TUESDAY);
        printObjectSize("Object", object);
        printObjectSize("emptyClass Instance", emptyClass);
        printObjectSize("Class Instance", stringClass);
    }

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}
