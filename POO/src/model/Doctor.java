package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class Doctor extends User {


    String speciality;

    public Doctor(String name, String email) {
        super(name, email);
        System.out.println("El nombre del doctor asignado es: " + name);
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }



    // comportamiento
    //public void showName(){
    //    System.out.println(name);
    //}

    //public void showId(){
    //    System.out.println("ID model.Doctor: " + id);
    //}

    // array list of type objects AvailableAppointment
    public ArrayList<AvailableAppointment> availableAppointments = new ArrayList<AvailableAppointment>();

    // method to add appointments
    public void addAvailableAppointment(String date, String time) {

        availableAppointments.add(new AvailableAppointment(date,time));
    }

    // method to get the available appointments
    public ArrayList<AvailableAppointment> getAvailableAppointments(){
        return availableAppointments;
    }

    @Override
    public String toString() {
        return super.toString() + "model.Doctor{" +
                "speciality='" + speciality + '\'' +
                ", availableAppointments=" + availableAppointments.toString() +
                '}';
    }

    @Override
    public void showDataUser() {
        System.out.println("Empleado del Hospital: 1");
        System.out.println("Departamento: 1");
    }

    // nested class: create a new appointment
    public static class AvailableAppointment {
        private int id;
        private Date date;
        private String time;
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        // constructor method
        public AvailableAppointment(String date, String time) {

            try {
                this.date = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDate(String DATE) {
            return format.format(date);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "AvailableAppointment{" +
                    "id=" + id +
                    ", date=" + date +
                    ", time='" + time + '\'' +
                    '}';
        }
    }
}
