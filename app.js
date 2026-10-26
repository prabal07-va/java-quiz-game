/**
 * Java Quiz Master - Frontend Application Engine
 * Handles gameplay, seconds timer, Web Audio effects, confetti, and results.
 */

// ==========================================
// 1. FALLBACK QUESTIONS (for offline/standalone)
// ==========================================
const FALLBACK_QUESTIONS = [{"id":49,"question":"Which Java standard library class provides a lightweight HTTP server out-of-the-box?","options":["java.net.HttpUrlConnection","com.sun.net.httpserver.HttpServer","javax.servlet.http.HttpServlet","java.io.HttpReader"],"correctIndex":1,"explanation":"com.sun.net.httpserver.HttpServer is the standard lightweight HTTP server included in Java SE since Java 6.","category":"Web Technologies","difficulty":"Medium"},{"id":18,"question":"Which access modifier makes a member accessible ONLY within its own package and by subclasses?","options":["private","default (package-private)","protected","public"],"correctIndex":2,"explanation":"'protected' allows access within the same package and to subclasses in different packages.","category":"OOP Concepts","difficulty":"Medium"},{"id":25,"question":"Can an abstract class be directly instantiated using the 'new' operator?","options":["Yes, always","No, abstract classes cannot be directly instantiated","Yes, if it has a constructor","Only if it implements Serializable"],"correctIndex":1,"explanation":"Abstract classes cannot be instantiated with 'new'; they must be subclassed by concrete classes.","category":"OOP Concepts","difficulty":"Easy"},{"id":40,"question":"What is the amortized time complexity to append an element to the end of an ArrayList?","options":["O(1)","O(n)","O(log n)","O(n^2)"],"correctIndex":0,"explanation":"Appending to an ArrayList takes O(1) amortized time, only occasionally resizing the array when capacity is reached.","category":"Collections","difficulty":"Medium"},{"id":59,"question":"In web development, what does DOM stand for?","options":["Data Object Model","Document Object Model","Digital Ordinance Module","Dynamic Operation Method"],"correctIndex":1,"explanation":"The DOM is the programming interface representing the structure of HTML documents as a tree of nodes.","category":"Web Technologies","difficulty":"Easy"},{"id":35,"question":"Which interface is the root of the standard Java Collections hierarchy (excluding Maps)?","options":["java.util.List","java.util.Collection","java.util.Iterable","java.util.Map"],"correctIndex":1,"explanation":"java.util.Collection is the root interface for List, Set, and Queue. Map is part of Collections Framework but does not extend Collection.","category":"Collections","difficulty":"Medium"},{"id":39,"question":"What is the difference between Comparable and Comparator interfaces in Java?","options":["Comparable provides natural ordering (`compareTo`); Comparator provides custom ordering (`compare`)","Comparator can only sort integers, Comparable sorts Strings","They are both deprecated in modern Java","Comparable is located in java.util, Comparator is in java.lang"],"correctIndex":0,"explanation":"Comparable imposes natural ordering via compareTo() inside the class; Comparator allows separate custom sorting strategies via compare().","category":"Collections","difficulty":"Medium"},{"id":46,"question":"Which HTTP status code signifies that a requested resource was successfully created?","options":["200 OK","201 Created","204 No Content","301 Moved"],"correctIndex":1,"explanation":"HTTP 201 Created indicates that the request has succeeded and led to the creation of a resource.","category":"Web Technologies","difficulty":"Easy"},{"id":54,"question":"What is the default port number for unencrypted HTTP web traffic?","options":["21","22","80","443"],"correctIndex":2,"explanation":"Port 80 is the standard port assigned to unencrypted HTTP web requests.","category":"Web Technologies","difficulty":"Easy"},{"id":17,"question":"Can an interface in Java 8 and later have method bodies?","options":["No, never","Yes, using 'default' or 'static' keywords","Yes, but only private methods","Only if the interface is abstract"],"correctIndex":1,"explanation":"Since Java 8, interfaces can provide method implementations using default and static methods.","category":"OOP Concepts","difficulty":"Medium"},{"id":36,"question":"Which data structure follows the Last-In, First-Out (LIFO) principle?","options":["Queue","Stack","LinkedList","HashMap"],"correctIndex":1,"explanation":"A Stack operates on LIFO (Last-In, First-Out).","category":"Collections","difficulty":"Easy"},{"id":32,"question":"What is the average time complexity for searching an element in a HashMap with good hash distribution?","options":["O(1)","O(log n)","O(n)","O(n^2)"],"correctIndex":0,"explanation":"A well-distributed HashMap provides O(1) constant average time for get() and put() operations.","category":"Collections","difficulty":"Medium"},{"id":26,"question":"What is the role of the `@Override` annotation in Java?","options":["It makes the method execute twice as fast","It informs the compiler to verify that the method actually overrides a superclass or interface method","It makes a private method public","It prevents subclasses from modifying the method"],"correctIndex":1,"explanation":"`@Override` instructs the compiler to check that the method signature properly overrides a method in a supertype.","category":"OOP Concepts","difficulty":"Medium"},{"id":48,"question":"In web development, what does CSS stand for?","options":["Creative Style Sheets","Cascading Style Sheets","Computer System Styles","Color and Style Syntax"],"correctIndex":1,"explanation":"CSS stands for Cascading Style Sheets, used to style HTML documents.","category":"Web Technologies","difficulty":"Easy"},{"id":50,"question":"Which HTTP request method is primarily designed to retrieve representation of a resource without side effects?","options":["POST","DELETE","GET","PUT"],"correctIndex":2,"explanation":"GET is an idempotent and safe HTTP method used to read data without altering server state.","category":"Web Technologies","difficulty":"Easy"},{"id":52,"question":"Which HTTP status code indicates 'Not Found'?","options":["400","401","403","404"],"correctIndex":3,"explanation":"HTTP 404 indicates that the server cannot find the requested resource.","category":"Web Technologies","difficulty":"Easy"},{"id":4,"question":"What will `System.out.println(10 + 20 + \"Hello\");` print?","options":["1020Hello","30Hello","Hello1020","Compile Error"],"correctIndex":1,"explanation":"Java evaluates left-to-right. 10 + 20 gives 30, and 30 + \"Hello\" results in \"30Hello\".","category":"Java Basics","difficulty":"Medium"},{"id":27,"question":"What is Dynamic Method Dispatch (Runtime Polymorphism)?","options":["Deciding which overloaded method to call at compile-time","Resolving which overridden method to execute at runtime based on the actual object instance","Sending messages between network sockets","Dynamically loading JAR files"],"correctIndex":1,"explanation":"Dynamic method dispatch resolves overridden method calls at runtime based on the type of object being referred to.","category":"OOP Concepts","difficulty":"Hard"},{"id":21,"question":"Does Java support multiple inheritance of classes (e.g. `class C extends A, B`)?","options":["Yes, always","No, Java avoids the diamond problem by allowing only single class inheritance","Yes, if both classes are abstract","Only in Java 17 and newer"],"correctIndex":1,"explanation":"Java does not support multiple class inheritance to avoid ambiguity (the Diamond Problem).","category":"OOP Concepts","difficulty":"Easy"},{"id":57,"question":"What is JSON?","options":["Java Standard Object Network","JavaScript Object Notation, a lightweight text data-interchange format","Java Scripting Operation Node","Joint System Operating Network"],"correctIndex":1,"explanation":"JSON stands for JavaScript Object Notation and is the most common format for REST APIs.","category":"Web Technologies","difficulty":"Easy"},{"id":5,"question":"Which memory area in JVM stores Java Objects?","options":["Stack Memory","Heap Memory","Method Area","PC Register"],"correctIndex":1,"explanation":"All class instances and arrays are allocated in the Heap memory in JVM.","category":"Java Basics","difficulty":"Medium"},{"id":19,"question":"What happens if a class extends an abstract class but does not implement all its abstract methods?","options":["It compiles fine with default null behaviors","The class must also be declared abstract or it fails to compile","A runtime exception is thrown when invoked","The JVM automatically provides empty stubs"],"correctIndex":1,"explanation":"If a subclass does not implement all abstract methods of its superclass, it must itself be declared abstract.","category":"OOP Concepts","difficulty":"Hard"},{"id":28,"question":"What is a Marker Interface in Java?","options":["An interface with at least 10 methods","An empty interface with no methods or constants (e.g. Serializable, Cloneable)","An interface that can only be implemented once","An interface used exclusively for graphic UI"],"correctIndex":1,"explanation":"A marker interface has no methods or fields; it acts as a tag to inform JVM or libraries of special behavior.","category":"OOP Concepts","difficulty":"Medium"},{"id":47,"question":"Which protocol is used to securely encrypt web browser traffic?","options":["FTP","HTTPS (SSL/TLS)","SMTP","SNMP"],"correctIndex":1,"explanation":"HTTPS uses SSL/TLS to encrypt communications between client browsers and web servers.","category":"Web Technologies","difficulty":"Easy"},{"id":16,"question":"Which OOP principle is demonstrated by having multiple methods with the same name but different parameters?","options":["Inheritance","Method Overloading (Polymorphism)","Encapsulation","Abstraction"],"correctIndex":1,"explanation":"Method Overloading is compile-time (static) polymorphism where methods share the same name with different signatures.","category":"OOP Concepts","difficulty":"Easy"},{"id":45,"question":"In Java 8 Stream API, what does the `filter()` intermediate operation do?","options":["Sorts the stream in reverse order","Returns a new stream containing only elements matching a given Predicate condition","Transforms each element to a new type","Terminates the stream and prints elements"],"correctIndex":1,"explanation":"filter() takes a Predicate and returns a Stream consisting only of elements that evaluate to true.","category":"Collections","difficulty":"Easy"},{"id":22,"question":"What is the purpose of the 'super' keyword in Java?","options":["To create a superuser admin thread","To refer to the immediate parent class object, methods, or constructor","To mark a class as globally accessible","To allocate extra memory on heap"],"correctIndex":1,"explanation":"'super' is used to invoke superclass constructors (`super()`) or call overridden parent methods (`super.method()`).","category":"OOP Concepts","difficulty":"Easy"},{"id":55,"question":"What is the default port number for secure HTTPS web traffic?","options":["80","443","8080","8443"],"correctIndex":1,"explanation":"Port 443 is the standard default port for encrypted HTTPS traffic.","category":"Web Technologies","difficulty":"Easy"},{"id":14,"question":"What does calling `System.gc()` do in Java?","options":["Forces immediate synchronous cleanup of all memory","Suggests to the JVM that garbage collection be run","Terminates unused threads immediately","Deletes all compiled class files"],"correctIndex":1,"explanation":"System.gc() merely hints to the JVM to run garbage collection, but does not guarantee immediate execution.","category":"Java Basics","difficulty":"Medium"},{"id":12,"question":"What is the file extension of compiled Java bytecode?","options":[".java",".class",".byte",".obj"],"correctIndex":1,"explanation":"Compiling a .java source file produces .class files containing JVM bytecode.","category":"Java Basics","difficulty":"Easy"},{"id":24,"question":"Can a constructor in Java be declared as private?","options":["No, constructors must always be public","Yes, commonly used in Singleton pattern or utility classes to prevent direct instantiation","Only if the class is marked as abstract","Yes, but the program will not compile"],"correctIndex":1,"explanation":"Private constructors prevent outside classes from creating instances, widely used in Singleton or utility classes.","category":"OOP Concepts","difficulty":"Medium"},{"id":1,"question":"Which of the following is NOT a Java primitive data type?","options":["int","boolean","String","double"],"correctIndex":2,"explanation":"String is an object (reference type) in Java, while int, boolean, and double are primitive types.","category":"Java Basics","difficulty":"Easy"},{"id":9,"question":"Which exception type is UNCHECKED by the Java compiler?","options":["IOException","SQLException","NullPointerException (RuntimeException)","ClassNotFoundException"],"correctIndex":2,"explanation":"Exceptions inheriting from RuntimeException and Error are unchecked; the compiler doesn't force try-catch or throws.","category":"Java Basics","difficulty":"Medium"},{"id":44,"question":"Which thread-safe List implementation is optimized for situations where reads vastly outnumber writes?","options":["Vector","Collections.synchronizedList","CopyOnWriteArrayList","ArrayList"],"correctIndex":2,"explanation":"CopyOnWriteArrayList creates a fresh copy of the array on every write, providing lock-free reads.","category":"Collections","difficulty":"Hard"},{"id":10,"question":"Which keyword is used to explicitly raise an exception in Java?","options":["throws","throw","raise","catch"],"correctIndex":1,"explanation":"The 'throw' keyword is used to explicitly trigger an exception: e.g. throw new IllegalArgumentException();","category":"Java Basics","difficulty":"Easy"},{"id":34,"question":"What is the key difference between ArrayList and LinkedList in Java?","options":["ArrayList is synchronized, LinkedList is not","ArrayList uses a dynamic resizable array; LinkedList uses a doubly-linked list node chain","LinkedList provides faster random index access than ArrayList","ArrayList cannot store primitive wrapper types"],"correctIndex":1,"explanation":"ArrayList is backed by an array (fast O(1) random access), while LinkedList is a doubly-linked list (fast O(1) insertion/deletion at ends).","category":"Collections","difficulty":"Medium"},{"id":42,"question":"What is the safe way to remove an element while iterating over a Collection?","options":["Using collection.remove() inside a standard foreach loop","Using Iterator's iterator.remove() method","Setting the item to null","Invoking System.gc() during iteration"],"correctIndex":1,"explanation":"Using iterator.remove() avoids throwing a ConcurrentModificationException during traversal.","category":"Collections","difficulty":"Medium"},{"id":51,"question":"Which HTTP method is typically used to create or submit new data to a web server?","options":["GET","POST","HEAD","TRACE"],"correctIndex":1,"explanation":"POST sends enclosed entity data to the designated resource, typically resulting in a change on the server.","category":"Web Technologies","difficulty":"Easy"},{"id":2,"question":"What is the default value of an uninitialized boolean instance variable in Java?","options":["true","false","null","0"],"correctIndex":1,"explanation":"Instance and static boolean variables in Java default to false if not explicitly initialized.","category":"Java Basics","difficulty":"Easy"},{"id":6,"question":"Which of the following is true about Java String immutability?","options":["Strings can be modified using the .append() method","Once created, the internal character sequence of a String object cannot change","Strings are mutable if marked as public","StringBuilder and String are identical in memory"],"correctIndex":1,"explanation":"Java String objects are immutable; any modification creates a new String instance.","category":"Java Basics","difficulty":"Medium"},{"id":33,"question":"Which data structure follows the First-In, First-Out (FIFO) principle?","options":["Stack","Queue","Tree","Graph"],"correctIndex":1,"explanation":"A Queue operates on FIFO (First-In, First-Out), while Stack operates on LIFO (Last-In, First-Out).","category":"Collections","difficulty":"Easy"},{"id":41,"question":"How does PriorityQueue in Java order its elements by default?","options":["FIFO order","Natural ascending order (Min-Heap)","LIFO order","Insertion order"],"correctIndex":1,"explanation":"By default, Java's PriorityQueue implements a min-heap where the smallest element is at the head.","category":"Collections","difficulty":"Medium"},{"id":56,"question":"What does REST stand for in web API architecture?","options":["Representational State Transfer","Remote Execution Standard Technology","Resource Encoding and Security Transmission","Reliable Endpoint Server Tunnel"],"correctIndex":0,"explanation":"REST stands for Representational State Transfer, an architectural style for networked web applications.","category":"Web Technologies","difficulty":"Medium"},{"id":38,"question":"Which Map implementation in Java maintains keys in natural or specified sorted order?","options":["HashMap","LinkedHashMap","TreeMap","WeakHashMap"],"correctIndex":2,"explanation":"TreeMap is based on a Red-Black tree structure and keeps its keys sorted in ascending/custom order.","category":"Collections","difficulty":"Medium"},{"id":31,"question":"Which collection class does NOT allow duplicate elements?","options":["ArrayList","LinkedList","HashSet","Vector"],"correctIndex":2,"explanation":"Set implementations like HashSet guarantee uniqueness and do not allow duplicate elements.","category":"Collections","difficulty":"Easy"},{"id":37,"question":"What is the main difference between HashMap and Hashtable in Java?","options":["Hashtable is synchronized and thread-safe, and does not allow null keys or values","HashMap is thread-safe and slower than Hashtable","Hashtable is sorted, HashMap is unsorted","They are identical aliases"],"correctIndex":0,"explanation":"Hashtable is synchronized (thread-safe, legacy) and disallows nulls. HashMap is unsynchronized and permits one null key.","category":"Collections","difficulty":"Medium"},{"id":20,"question":"Which keyword is used by a class to inherit from another class in Java?","options":["implements","extends","inherits","instanceof"],"correctIndex":1,"explanation":"In Java, classes use 'extends' for class inheritance and 'implements' for interfaces.","category":"OOP Concepts","difficulty":"Easy"},{"id":7,"question":"What is the size of an 'int' data type in Java?","options":["16 bits (2 bytes)","32 bits (4 bytes)","64 bits (8 bytes)","Platform dependent"],"correctIndex":1,"explanation":"In Java, an int is strictly 32 bits (4 bytes) signed two's complement on all platforms.","category":"Java Basics","difficulty":"Easy"},{"id":11,"question":"Which interface must a resource class implement to be used within a 'try-with-resources' statement?","options":["java.io.Serializable","java.lang.AutoCloseable","java.lang.Cloneable","java.lang.Runnable"],"correctIndex":1,"explanation":"Try-with-resources requires the resource to implement AutoCloseable or Closeable.","category":"Java Basics","difficulty":"Medium"},{"id":3,"question":"Which keyword is used to prevent method overriding in Java?","options":["static","const","final","abstract"],"correctIndex":2,"explanation":"The 'final' keyword on a method prevents subclasses from overriding it.","category":"Java Basics","difficulty":"Easy"},{"id":60,"question":"Which HTTP status code represents 'Forbidden' access?","options":["401 Unauthorized","403 Forbidden","405 Method Not Allowed","408 Timeout"],"correctIndex":1,"explanation":"HTTP 403 Forbidden indicates that the server understands the request but refuses to authorize it.","category":"Web Technologies","difficulty":"Medium"},{"id":58,"question":"What does CORS stand for in web browsers?","options":["Cross-Origin Resource Sharing","Client-Oriented Remote Server","Cascading Override Response System","Central Online Resource Script"],"correctIndex":0,"explanation":"CORS allows servers to specify who can access their resources from other origins via HTTP headers.","category":"Web Technologies","difficulty":"Medium"},{"id":13,"question":"Which method signature is valid for the main entry point in standard Java?","options":["public void main(String[] args)","public static void main(String[] args)","static void main(String args)","public int main(String[] args)"],"correctIndex":1,"explanation":"The JVM looks for 'public static void main(String[] args)' to launch an application.","category":"Java Basics","difficulty":"Easy"},{"id":15,"question":"Which of the following types CANNOT be used in a Java switch statement (prior to pattern matching)?","options":["int","String","double","char"],"correctIndex":2,"explanation":"Floating-point types like double and float cannot be used in traditional Java switch expressions.","category":"Java Basics","difficulty":"Medium"},{"id":8,"question":"What is the difference between '==' and '.equals()' when comparing two String objects?","options":["They are always identical in behavior","'==' compares memory references, while '.equals()' compares the actual text content","'.equals()' compares memory addresses, while '==' compares character values","'==' can only compare numbers, not Strings"],"correctIndex":1,"explanation":"'==' checks reference equality (pointing to same memory), while .equals() compares string contents.","category":"Java Basics","difficulty":"Medium"},{"id":53,"question":"Which HTTP status code indicates an 'Internal Server Error'?","options":["500","502","503","504"],"correctIndex":0,"explanation":"HTTP 500 is a generic error message indicating that an unexpected condition occurred on the server.","category":"Web Technologies","difficulty":"Easy"},{"id":30,"question":"What is a Covariant Return Type in Java?","options":["An overridden method returning a subclass type of the return type declared in parent method","A method returning multiple values simultaneously","A method that cannot return null","A method returning a generic wildcard"],"correctIndex":0,"explanation":"Covariant return type allows an overriding method to narrow the return type to a subtype.","category":"OOP Concepts","difficulty":"Hard"},{"id":23,"question":"What is Encapsulation in Object-Oriented Programming?","options":["Hiding implementation details behind public methods (getters/setters) and keeping fields private","Allowing classes to have multiple parent classes","Writing the same function with multiple argument types","Converting high-level code to bytecode"],"correctIndex":0,"explanation":"Encapsulation is bundling data and methods that operate on that data while restricting direct access to object fields.","category":"OOP Concepts","difficulty":"Easy"},{"id":29,"question":"Which OOP principle emphasizes showing only essential features and hiding complex background details?","options":["Abstraction","Inheritance","Polymorphism","Coupling"],"correctIndex":0,"explanation":"Abstraction hides internal complexity and exposes only the necessary interface to the user.","category":"OOP Concepts","difficulty":"Easy"},{"id":43,"question":"Which Set implementation preserves the exact INSERTION order of elements?","options":["HashSet","TreeSet","LinkedHashSet","EnumSet"],"correctIndex":2,"explanation":"LinkedHashSet maintains a doubly-linked list running through all its entries, preserving insertion order.","category":"Collections","difficulty":"Medium"}];

