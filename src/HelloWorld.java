public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, Jenkins! This is my first scripted pipeline.");
        System.out.println("Pipeline executed successfully on: " + java.time.LocalDateTime.now());
    }
    
    public String getMessage() {
        return "Hello, Jenkins!";
    }
}
