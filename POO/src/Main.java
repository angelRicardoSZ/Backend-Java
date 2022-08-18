import javax.print.Doc;

import java.util.Date;

import static ui.UiMenu.*;

public class Main {
    public static void main(String[] args) {


        //showMenu();

        Doctor myDoctor = new Doctor("Angel","doctor@hotmail.com");

        System.out.println(myDoctor.getName());

        System.out.println(myDoctor.getEmail());

        Patient patient = new Patient("Patient 1", "w@hotmail.com");

        patient.setWeight(54.6);

        System.out.println(patient.getWeight());

        patient.setPhoneNumber("12345678");

        System.out.println(patient.getPhoneNumber());

        //int i = 0;

        //int b = 2;

        //b = i;

        // b = 0

        Patient patient3 = new Patient("Patient 3", "w3@hotmail.com");

        Patient patient4 = new Patient("Patient 4", "w4@hotmail.com");

        System.out.println("Patient 3");

        System.out.println(patient3);
        System.out.println(patient3.getName());

        System.out.println("Patient 4");

        System.out.println(patient4);
        System.out.println(patient4.getName());

        patient3 = patient4;

        System.out.println("Patient 3 edited");

        System.out.println(patient3);
        System.out.println(patient3.getName());

        System.out.println("Patient 4 edited");

        System.out.println(patient4);
        System.out.println(patient4.getName());

        patient4.setName("Patient 4 new name");
        System.out.println("Patient 4 name edited");
        System.out.println(patient3.getName());
        System.out.println(patient4.getName());

        System.out.println("add appointments");

        myDoctor.addAvailableAppointment(new Date(), "4 pm");
        myDoctor.addAvailableAppointment(new Date(), "10 am");
        myDoctor.addAvailableAppointment(new Date(), "1 pm");

        System.out.println(myDoctor);

        System.out.println(myDoctor.getAvailableAppointments());

        // for-each
        for (Doctor.AvailableAppointment availableAppointment: myDoctor.availableAppointments) {
            System.out.println("List of available appointments "+availableAppointment.getDate() + " - " + availableAppointment.getTime());
        }
        System.out.println(patient);
    }
}