// ==========================================
// 2. SYNTHESIZED SOUND EFFECTS (Web Audio API)
// ==========================================
class SoundFX {
  constructor() {
    this.enabled = true;
    this.ctx = null;
  }

  init() {
    if (!this.ctx) {
      const AudioContext = window.AudioContext || window.webkitAudioContext;
      if (AudioContext) {
        this.ctx = new AudioContext();
      }
    }
    if (this.ctx && this.ctx.state === 'suspended') {
      this.ctx.resume();
    }
  }

  toggle() {
    this.enabled = !this.enabled;
    return this.enabled;
  }

  playTick() {
    if (!this.enabled) return;
    this.init();
    if (!this.ctx) return;
    try {
      const osc = this.ctx.createOscillator();
      const gain = this.ctx.createGain();
      osc.type = 'triangle';
      osc.frequency.setValueAtTime(800, this.ctx.currentTime);
      gain.gain.setValueAtTime(0.04, this.ctx.currentTime);
      gain.gain.exponentialRampToValueAtTime(0.001, this.ctx.currentTime + 0.05);
      osc.connect(gain);
      gain.connect(this.ctx.destination);
      osc.start();
      osc.stop(this.ctx.currentTime + 0.05);
    } catch (e) {}
  }

  playUrgentTick() {
    if (!this.enabled) return;
    this.init();
    if (!this.ctx) return;
    try {
      const osc = this.ctx.createOscillator();
      const gain = this.ctx.createGain();
      osc.type = 'sawtooth';
      osc.frequency.setValueAtTime(1050, this.ctx.currentTime);
      gain.gain.setValueAtTime(0.1, this.ctx.currentTime);
      gain.gain.exponentialRampToValueAtTime(0.001, this.ctx.currentTime + 0.08);
      osc.connect(gain);
      gain.connect(this.ctx.destination);
      osc.start();
      osc.stop(this.ctx.currentTime + 0.08);
    } catch (e) {}
  }

