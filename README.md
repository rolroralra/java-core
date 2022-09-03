# How to Get the Size of an Object in Java
0. [Build and Run Execution](0-Build-and-Run-Execution)
1. [Creating Instrumentation Agent](#1-Creating-Instrumentation-Agent)
2. [Execution Example Class](#2-Execution-Example-Class)

---
## 0. Build and Run Execution
```bash
# InstrumentationAgent Class Compile
javac com/baeldung.objectsize/InstrumentationAgent.java

# Make Jar File
jar cmf MANIFEST.MF com/baeldung/objectsize/InstrumentationAgent.jar com/baeldung/objectsize/InstrumentationAgent.class

# Execution with JVM Option "-javaagent:{path}/InstrumentationAgent.jar"
java -javaagent:com/baeldung/objectsize/InstrumentationAgent.jar InstrumentationExample
```

## 1. Creating Instrumentation Agent
### InstrumentationAgent Class
```java
package com.baeldung.objectsize;

import java.lang.instrument.Instrumentation;

public class InstrumentationAgent {
    private static volatile Instrumentation globalInstrumentation;

    public static void premain(final String agentArgs, final Instrumentation instrumentation) {
        globalInstrumentation = instrumentation;
    }

    public static long getObjectSize(final Object object) {
        if (globalInstrumentation == null) {
            throw new IllegalStateException("Agent not initialized.");
        }

        return globalInstrumentation.getObjectSize(object);
    }
}
```

### MANIFEST.MF
```manifest
Premain-class: com.baeldung.objectsize.InstrumentationAgent
```

## 2. Execution Example Class
### Example Code
<details>
  <summary>Details</summary>
  <p>

```java
public class InstrumentationExample {

    public static void printObjectSize(Object object) {
        System.out.println("Object type: " + object.getClass() +
          ", size: " + InstrumentationAgent.getObjectSize(object) + " bytes");
    }

    public static void main(String[] arguments) {
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

        printObjectSize(emptyString);
        printObjectSize(string);
        printObjectSize(stringArray);
        printObjectSize(anotherStringArray);
        printObjectSize(stringList);
        printObjectSize(stringBuilder);
        printObjectSize(maxIntPrimitive);
        printObjectSize(minIntPrimitive);
        printObjectSize(maxInteger);
        printObjectSize(minInteger);
        printObjectSize(zeroLong);
        printObjectSize(zeroDouble);
        printObjectSize(falseBoolean);
        printObjectSize(Day.TUESDAY);
        printObjectSize(object);
        printObjectSize(emptyClass);
        printObjectSize(stringClass);
    }

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}
```

  </p>
</details>

### Result
```text
[emptyString              ] Object type: class java.lang.String                            , size: 24  bytes
[string                   ] Object type: class java.lang.String                            , size: 24  bytes
[string[3]                ] Object type: class [Ljava.lang.String;                         , size: 32  bytes
[string[100]              ] Object type: class [Ljava.lang.String;                         , size: 416 bytes
[List<String>             ] Object type: class java.util.ArrayList                         , size: 24  bytes
[StringBuilder(100)       ] Object type: class java.lang.StringBuilder                     , size: 24  bytes
[(int)Integer.MAX_VALUE   ] Object type: class java.lang.Integer                           , size: 16  bytes
[(int)Integer.MIN_VALUE   ] Object type: class java.lang.Integer                           , size: 16  bytes
[Integer.MAX_VALUE        ] Object type: class java.lang.Integer                           , size: 16  bytes
[Integer.MIN_VALUE        ] Object type: class java.lang.Integer                           , size: 16  bytes
[0L                       ] Object type: class java.lang.Long                              , size: 24  bytes
[0.0                      ] Object type: class java.lang.Double                            , size: 24  bytes
[false                    ] Object type: class java.lang.Boolean                           , size: 16  bytes
[Enum                     ] Object type: class InstrumentationExample$Day                  , size: 24  bytes
[Object                   ] Object type: class java.lang.Object                            , size: 16  bytes
[emptyClass Instance      ] Object type: class InstrumentationExample$1EmptyClass          , size: 16  bytes
[Class Instance           ] Object type: class InstrumentationExample$1StringClass         , size: 16  bytes
```

