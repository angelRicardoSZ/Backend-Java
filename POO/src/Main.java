import static ui.UiMenu.*;

public class Main {
    public static void main(String[] args) {

        Doctor myDoctor = new Doctor();

        myDoctor.name = "Angel";

        myDoctor.showName();

        myDoctor.showId();

        System.out.println(Doctor.id);

        Doctor myDoctorAngel = new Doctor();

        myDoctorAngel.showId();

        System.out.println(Doctor.id);


        showMenu();



    }
}