  playCorrect() {
    if (!this.enabled) return;
    this.init();
    if (!this.ctx) return;
    try {
      const now = this.ctx.currentTime;
      [523.25, 659.25, 783.99, 1046.50].forEach((freq, i) => {
        const osc = this.ctx.createOscillator();
        const gain = this.ctx.createGain();
        osc.type = 'sine';
        osc.frequency.value = freq;
        gain.gain.setValueAtTime(0.08, now + i * 0.06);
        gain.gain.exponentialRampToValueAtTime(0.001, now + i * 0.06 + 0.28);
        osc.connect(gain);
        gain.connect(this.ctx.destination);
        osc.start(now + i * 0.06);
        osc.stop(now + i * 0.06 + 0.3);
      });
    } catch (e) {}
  }

  playWrong() {
    if (!this.enabled) return;
    this.init();
    if (!this.ctx) return;
    try {
      const now = this.ctx.currentTime;
      const osc = this.ctx.createOscillator();
      const gain = this.ctx.createGain();
      osc.type = 'sawtooth';
      osc.frequency.setValueAtTime(220, now);
      osc.frequency.exponentialRampToValueAtTime(130, now + 0.25);
      gain.gain.setValueAtTime(0.12, now);
      gain.gain.exponentialRampToValueAtTime(0.001, now + 0.25);
      osc.connect(gain);
      gain.connect(this.ctx.destination);
      osc.start(now);
      osc.stop(now + 0.25);
    } catch (e) {}
  }

