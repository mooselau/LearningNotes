package annotation;

public class AnnotationDemo {
    public static void main(String[] args) {
        AnnotationDemo demo = new AnnotationDemo();
        demo.entrypoint();
    }

    private void entrypoint() {
        Person person = new Person("Peter", 28, "LJZ");
        ObjectToJsonConverter converter = new ObjectToJsonConverter();
        String jsonString = converter.convertToJson(person);
        System.out.println(jsonString);
    }
}
