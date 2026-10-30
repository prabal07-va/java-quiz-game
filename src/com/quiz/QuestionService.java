package com.quiz;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service providing question catalog, filtering, and shuffling.
 */
public class QuestionService {
    private final List<Question> questionBank = new ArrayList<>();

    public QuestionService() {
        loadQuestions();
    }

    private void loadQuestions() {
        int id = 1;

        // ==========================================
        // 1. JAVA BASICS (15 Questions)
        // ==========================================
        questionBank.add(new Question(
            id++,
            "Which of the following is NOT a Java primitive data type?",
            Arrays.asList("int", "boolean", "String", "double"),
            2,
            "String is an object (reference type) in Java, while int, boolean, and double are primitive types.",
            "Java Basics", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "What is the default value of an uninitialized boolean instance variable in Java?",
            Arrays.asList("true", "false", "null", "0"),
            1,
            "Instance and static boolean variables in Java default to false if not explicitly initialized.",
            "Java Basics", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "Which keyword is used to prevent method overriding in Java?",
            Arrays.asList("static", "const", "final", "abstract"),
            2,
            "The 'final' keyword on a method prevents subclasses from overriding it.",
            "Java Basics", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "What will `System.out.println(10 + 20 + \"Hello\");` print?",
            Arrays.asList("1020Hello", "30Hello", "Hello1020", "Compile Error"),
            1,
            "Java evaluates left-to-right. 10 + 20 gives 30, and 30 + \"Hello\" results in \"30Hello\".",
            "Java Basics", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "Which memory area in JVM stores Java Objects?",
            Arrays.asList("Stack Memory", "Heap Memory", "Method Area", "PC Register"),
            1,
            "All class instances and arrays are allocated in the Heap memory in JVM.",
            "Java Basics", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "Which of the following is true about Java String immutability?",
            Arrays.asList(
                "Strings can be modified using the .append() method",
                "Once created, the internal character sequence of a String object cannot change",
                "Strings are mutable if marked as public",
                "StringBuilder and String are identical in memory"
            ),
            1,
            "Java String objects are immutable; any modification creates a new String instance.",
            "Java Basics", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "What is the size of an 'int' data type in Java?",
            Arrays.asList("16 bits (2 bytes)", "32 bits (4 bytes)", "64 bits (8 bytes)", "Platform dependent"),
            1,
            "In Java, an int is strictly 32 bits (4 bytes) signed two's complement on all platforms.",
            "Java Basics", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "What is the difference between '==' and '.equals()' when comparing two String objects?",
            Arrays.asList(
                "They are always identical in behavior",
                "'==' compares memory references, while '.equals()' compares the actual text content",
                "'.equals()' compares memory addresses, while '==' compares character values",
                "'==' can only compare numbers, not Strings"
            ),
            1,
            "'==' checks reference equality (pointing to same memory), while .equals() compares string contents.",
            "Java Basics", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "Which exception type is UNCHECKED by the Java compiler?",
            Arrays.asList("IOException", "SQLException", "NullPointerException (RuntimeException)", "ClassNotFoundException"),
            2,
            "Exceptions inheriting from RuntimeException and Error are unchecked; the compiler doesn't force try-catch or throws.",
            "Java Basics", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "Which keyword is used to explicitly raise an exception in Java?",
            Arrays.asList("throws", "throw", "raise", "catch"),
            1,
            "The 'throw' keyword is used to explicitly trigger an exception: e.g. throw new IllegalArgumentException();",
            "Java Basics", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "Which interface must a resource class implement to be used within a 'try-with-resources' statement?",
            Arrays.asList("java.io.Serializable", "java.lang.AutoCloseable", "java.lang.Cloneable", "java.lang.Runnable"),
            1,
            "Try-with-resources requires the resource to implement AutoCloseable or Closeable.",
            "Java Basics", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "What is the file extension of compiled Java bytecode?",
            Arrays.asList(".java", ".class", ".byte", ".obj"),
            1,
            "Compiling a .java source file produces .class files containing JVM bytecode.",
            "Java Basics", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "Which method signature is valid for the main entry point in standard Java?",
            Arrays.asList(
                "public void main(String[] args)",
                "public static void main(String[] args)",
                "static void main(String args)",
                "public int main(String[] args)"
            ),
            1,
            "The JVM looks for 'public static void main(String[] args)' to launch an application.",
            "Java Basics", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "What does calling `System.gc()` do in Java?",
            Arrays.asList(
                "Forces immediate synchronous cleanup of all memory",
                "Suggests to the JVM that garbage collection be run",
                "Terminates unused threads immediately",
                "Deletes all compiled class files"
            ),
            1,
            "System.gc() merely hints to the JVM to run garbage collection, but does not guarantee immediate execution.",
            "Java Basics", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "Which of the following types CANNOT be used in a Java switch statement (prior to pattern matching)?",
            Arrays.asList("int", "String", "double", "char"),
            2,
            "Floating-point types like double and float cannot be used in traditional Java switch expressions.",
            "Java Basics", "Medium"
        ));

        // ==========================================
        // 2. OOP CONCEPTS (15 Questions)
        // ==========================================
        questionBank.add(new Question(
            id++,
            "Which OOP principle is demonstrated by having multiple methods with the same name but different parameters?",
            Arrays.asList("Inheritance", "Method Overloading (Polymorphism)", "Encapsulation", "Abstraction"),
            1,
            "Method Overloading is compile-time (static) polymorphism where methods share the same name with different signatures.",
            "OOP Concepts", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "Can an interface in Java 8 and later have method bodies?",
            Arrays.asList(
                "No, never",
                "Yes, using 'default' or 'static' keywords",
                "Yes, but only private methods",
                "Only if the interface is abstract"
            ),
            1,
            "Since Java 8, interfaces can provide method implementations using default and static methods.",
            "OOP Concepts", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "Which access modifier makes a member accessible ONLY within its own package and by subclasses?",
            Arrays.asList("private", "default (package-private)", "protected", "public"),
            2,
            "'protected' allows access within the same package and to subclasses in different packages.",
            "OOP Concepts", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "What happens if a class extends an abstract class but does not implement all its abstract methods?",
            Arrays.asList(
                "It compiles fine with default null behaviors",
                "The class must also be declared abstract or it fails to compile",
                "A runtime exception is thrown when invoked",
                "The JVM automatically provides empty stubs"
            ),
            1,
            "If a subclass does not implement all abstract methods of its superclass, it must itself be declared abstract.",
            "OOP Concepts", "Hard"
        ));

        questionBank.add(new Question(
            id++,
            "Which keyword is used by a class to inherit from another class in Java?",
            Arrays.asList("implements", "extends", "inherits", "instanceof"),
            1,
            "In Java, classes use 'extends' for class inheritance and 'implements' for interfaces.",
            "OOP Concepts", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "Does Java support multiple inheritance of classes (e.g. `class C extends A, B`)?",
            Arrays.asList("Yes, always", "No, Java avoids the diamond problem by allowing only single class inheritance", "Yes, if both classes are abstract", "Only in Java 17 and newer"),
            1,
            "Java does not support multiple class inheritance to avoid ambiguity (the Diamond Problem).",
            "OOP Concepts", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "What is the purpose of the 'super' keyword in Java?",
            Arrays.asList(
                "To create a superuser admin thread",
                "To refer to the immediate parent class object, methods, or constructor",
                "To mark a class as globally accessible",
                "To allocate extra memory on heap"
            ),
            1,
            "'super' is used to invoke superclass constructors (`super()`) or call overridden parent methods (`super.method()`).",
            "OOP Concepts", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "What is Encapsulation in Object-Oriented Programming?",
            Arrays.asList(
                "Hiding implementation details behind public methods (getters/setters) and keeping fields private",
                "Allowing classes to have multiple parent classes",
                "Writing the same function with multiple argument types",
                "Converting high-level code to bytecode"
            ),
            0,
            "Encapsulation is bundling data and methods that operate on that data while restricting direct access to object fields.",
            "OOP Concepts", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "Can a constructor in Java be declared as private?",
            Arrays.asList(
                "No, constructors must always be public",
                "Yes, commonly used in Singleton pattern or utility classes to prevent direct instantiation",
                "Only if the class is marked as abstract",
                "Yes, but the program will not compile"
            ),
            1,
            "Private constructors prevent outside classes from creating instances, widely used in Singleton or utility classes.",
            "OOP Concepts", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "Can an abstract class be directly instantiated using the 'new' operator?",
            Arrays.asList("Yes, always", "No, abstract classes cannot be directly instantiated", "Yes, if it has a constructor", "Only if it implements Serializable"),
            1,
            "Abstract classes cannot be instantiated with 'new'; they must be subclassed by concrete classes.",
            "OOP Concepts", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "What is the role of the `@Override` annotation in Java?",
            Arrays.asList(
                "It makes the method execute twice as fast",
                "It informs the compiler to verify that the method actually overrides a superclass or interface method",
                "It makes a private method public",
                "It prevents subclasses from modifying the method"
            ),
            1,
            "`@Override` instructs the compiler to check that the method signature properly overrides a method in a supertype.",
            "OOP Concepts", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "What is Dynamic Method Dispatch (Runtime Polymorphism)?",
            Arrays.asList(
                "Deciding which overloaded method to call at compile-time",
                "Resolving which overridden method to execute at runtime based on the actual object instance",
                "Sending messages between network sockets",
                "Dynamically loading JAR files"
            ),
            1,
            "Dynamic method dispatch resolves overridden method calls at runtime based on the type of object being referred to.",
            "OOP Concepts", "Hard"
        ));

        questionBank.add(new Question(
            id++,
            "What is a Marker Interface in Java?",
            Arrays.asList(
                "An interface with at least 10 methods",
                "An empty interface with no methods or constants (e.g. Serializable, Cloneable)",
                "An interface that can only be implemented once",
                "An interface used exclusively for graphic UI"
            ),
            1,
            "A marker interface has no methods or fields; it acts as a tag to inform JVM or libraries of special behavior.",
            "OOP Concepts", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "Which OOP principle emphasizes showing only essential features and hiding complex background details?",
            Arrays.asList("Abstraction", "Inheritance", "Polymorphism", "Coupling"),
            0,
            "Abstraction hides internal complexity and exposes only the necessary interface to the user.",
            "OOP Concepts", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "What is a Covariant Return Type in Java?",
            Arrays.asList(
                "An overridden method returning a subclass type of the return type declared in parent method",
                "A method returning multiple values simultaneously",
                "A method that cannot return null",
                "A method returning a generic wildcard"
            ),
            0,
            "Covariant return type allows an overriding method to narrow the return type to a subtype.",
            "OOP Concepts", "Hard"
        ));

        // ==========================================
        // 3. COLLECTIONS & DATA STRUCTURES (15 Questions)
        // ==========================================
        questionBank.add(new Question(
            id++,
            "Which collection class does NOT allow duplicate elements?",
            Arrays.asList("ArrayList", "LinkedList", "HashSet", "Vector"),
            2,
            "Set implementations like HashSet guarantee uniqueness and do not allow duplicate elements.",
            "Collections", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "What is the average time complexity for searching an element in a HashMap with good hash distribution?",
            Arrays.asList("O(1)", "O(log n)", "O(n)", "O(n^2)"),
            0,
            "A well-distributed HashMap provides O(1) constant average time for get() and put() operations.",
            "Collections", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "Which data structure follows the First-In, First-Out (FIFO) principle?",
            Arrays.asList("Stack", "Queue", "Tree", "Graph"),
            1,
            "A Queue operates on FIFO (First-In, First-Out), while Stack operates on LIFO (Last-In, First-Out).",
            "Collections", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "What is the key difference between ArrayList and LinkedList in Java?",
            Arrays.asList(
                "ArrayList is synchronized, LinkedList is not",
                "ArrayList uses a dynamic resizable array; LinkedList uses a doubly-linked list node chain",
                "LinkedList provides faster random index access than ArrayList",
                "ArrayList cannot store primitive wrapper types"
            ),
            1,
            "ArrayList is backed by an array (fast O(1) random access), while LinkedList is a doubly-linked list (fast O(1) insertion/deletion at ends).",
            "Collections", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "Which interface is the root of the standard Java Collections hierarchy (excluding Maps)?",
            Arrays.asList("java.util.List", "java.util.Collection", "java.util.Iterable", "java.util.Map"),
            1,
            "java.util.Collection is the root interface for List, Set, and Queue. Map is part of Collections Framework but does not extend Collection.",
            "Collections", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "Which data structure follows the Last-In, First-Out (LIFO) principle?",
            Arrays.asList("Queue", "Stack", "LinkedList", "HashMap"),
            1,
            "A Stack operates on LIFO (Last-In, First-Out).",
            "Collections", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "What is the main difference between HashMap and Hashtable in Java?",
            Arrays.asList(
                "Hashtable is synchronized and thread-safe, and does not allow null keys or values",
                "HashMap is thread-safe and slower than Hashtable",
                "Hashtable is sorted, HashMap is unsorted",
                "They are identical aliases"
            ),
            0,
            "Hashtable is synchronized (thread-safe, legacy) and disallows nulls. HashMap is unsynchronized and permits one null key.",
            "Collections", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "Which Map implementation in Java maintains keys in natural or specified sorted order?",
            Arrays.asList("HashMap", "LinkedHashMap", "TreeMap", "WeakHashMap"),
            2,
            "TreeMap is based on a Red-Black tree structure and keeps its keys sorted in ascending/custom order.",
            "Collections", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "What is the difference between Comparable and Comparator interfaces in Java?",
            Arrays.asList(
                "Comparable provides natural ordering (`compareTo`); Comparator provides custom ordering (`compare`)",
                "Comparator can only sort integers, Comparable sorts Strings",
                "They are both deprecated in modern Java",
                "Comparable is located in java.util, Comparator is in java.lang"
            ),
            0,
            "Comparable imposes natural ordering via compareTo() inside the class; Comparator allows separate custom sorting strategies via compare().",
            "Collections", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "What is the amortized time complexity to append an element to the end of an ArrayList?",
            Arrays.asList("O(1)", "O(n)", "O(log n)", "O(n^2)"),
            0,
            "Appending to an ArrayList takes O(1) amortized time, only occasionally resizing the array when capacity is reached.",
            "Collections", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "How does PriorityQueue in Java order its elements by default?",
            Arrays.asList("FIFO order", "Natural ascending order (Min-Heap)", "LIFO order", "Insertion order"),
            1,
            "By default, Java's PriorityQueue implements a min-heap where the smallest element is at the head.",
            "Collections", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "What is the safe way to remove an element while iterating over a Collection?",
            Arrays.asList(
                "Using collection.remove() inside a standard foreach loop",
                "Using Iterator's iterator.remove() method",
                "Setting the item to null",
                "Invoking System.gc() during iteration"
            ),
            1,
            "Using iterator.remove() avoids throwing a ConcurrentModificationException during traversal.",
            "Collections", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "Which Set implementation preserves the exact INSERTION order of elements?",
            Arrays.asList("HashSet", "TreeSet", "LinkedHashSet", "EnumSet"),
            2,
            "LinkedHashSet maintains a doubly-linked list running through all its entries, preserving insertion order.",
            "Collections", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "Which thread-safe List implementation is optimized for situations where reads vastly outnumber writes?",
            Arrays.asList("Vector", "Collections.synchronizedList", "CopyOnWriteArrayList", "ArrayList"),
            2,
            "CopyOnWriteArrayList creates a fresh copy of the array on every write, providing lock-free reads.",
            "Collections", "Hard"
        ));

        questionBank.add(new Question(
            id++,
            "In Java 8 Stream API, what does the `filter()` intermediate operation do?",
            Arrays.asList(
                "Sorts the stream in reverse order",
                "Returns a new stream containing only elements matching a given Predicate condition",
                "Transforms each element to a new type",
                "Terminates the stream and prints elements"
            ),
            1,
            "filter() takes a Predicate and returns a Stream consisting only of elements that evaluate to true.",
            "Collections", "Easy"
        ));

        // ==========================================
        // 4. WEB TECHNOLOGIES & NETWORKING (15 Questions)
        // ==========================================
        questionBank.add(new Question(
            id++,
            "Which HTTP status code signifies that a requested resource was successfully created?",
            Arrays.asList("200 OK", "201 Created", "204 No Content", "301 Moved"),
            1,
            "HTTP 201 Created indicates that the request has succeeded and led to the creation of a resource.",
            "Web Technologies", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "Which protocol is used to securely encrypt web browser traffic?",
            Arrays.asList("FTP", "HTTPS (SSL/TLS)", "SMTP", "SNMP"),
            1,
            "HTTPS uses SSL/TLS to encrypt communications between client browsers and web servers.",
            "Web Technologies", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "In web development, what does CSS stand for?",
            Arrays.asList(
                "Creative Style Sheets",
                "Cascading Style Sheets",
                "Computer System Styles",
                "Color and Style Syntax"
            ),
            1,
            "CSS stands for Cascading Style Sheets, used to style HTML documents.",
            "Web Technologies", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "Which Java standard library class provides a lightweight HTTP server out-of-the-box?",
            Arrays.asList("java.net.HttpUrlConnection", "com.sun.net.httpserver.HttpServer", "javax.servlet.http.HttpServlet", "java.io.HttpReader"),
            1,
            "com.sun.net.httpserver.HttpServer is the standard lightweight HTTP server included in Java SE since Java 6.",
            "Web Technologies", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "Which HTTP request method is primarily designed to retrieve representation of a resource without side effects?",
            Arrays.asList("POST", "DELETE", "GET", "PUT"),
            2,
            "GET is an idempotent and safe HTTP method used to read data without altering server state.",
            "Web Technologies", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "Which HTTP method is typically used to create or submit new data to a web server?",
            Arrays.asList("GET", "POST", "HEAD", "TRACE"),
            1,
            "POST sends enclosed entity data to the designated resource, typically resulting in a change on the server.",
            "Web Technologies", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "Which HTTP status code indicates 'Not Found'?",
            Arrays.asList("400", "401", "403", "404"),
            3,
            "HTTP 404 indicates that the server cannot find the requested resource.",
            "Web Technologies", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "Which HTTP status code indicates an 'Internal Server Error'?",
            Arrays.asList("500", "502", "503", "504"),
            0,
            "HTTP 500 is a generic error message indicating that an unexpected condition occurred on the server.",
            "Web Technologies", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "What is the default port number for unencrypted HTTP web traffic?",
            Arrays.asList("21", "22", "80", "443"),
            2,
            "Port 80 is the standard port assigned to unencrypted HTTP web requests.",
            "Web Technologies", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "What is the default port number for secure HTTPS web traffic?",
            Arrays.asList("80", "443", "8080", "8443"),
            1,
            "Port 443 is the standard default port for encrypted HTTPS traffic.",
            "Web Technologies", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "What does REST stand for in web API architecture?",
            Arrays.asList(
                "Representational State Transfer",
                "Remote Execution Standard Technology",
                "Resource Encoding and Security Transmission",
                "Reliable Endpoint Server Tunnel"
            ),
            0,
            "REST stands for Representational State Transfer, an architectural style for networked web applications.",
            "Web Technologies", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "What is JSON?",
            Arrays.asList(
                "Java Standard Object Network",
                "JavaScript Object Notation, a lightweight text data-interchange format",
                "Java Scripting Operation Node",
                "Joint System Operating Network"
            ),
            1,
            "JSON stands for JavaScript Object Notation and is the most common format for REST APIs.",
            "Web Technologies", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "What does CORS stand for in web browsers?",
            Arrays.asList(
                "Cross-Origin Resource Sharing",
                "Client-Oriented Remote Server",
                "Cascading Override Response System",
                "Central Online Resource Script"
            ),
            0,
            "CORS allows servers to specify who can access their resources from other origins via HTTP headers.",
            "Web Technologies", "Medium"
        ));

        questionBank.add(new Question(
            id++,
            "In web development, what does DOM stand for?",
            Arrays.asList(
                "Data Object Model",
                "Document Object Model",
                "Digital Ordinance Module",
                "Dynamic Operation Method"
            ),
            1,
            "The DOM is the programming interface representing the structure of HTML documents as a tree of nodes.",
            "Web Technologies", "Easy"
        ));

        questionBank.add(new Question(
            id++,
            "Which HTTP status code represents 'Forbidden' access?",
            Arrays.asList("401 Unauthorized", "403 Forbidden", "405 Method Not Allowed", "408 Timeout"),
            1,
            "HTTP 403 Forbidden indicates that the server understands the request but refuses to authorize it.",
            "Web Technologies", "Medium"
        ));
    }

    public List<Question> getAllQuestions() {
        return Collections.unmodifiableList(questionBank);
    }

    public List<String> getCategories() {
        return questionBank.stream()
                .map(Question::getCategory)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Question> getQuestions(String category, int limit, boolean shuffle) {
        List<Question> matching = new ArrayList<>();
        List<Question> others = new ArrayList<>();

        for (Question q : questionBank) {
            if (category == null || category.equalsIgnoreCase("all") || category.equalsIgnoreCase(q.getCategory())) {
                matching.add(q);
            } else {
                others.add(q);
            }
        }

        if (shuffle) {
            Collections.shuffle(matching);
            Collections.shuffle(others);
        }

        // Guarantee that the requested question limit is ALWAYS satisfied!
        // If a category has fewer than requested, fill from the general pool
        if (limit > 0 && matching.size() < limit) {
            for (Question q : others) {
                if (matching.size() >= limit) break;
                matching.add(q);
            }
        }

        if (limit > 0 && limit < matching.size()) {
            return new ArrayList<>(matching.subList(0, limit));
        }
        return matching;
    }
}