  playTimeout() {
    if (!this.enabled) return;
    this.init();
    if (!this.ctx) return;
    try {
      const now = this.ctx.currentTime;
      [320, 240, 180].forEach((freq, idx) => {
        const osc = this.ctx.createOscillator();
        const gain = this.ctx.createGain();
        osc.type = 'square';
        osc.frequency.setValueAtTime(freq, now + idx * 0.1);
        gain.gain.setValueAtTime(0.08, now + idx * 0.1);
        gain.gain.exponentialRampToValueAtTime(0.001, now + idx * 0.1 + 0.15);
        osc.connect(gain);
        gain.connect(this.ctx.destination);
        osc.start(now + idx * 0.1);
        osc.stop(now + idx * 0.1 + 0.16);
      });
    } catch (e) {}
  }

  playFanfare() {
    if (!this.enabled) return;
    this.init();
    if (!this.ctx) return;
    try {
      const notes = [440, 554.37, 659.25, 880];
      const now = this.ctx.currentTime;
      notes.forEach((f, idx) => {
        const osc = this.ctx.createOscillator();
        const gain = this.ctx.createGain();
        osc.type = 'triangle';
        osc.frequency.value = f;
        const start = now + idx * 0.12;
        gain.gain.setValueAtTime(0.12, start);
        gain.gain.exponentialRampToValueAtTime(0.001, start + 0.4);
        osc.connect(gain);
        gain.connect(this.ctx.destination);
        osc.start(start);
        osc.stop(start + 0.45);
      });
    } catch (e) {}
  }
}

// ==========================================
// 3. CONFETTI GENERATOR (Pure Canvas)
// ==========================================
class ConfettiShower {
  constructor(canvasId) {
    this.canvas = document.getElementById(canvasId);
    this.ctx = this.canvas ? this.canvas.getContext('2d') : null;
    this.particles = [];
    this.animId = null;
    this.resize();
    window.addEventListener('resize', () => this.resize());
  }

  resize() {
    if (!this.canvas) return;
    this.canvas.width = window.innerWidth;
    this.canvas.height = window.innerHeight;
  }

  launch() {
    if (!this.ctx) return;
    this.resize();
    this.particles = [];
    const colors = ['#6366f1', '#06b6d4', '#10b981', '#f59e0b', '#ec4899', '#8b5cf6'];
    for (let i = 0; i < 90; i++) {
      this.particles.push({
        x: Math.random() * this.canvas.width,
        y: Math.random() * -this.canvas.height * 0.5,
        w: Math.random() * 8 + 6,
        h: Math.random() * 6 + 4,
        color: colors[Math.floor(Math.random() * colors.length)],
        vx: (Math.random() - 0.5) * 4,
        vy: Math.random() * 3 + 3,
        rot: Math.random() * 360,
        vRot: (Math.random() - 0.5) * 8
      });
    }

    if (this.animId) cancelAnimationFrame(this.animId);
    this.loop();
  }

