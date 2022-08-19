import java.util.function.Function;
import java.util.function.Predicate;

public class MathFunctions {
    public static void main(String[] args) {

        // First syntax
        Function<Integer, Integer> square = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x * x ;
            }
        };

        System.out.println(square.apply(5));

        // Second syntax
        Function<Integer, Boolean> isOdd = x -> (x % 2 == 1);


        // predicate

        Predicate<Integer> isEven = x -> x % 2 == 0;

        System.out.println(isEven.test(4));;

        Predicate<Student> isApproved = student -> student.getCalificacion() >= 6.0;

        // creating student
        Student angel = new Student(3.5);

        System.out.println(isApproved.test(angel));

    }

    static class Student {

        private double calificacion;

        public Student(double calificacion){
            this.calificacion = calificacion;
        }

        public double getCalificacion() {
            return calificacion;
        }
    }
}
