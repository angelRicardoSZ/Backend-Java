import javax.print.Doc;

import static ui.UiMenu.*;

public class Main {
    public static void main(String[] args) {


        //showMenu();

        Doctor myDoctor = new Doctor("Angel","specialliy 1");

        System.out.println(myDoctor.name);

        System.out.println(myDoctor.speciality);

        Patient patient = new Patient("Patient 1", "w@hotmail.com");

        System.out.println(patient.name);

        System.out.println(patient.email);





    }
}