  loop() {
    if (!this.ctx) return;
    this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
    let activeCount = 0;

    for (const p of this.particles) {
      p.x += p.vx;
      p.y += p.vy;
      p.rot += p.vRot;

      if (p.y < this.canvas.height + 20) {
        activeCount++;
        this.ctx.save();
        this.ctx.translate(p.x, p.y);
        this.ctx.rotate((p.rot * Math.PI) / 180);
        this.ctx.fillStyle = p.color;
        this.ctx.fillRect(-p.w / 2, -p.h / 2, p.w, p.h);
        this.ctx.restore();
      }
    }

    if (activeCount > 0) {
      this.animId = requestAnimationFrame(() => this.loop());
    } else {
      this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
    }
  }

  stop() {
    if (this.animId) cancelAnimationFrame(this.animId);
    if (this.ctx) this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
  }
}

// ==========================================
// 4. MAIN GAME ENGINE
// ==========================================
class QuizGame {
  constructor() {
    // Sound & Confetti
    this.sound = new SoundFX();
    this.confetti = new ConfettiShower('confetti-canvas');

    // State
    this.playerName = 'Player';
    this.category = 'All';
    this.timerSecondsLimit = 15;
    this.questionCount = 10;

    this.questions = [];
    this.currentIndex = 0;
    this.score = 0;
    this.correctCount = 0;
    this.wrongCount = 0;
    this.timeoutCount = 0;
    this.totalTimeTaken = 0;

    this.remainingSeconds = 0;
    this.timerInterval = null;
    this.questionStartTime = 0;
    this.isAnswered = false;

    // Track user answers for detailed review:
    // { question, selectedIndex, correctIndex, timeSpent, status: 'correct'|'wrong'|'timeout' }
    this.answerHistory = [];

    // DOM Elements
    this.dom = {
      // Screens
      screenWelcome: document.getElementById('screen-welcome'),
      screenQuiz: document.getElementById('screen-quiz'),
      screenResult: document.getElementById('screen-result'),

      // Header controls
      soundBtn: document.getElementById('sound-btn'),
      soundIcon: document.getElementById('sound-icon'),
      leaderboardBtn: document.getElementById('leaderboard-btn'),

      // Welcome Form
      configForm: document.getElementById('quiz-config-form'),
      playerNameInput: document.getElementById('player-name'),

      // Quiz top bar
      categoryBadge: document.getElementById('quiz-category-badge'),
      currentQNum: document.getElementById('current-q-num'),
      totalQNum: document.getElementById('total-q-num'),
      progressBar: document.getElementById('progress-bar-fill'),
      liveScore: document.getElementById('live-score'),

      // Seconds Timer
      timerBox: document.getElementById('timer-box'),
      timerCircle: document.getElementById('timer-progress-circle'),
      timerDisplay: document.getElementById('timer-seconds-display'),

      // Question & options
      questionText: document.getElementById('question-text'),
      optionsContainer: document.getElementById('options-container'),
      explanationBox: document.getElementById('explanation-box'),
      explanationTitle: document.getElementById('explanation-title'),
      explanationText: document.getElementById('explanation-text'),
      nextBtn: document.getElementById('next-btn'),
      nextBtnText: document.getElementById('next-btn-text'),

      // Result screen
      resultTrophy: document.getElementById('result-trophy-emoji'),
      resultTitle: document.getElementById('result-title'),
      resultSubtitle: document.getElementById('result-subtitle'),
      resultPlayerName: document.getElementById('result-player-name'),
      resultCircleFg: document.getElementById('result-circle-fg'),
      resultPercentDisplay: document.getElementById('result-percentage-display'),
      performanceTag: document.getElementById('performance-tag'),
      performanceFeedback: document.getElementById('performance-feedback'),

      // Result stats
      statScore: document.getElementById('stat-score'),
      statCorrect: document.getElementById('stat-correct'),
      statWrong: document.getElementById('stat-wrong'),
      statTimeout: document.getElementById('stat-timeout'),
      statTotalTime: document.getElementById('stat-total-time'),
      statAvgTime: document.getElementById('stat-avg-time'),

      // Result actions
      restartBtn: document.getElementById('restart-btn'),
      toggleReviewBtn: document.getElementById('toggle-review-btn'),
      shareResultBtn: document.getElementById('share-result-btn'),
      reviewContainer: document.getElementById('review-container'),
      reviewList: document.getElementById('review-list'),

      // Leaderboard modal
      leaderboardModal: document.getElementById('leaderboard-modal'),
      closeModalBtn: document.getElementById('close-modal-btn'),
      modalOkBtn: document.getElementById('modal-ok-btn'),
      refreshLeaderboardBtn: document.getElementById('refresh-leaderboard-btn'),
      leaderboardRows: document.getElementById('leaderboard-rows'),

      // Toast
      toast: document.getElementById('toast')
    };

    this.timerCircumference = 2 * Math.PI * 34; // r = 34
    this.resultCircumference = 2 * Math.PI * 58; // r = 58

    this.initEvents();
    this.loadSavedPlayerName();
  }

  initEvents() {
    // Sound toggle
    this.dom.soundBtn.addEventListener('click', () => {
      const active = this.sound.toggle();
      this.dom.soundIcon.textContent = active ? 'ðŸ”Š' : 'ðŸ”‡';
      this.showToast(active ? 'Sound On' : 'Sound Muted');
    });

    // Form submit -> Start quiz
    this.dom.configForm.addEventListener('submit', (e) => {
      e.preventDefault();
      this.startQuiz();
    });

    // Next question button
    this.dom.nextBtn.addEventListener('click', () => {
      this.nextQuestion();
    });

    // Result buttons
    this.dom.restartBtn.addEventListener('click', () => {
      this.showScreen('welcome');
    });

    this.dom.toggleReviewBtn.addEventListener('click', () => {
      const isHidden = this.dom.reviewContainer.classList.toggle('hidden');
      this.dom.toggleReviewBtn.querySelector('span').textContent = isHidden ? 'ðŸ“ Review Answers' : 'ðŸ”¼ Hide Review';
      if (!isHidden) {
        this.dom.reviewContainer.scrollIntoView({ behavior: 'smooth' });
      }
    });

    this.dom.shareResultBtn.addEventListener('click', () => {
      this.copyResultToClipboard();
    });

    // Leaderboard modal
    this.dom.leaderboardBtn.addEventListener('click', () => this.openLeaderboard());
    this.dom.closeModalBtn.addEventListener('click', () => this.closeLeaderboard());
    this.dom.modalOkBtn.addEventListener('click', () => this.closeLeaderboard());
    this.dom.refreshLeaderboardBtn.addEventListener('click', () => this.fetchLeaderboard());

    this.dom.leaderboardModal.addEventListener('click', (e) => {
      if (e.target === this.dom.leaderboardModal) this.closeLeaderboard();
    });

    // Keyboard shortcuts (1-4 or A-D, Enter)
    window.addEventListener('keydown', (e) => {
      if (this.dom.screenQuiz.classList.contains('active')) {
        const key = e.key.toUpperCase();
        const map = { '1': 0, '2': 1, '3': 2, '4': 3, 'A': 0, 'B': 1, 'C': 2, 'D': 3 };

        if (map[key] !== undefined && !this.isAnswered) {
          this.selectOption(map[key]);
        } else if (e.key === 'Enter' && this.isAnswered) {
          this.nextQuestion();
        }
      }
    });
  }

