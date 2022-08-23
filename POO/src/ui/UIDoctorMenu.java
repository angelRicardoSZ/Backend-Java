package ui;

import java.util.Scanner;

public class UIDoctorMenu {
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
                System.out.println(j + ". " + UiMenu.MONTHS[j]);
            }
            System.out.println("0. Return ");
            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            // response validation
            if(response >0  && response <4)
            {
                int monthSelected = response;
                System.out.println("The month selected is: ");
                System.out.println(monthSelected + ". " + UiMenu.MONTHS[monthSelected]);

                System.out.println("Insert the date available: [dd/mm/yyyy] ");
                String date = sc.nextLine();
                System.out.println("The date selected is: ");
                System.out.println(date + "\n1. Correct \n2. Change date");




            }
        } while (response!=0);
    }
}
