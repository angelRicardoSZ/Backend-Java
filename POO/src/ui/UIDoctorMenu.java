package ui;

import model.Doctor;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Scanner;

public class UIDoctorMenu {

    public static ArrayList<Doctor> doctorsAvailableAppointments = new ArrayList<>();

    public static void showDoctorMenu()
    {
        int response = 0;
        do {
            System.out.println("\n\n");
            System.out.println("Doctor");
            System.out.println("Welcome " + UiMenu.doctorLogged.getName());
            System.out.println("1. Add Available Appointment");
            System.out.println("2. My scheduled appointments");
            System.out.println("3. Logout");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());
            switch (response) {
                case 1:
                    showAddAvailableAppointmentsMenu();
                    break;

                case 2:
                    break;

                case 3:
                    UiMenu.showMenu();
                    break;

            }

        } while (response != 0);
    }

    private static void showAddAvailableAppointmentsMenu(){
        int response = 0;
        do {
            System.out.println("________");
            System.out.println(":: Add available appointment");
            System.out.println(":: Select a month  ");
            for (int i = 0; i < 3; i++) {
                int j = i +1;
                System.out.println(j + ". " + UiMenu.MONTHS[i]);
            }
            System.out.println("0. Return ");
            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            // if the user select a valid month:
            if(response >0  && response <4)
            {
                int monthSelected = response;

                // month validation by the user
                System.out.println("The month selected is: ");
                System.out.println(monthSelected + ". " + UiMenu.MONTHS[monthSelected-1]);

                // date validation by the user
                System.out.println("Insert the date available: [dd/mm/yyyy] ");
                String date = sc.nextLine();
                System.out.println("The date selected is: ");
                System.out.println(date + "\n1. Correct \n2. Change date");
                int responseDate = Integer.valueOf(sc.nextLine());
                if( responseDate ==2 ) continue;

                int responseTime = 0;
                String time = "";
                do {
                    System.out.println("Insert the time available for date: "+ date+ "[16:00]");
                    time= sc.nextLine();
                    System.out.println("The time selected is: ");
                    System.out.println(time + "\n1. Correct \n2. Change time");
                    responseTime = Integer.valueOf(sc.nextLine());

                } while (responseTime == 2);
                UiMenu.doctorLogged.addAvailableAppointment(date, time);
                checkDoctorAvailableAppointments(UiMenu.doctorLogged);


            }
        } while (response!=0);
    }

    private static void checkDoctorAvailableAppointments(Doctor doctor)
    {
        if(doctor.getAvailableAppointments().size() > 0
        && !doctorsAvailableAppointments.contains(doctor))
        {
            doctorsAvailableAppointments.add(doctor);
        }
    }
}