  loadSavedPlayerName() {
    const saved = localStorage.getItem('javaquiz_player_name');
    if (saved) {
      this.dom.playerNameInput.value = saved;
    }
  }

  showScreen(screenName) {
    this.dom.screenWelcome.classList.remove('active');
    this.dom.screenQuiz.classList.remove('active');
    this.dom.screenResult.classList.remove('active');
    this.confetti.stop();

    if (screenName === 'welcome') {
      this.dom.screenWelcome.classList.add('active');
    } else if (screenName === 'quiz') {
      this.dom.screenQuiz.classList.add('active');
    } else if (screenName === 'result') {
      this.dom.screenResult.classList.add('active');
    }
  }

  // ==========================================
  // START QUIZ
  // ==========================================
  async startQuiz() {
    this.sound.init();

    // Read config
    this.playerName = this.dom.playerNameInput.value.trim() || 'Player';
    localStorage.setItem('javaquiz_player_name', this.playerName);

    const catEl = document.querySelector('input[name="category"]:checked');
    this.category = catEl ? catEl.value : 'All';

    const timerEl = document.querySelector('input[name="timer-seconds"]:checked');
    this.timerSecondsLimit = timerEl ? parseInt(timerEl.value) : 15;

    const countEl = document.querySelector('input[name="question-count"]:checked');
    this.questionCount = countEl ? parseInt(countEl.value) : 10;

    // Reset scores & history
    this.currentIndex = 0;
    this.score = 0;
    this.correctCount = 0;
    this.wrongCount = 0;
    this.timeoutCount = 0;
    this.totalTimeTaken = 0;
    this.answerHistory = [];

    this.dom.liveScore.textContent = '0';
    this.dom.categoryBadge.textContent = this.category === 'All' ? 'ðŸŒŸ All Topics' : this.category;

    // Fetch questions from Java backend (or fallback)
    await this.fetchQuestions();

    if (this.questions.length === 0) {
      alert('No questions available for this category. Loading general pool.');
      this.questions = [...FALLBACK_QUESTIONS];
    }

    this.dom.totalQNum.textContent = this.questions.length;
    this.showScreen('quiz');
    this.renderQuestion();
  }

  async fetchQuestions() {
    try {
      const url = `/api/questions?category=${encodeURIComponent(this.category)}&limit=${this.questionCount}`;
      const res = await fetch(url);
      if (!res.ok) throw new Error('API response not ok');
      const data = await res.json();
      if (Array.isArray(data) && data.length > 0) {
        this.questions = data;
        return;
      }
    } catch (err) {
      console.warn('Backend API request failed, using local questions pool:', err.message);
    }

    // Fallback filtering
    let pool = [];
    let others = [];
    if (this.category !== 'All') {
      pool = FALLBACK_QUESTIONS.filter(q => q.category.toLowerCase() === this.category.toLowerCase());
      others = FALLBACK_QUESTIONS.filter(q => q.category.toLowerCase() !== this.category.toLowerCase());
    } else {
      pool = [...FALLBACK_QUESTIONS];
    }
    pool.sort(() => Math.random() - 0.5);
    others.sort(() => Math.random() - 0.5);

    // If pool has fewer than requested, fill up from others
    if (pool.length < this.questionCount) {
      for (const q of others) {
        if (pool.length >= this.questionCount) break;
        pool.push(q);
      }
    }
    this.questions = pool.slice(0, this.questionCount);
  }

  // ==========================================
  // RENDER QUESTION
  // ==========================================
  renderQuestion() {
    const q = this.questions[this.currentIndex];
    this.isAnswered = false;

    // Update trackers
    this.dom.currentQNum.textContent = this.currentIndex + 1;
    const progressPercent = ((this.currentIndex) / this.questions.length) * 100;
    this.dom.progressBar.style.width = `${progressPercent}%`;

    // Question text
    this.dom.questionText.textContent = q.question;

    // Hide explanation box & disable next button
    this.dom.explanationBox.classList.add('hidden');
    this.dom.nextBtn.disabled = true;
    const isLast = this.currentIndex === this.questions.length - 1;
    this.dom.nextBtnText.textContent = isLast ? 'Finish Quiz' : 'Next Question';

    // Render option buttons
    this.dom.optionsContainer.innerHTML = '';
    const letters = ['A', 'B', 'C', 'D'];

    q.options.forEach((optText, index) => {
      const btn = document.createElement('button');
      btn.className = 'option-btn';
      btn.dataset.index = index;

      btn.innerHTML = `
        <span class="option-letter">${letters[index]}</span>
        <span class="option-text">${this.escapeHtml(optText)}</span>
        <span class="option-status-icon"></span>
      `;

      btn.addEventListener('click', () => {
        if (!this.isAnswered) {
          this.selectOption(index);
        }
      });

      this.dom.optionsContainer.appendChild(btn);
    });

    // Start Seconds Timer
    this.startTimer();
  }

  // ==========================================
  // SECONDS TIMER ENGINE
  // ==========================================
  startTimer() {
    clearInterval(this.timerInterval);
    this.remainingSeconds = this.timerSecondsLimit;
    this.questionStartTime = Date.now();

    this.updateTimerUI(this.remainingSeconds);

    this.timerInterval = setInterval(() => {
      this.remainingSeconds--;

      if (this.remainingSeconds > 0) {
        if (this.remainingSeconds <= 3) {
          this.sound.playUrgentTick();
        } else {
          this.sound.playTick();
        }
        this.updateTimerUI(this.remainingSeconds);
      } else {
        // Time ran out!
        this.remainingSeconds = 0;
        this.updateTimerUI(0);
        clearInterval(this.timerInterval);
        this.onTimeout();
      }
    }, 1000);
  }

  updateTimerUI(seconds) {
    this.dom.timerDisplay.textContent = seconds;

    // Circle progress offset
    const fraction = seconds / this.timerSecondsLimit;
    const offset = this.timerCircumference * (1 - fraction);
    this.dom.timerCircle.style.strokeDasharray = this.timerCircumference;
    this.dom.timerCircle.style.strokeDashoffset = offset;

    // Visual classes based on remaining seconds
    this.dom.timerBox.classList.remove('warning', 'danger');
    if (seconds <= 3) {
      this.dom.timerBox.classList.add('danger');
    } else if (seconds <= Math.max(5, Math.floor(this.timerSecondsLimit * 0.4))) {
      this.dom.timerBox.classList.add('warning');
    }
  }

