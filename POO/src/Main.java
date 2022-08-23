import model.Doctor;
import model.Patient;
import model.User;

import java.util.Date;

public class Main {
    public static void main(String[] args) {


        //showMenu();


        // instance of a Doctor
        Doctor myDoctor = new Doctor("Angel","doctor@hotmail.com");

        // Get details

        //System.out.println(myDoctor.getName());

        //System.out.println(myDoctor.getEmail());


        // Instance of patient
        Patient patient = new Patient("model.Patient 1", "w@hotmail.com");

        patient.setWeight(54.6);

        //System.out.println(patient.getWeight());

        patient.setPhoneNumber("12345678");

        //System.out.println(patient.getPhoneNumber());





        /*
        instances of patients:

        Patient patient3 = new Patient("model.Patient 3", "w3@hotmail.com");

        Patient patient4 = new Patient("model.Patient 4", "w4@hotmail.com");

        System.out.println("model.Patient 3");

        System.out.println(patient3);
        System.out.println(patient3.getName());

        System.out.println("model.Patient 4");

        System.out.println(patient4);
        System.out.println(patient4.getName());

        patient3 = patient4;

        System.out.println("model.Patient 3 edited");

        System.out.println(patient3);
        System.out.println(patient3.getName());

        System.out.println("model.Patient 4 edited");

        System.out.println(patient4);
        System.out.println(patient4.getName());

        patient4.setName("model.Patient 4 new name");
        System.out.println("model.Patient 4 name edited");
        System.out.println(patient3.getName());
        System.out.println(patient4.getName());*/

        // Add appointments:
        System.out.println("add appointments");

        myDoctor.addAvailableAppointment(new Date(), "4 pm");
        myDoctor.addAvailableAppointment(new Date(), "10 am");
        myDoctor.addAvailableAppointment(new Date(), "1 pm");

        //System.out.println(myDoctor);

        // tow forms to get the availble appointments:
        System.out.println(myDoctor.getAvailableAppointments());

        // for-each
        for (Doctor.AvailableAppointment availableAppointment: myDoctor.availableAppointments) {
            System.out.println("List of available appointments "+availableAppointment.getDate() + " - " + availableAppointment.getTime());
        }

        System.out.println(patient);



    }
}
