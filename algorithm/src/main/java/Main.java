/**
 * @author zhengdayue
 */
public class Main {

    public static void main(String[] args) {
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
        System.out.println(f1 == f2);
        System.out.println(f3 == f4);

        Student student = new Student(10);
        chage(student);
        System.out.println(student.age);
    }

    public static void chage(Student student) {
        student.setAge(100);
    }
}

class Student{
    Integer age;
    public Student(Integer age) {
        this.age = age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