  stopTimer() {
    clearInterval(this.timerInterval);
    const elapsed = Math.round((Date.now() - this.questionStartTime) / 1000);
    return Math.min(this.timerSecondsLimit, Math.max(1, elapsed));
  }

  // ==========================================
  // OPTION SELECTION & EVALUATION
  // ==========================================
  selectOption(selectedIndex) {
    if (this.isAnswered) return;
    this.isAnswered = true;

    const timeSpent = this.stopTimer();
    this.totalTimeTaken += timeSpent;

    const q = this.questions[this.currentIndex];
    const isCorrect = (selectedIndex === q.correctIndex);

    // Disable all options
    const optionButtons = this.dom.optionsContainer.querySelectorAll('.option-btn');
    optionButtons.forEach(btn => btn.disabled = true);

    const selectedBtn = optionButtons[selectedIndex];
    const correctBtn = optionButtons[q.correctIndex];

    if (isCorrect) {
      this.score += 10;
      this.correctCount++;
      selectedBtn.classList.add('correct');
      selectedBtn.querySelector('.option-status-icon').textContent = 'âœ“';
      this.sound.playCorrect();
      this.showExplanation(true, q.explanation);
    } else {
      this.wrongCount++;
      selectedBtn.classList.add('wrong');
      selectedBtn.querySelector('.option-status-icon').textContent = 'âœ—';
      correctBtn.classList.add('correct');
      correctBtn.querySelector('.option-status-icon').textContent = 'âœ“';
      this.sound.playWrong();
      this.showExplanation(false, q.explanation);
    }

    this.dom.liveScore.textContent = this.score;

    // Save answer record for End Result review
    this.answerHistory.push({
      question: q,
      selectedIndex: selectedIndex,
      correctIndex: q.correctIndex,
      timeSpent: timeSpent,
      status: isCorrect ? 'correct' : 'wrong'
    });

    this.dom.nextBtn.disabled = false;
  }

  // ==========================================
  // TIMEOUT HANDLER
  // ==========================================
  onTimeout() {
    if (this.isAnswered) return;
    this.isAnswered = true;

    this.totalTimeTaken += this.timerSecondsLimit;
    this.timeoutCount++;
    this.sound.playTimeout();

    const q = this.questions[this.currentIndex];

    // Highlight correct answer
    const optionButtons = this.dom.optionsContainer.querySelectorAll('.option-btn');
    optionButtons.forEach(btn => btn.disabled = true);

    const correctBtn = optionButtons[q.correctIndex];
    if (correctBtn) {
      correctBtn.classList.add('correct');
      correctBtn.querySelector('.option-status-icon').textContent = 'âœ“';
    }

    this.showExplanation('timeout', q.explanation);

    // Save answer record
    this.answerHistory.push({
      question: q,
      selectedIndex: -1, // Timed out
      correctIndex: q.correctIndex,
      timeSpent: this.timerSecondsLimit,
      status: 'timeout'
    });

    this.dom.nextBtn.disabled = false;
  }

  showExplanation(state, text) {
    this.dom.explanationBox.classList.remove('hidden');
    if (state === true) {
      this.dom.explanationTitle.textContent = 'Correct Answer!';
      this.dom.explanationTitle.style.color = '#10b981';
    } else if (state === 'timeout') {
      this.dom.explanationTitle.textContent = "Time's Up! Correct Answer Shown Below:";
      this.dom.explanationTitle.style.color = '#f59e0b';
    } else {
      this.dom.explanationTitle.textContent = 'Incorrect. Correct Answer Shown Below:';
      this.dom.explanationTitle.style.color = '#ef4444';
    }
    this.dom.explanationText.textContent = text || 'No explanation provided.';
  }

  nextQuestion() {
    this.currentIndex++;
    if (this.currentIndex < this.questions.length) {
      this.renderQuestion();
    } else {
      this.showEndResults();
    }
  }

  // ==========================================
  // SCREEN 3: END RESULTS
  // ==========================================
  async showEndResults() {
    this.stopTimer();

    // Fill progress bar to 100%
    this.dom.progressBar.style.width = '100%';

    const total = this.questions.length;
    const percentage = Math.round((this.correctCount / total) * 100);
    const avgTime = Math.round((this.totalTimeTaken / total) * 10) / 10;

    // Send score to Java backend
    this.submitScoreToBackend(percentage);

    // Set greeting & title
    this.dom.resultPlayerName.textContent = this.playerName;

    if (percentage === 100) {
      this.dom.resultTrophy.textContent = 'ðŸ‘‘';
      this.dom.resultTitle.textContent = 'Flawless Victory!';
      this.dom.performanceTag.textContent = 'Grand Java Architect';
      this.dom.performanceFeedback.textContent = 'Outstanding! You achieved a 100% perfect score under high time pressure!';
      this.confetti.launch();
      this.sound.playFanfare();
    } else if (percentage >= 80) {
      this.dom.resultTrophy.textContent = 'ðŸ†';
      this.dom.resultTitle.textContent = 'Master Level!';
      this.dom.performanceTag.textContent = 'Senior Java Developer';
      this.dom.performanceFeedback.textContent = 'Phenomenal knowledge and speed! You mastered nearly every question!';
      this.confetti.launch();
      this.sound.playFanfare();
    } else if (percentage >= 60) {
      this.dom.resultTrophy.textContent = 'â­';
      this.dom.resultTitle.textContent = 'Great Effort!';
      this.dom.performanceTag.textContent = 'Proficient Coder';
      this.dom.performanceFeedback.textContent = 'Good job! Review the questions you missed to polish your understanding.';
      this.sound.playFanfare();
    } else {
      this.dom.resultTrophy.textContent = 'ðŸŒ±';
      this.dom.resultTitle.textContent = 'Keep Learning!';
      this.dom.performanceTag.textContent = 'Junior Apprentice';
      this.dom.performanceFeedback.textContent = 'Practice makes perfect! Check the explanations below and try another round.';
    }

    // Stats Grid
    this.dom.statScore.textContent = `${this.score} pts`;
    this.dom.statCorrect.textContent = `${this.correctCount} / ${total}`;
    this.dom.statWrong.textContent = `${this.wrongCount}`;
    this.dom.statTimeout.textContent = `${this.timeoutCount}`;
    this.dom.statTotalTime.textContent = this.formatTime(this.totalTimeTaken);
    this.dom.statAvgTime.textContent = `${avgTime}s`;

    // Render detailed question review
    this.renderReviewList();

    // Show result screen
    this.showScreen('result');

    // Animate percentage circle
    this.animateResultCircle(percentage);
  }

  animateResultCircle(targetPercent) {
    this.dom.resultCircleFg.style.strokeDasharray = this.resultCircumference;
    this.dom.resultCircleFg.style.strokeDashoffset = this.resultCircumference;

    // Color based on performance
    if (targetPercent >= 80) {
      this.dom.resultCircleFg.style.stroke = '#10b981';
    } else if (targetPercent >= 50) {
      this.dom.resultCircleFg.style.stroke = '#6366f1';
    } else {
      this.dom.resultCircleFg.style.stroke = '#f59e0b';
    }

    setTimeout(() => {
      const offset = this.resultCircumference * (1 - targetPercent / 100);
      this.dom.resultCircleFg.style.strokeDashoffset = offset;
    }, 150);

    // Number count-up
    let current = 0;
    const duration = 1200;
    const stepTime = 20;
    const step = targetPercent / (duration / stepTime);

    const countUp = setInterval(() => {
      current += step;
      if (current >= targetPercent) {
        current = targetPercent;
        clearInterval(countUp);
      }
      this.dom.resultPercentDisplay.textContent = `${Math.round(current)}%`;
    }, stepTime);
  }

  // ==========================================
  // REVIEW LIST
  // ==========================================
  renderReviewList() {
    this.dom.reviewContainer.classList.add('hidden');
    this.dom.toggleReviewBtn.querySelector('span').textContent = 'ðŸ“ Review Answers';
    this.dom.reviewList.innerHTML = '';

    const letters = ['A', 'B', 'C', 'D'];

    this.answerHistory.forEach((item, idx) => {
      const div = document.createElement('div');
      div.className = `review-item ${item.status}`;

      let badgeHtml = '';
      if (item.status === 'correct') {
        badgeHtml = '<span class="review-badge correct">âœ“ Correct</span>';
      } else if (item.status === 'wrong') {
        badgeHtml = '<span class="review-badge wrong">âœ— Incorrect</span>';
      } else {
        badgeHtml = '<span class="review-badge timeout">â° Timed Out</span>';
      }

      const userChoice = item.selectedIndex >= 0 ? `${letters[item.selectedIndex]}. ${item.question.options[item.selectedIndex]}` : 'None (Time expired)';
      const correctChoice = `${letters[item.correctIndex]}. ${item.question.options[item.correctIndex]}`;

      div.innerHTML = `
        <div class="review-top">
          <span class="review-q-num">Question ${idx + 1} â€¢ ${item.timeSpent}s</span>
          ${badgeHtml}
        </div>
        <div class="review-question">${idx + 1}. ${this.escapeHtml(item.question.question)}</div>
        <div class="review-answers">
          <div class="review-answer-row">
            <strong>Your Answer:</strong>
            <span style="color: ${item.status === 'correct' ? '#10b981' : '#ef4444'}">${this.escapeHtml(userChoice)}</span>
          </div>
          <div class="review-answer-row">
            <strong>Correct Answer:</strong>
            <span style="color: #10b981">${this.escapeHtml(correctChoice)}</span>
          </div>
        </div>
        <div class="review-exp"><strong>Explanation:</strong> ${this.escapeHtml(item.question.explanation)}</div>
      `;

      this.dom.reviewList.appendChild(div);
    });
  }

  // ==========================================
  // BACKEND SUBMIT & LEADERBOARD
  // ==========================================
  async submitScoreToBackend(percentage) {
    try {
      const payload = {
        playerName: this.playerName,
        category: this.category,
        score: this.score,
        correctAnswers: this.correctCount,
        totalQuestions: this.questions.length,
        timeTakenSeconds: this.totalTimeTaken
      };

      await fetch('/api/submit', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });
    } catch (e) {
      console.warn('Could not submit score to Java server:', e.message);
    }
  }

  async openLeaderboard() {
    this.dom.leaderboardModal.classList.remove('hidden');
    await this.fetchLeaderboard();
  }

  closeLeaderboard() {
    this.dom.leaderboardModal.classList.add('hidden');
  }

  async fetchLeaderboard() {
    this.dom.leaderboardRows.innerHTML = '<tr><td colspan="5" style="text-align:center">Loading scores...</td></tr>';
    try {
      const res = await fetch('/api/leaderboard');
      if (!res.ok) throw new Error('Failed to load');
      const data = await res.json();
      this.renderLeaderboardTable(data);
    } catch (e) {
      // Mock data fallback if standalone
      this.renderLeaderboardTable([
        { playerName: 'Alex Dev', category: 'Java Basics', score: 100, totalQuestions: 10, percentage: 100, timeTakenSeconds: 58 },
        { playerName: 'Sophia Code', category: 'OOP Concepts', score: 90, totalQuestions: 10, percentage: 90, timeTakenSeconds: 64 },
        { playerName: 'James Byte', category: 'Collections', score: 80, totalQuestions: 10, percentage: 80, timeTakenSeconds: 72 }
      ]);
    }
  }

  renderLeaderboardTable(records) {
    if (!records || records.length === 0) {
      this.dom.leaderboardRows.innerHTML = '<tr><td colspan="5" style="text-align:center">No records yet. Be the first!</td></tr>';
      return;
    }

    const medals = ['ðŸ¥‡', 'ðŸ¥ˆ', 'ðŸ¥‰'];
    this.dom.leaderboardRows.innerHTML = records.map((rec, i) => `
      <tr>
        <td><strong>${medals[i] || (i + 1)}</strong></td>
        <td><strong>${this.escapeHtml(rec.playerName)}</strong></td>
        <td><span class="badge" style="font-size:0.7rem; padding: 2px 8px;">${this.escapeHtml(rec.category)}</span></td>
        <td><strong style="color: #06b6d4">${rec.score} pts</strong> (${rec.percentage}%)</td>
        <td>${this.formatTime(rec.timeTakenSeconds)}</td>
      </tr>
    `).join('');
  }

  // ==========================================
  // UTILITIES
  // ==========================================
  copyResultToClipboard() {
    const accuracy = Math.round((this.correctCount / this.questions.length) * 100);
    const text = `ðŸ† JavaQuiz Master Result:
ðŸ‘¤ Player: ${this.playerName}
ðŸ“š Category: ${this.category}
â­ Score: ${this.score} pts (${this.correctCount}/${this.questions.length})
ðŸŽ¯ Accuracy: ${accuracy}%
â±ï¸ Total Time: ${this.formatTime(this.totalTimeTaken)}
âš¡ Played on Java Quiz Web App`;

    navigator.clipboard.writeText(text).then(() => {
      this.showToast('ðŸ“‹ Results copied to clipboard!');
    }).catch(() => {
      this.showToast('Failed to copy to clipboard.');
    });
  }

  showToast(msg) {
    this.dom.toast.textContent = msg;
    this.dom.toast.classList.remove('hidden');
    setTimeout(() => {
      this.dom.toast.classList.add('hidden');
    }, 2500);
  }

  formatTime(totalSeconds) {
    const m = Math.floor(totalSeconds / 60);
    const s = totalSeconds % 60;
    if (m > 0) return `${m}m ${s}s`;
    return `${s}s`;
  }

  escapeHtml(str) {
    if (!str) return '';
    return str
      .replace(/&/g, '&amp;')
      .replace(/</g, '&lt;')
      .replace(/>/g, '&gt;')
      .replace(/"/g, '&quot;')
      .replace(/'/g, '&#039;');
  }
}

// Instantiate on DOM ready
document.addEventListener('DOMContentLoaded', () => {
  window.quizApp = new QuizGame();
});

