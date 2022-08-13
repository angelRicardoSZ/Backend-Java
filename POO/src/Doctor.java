import java.util.ArrayList;
import java.util.Date;
public class Doctor extends User {


    String speciality;

    Doctor(String name, String email) {
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
    //    System.out.println("ID Doctor: " + id);
    //}

    // array list of type objects AvailableAppointment
    ArrayList<AvailableAppointment> availableAppointments = new ArrayList<AvailableAppointment>();

    // method to add appointments
    public void addAvailableAppointment(Date date, String time) {
        availableAppointments.add(new AvailableAppointment(date,time));
    }

    // method to get the available appointments
    public ArrayList<AvailableAppointment> getAvailableAppointments(){
        return availableAppointments;
    }

    // nested class: create a new appointment
    public static class AvailableAppointment {
        private int id;
        private Date date;
        private String time;

        // constructor method
        public AvailableAppointment(Date date, String time) {
            this.date = date;
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getDate() {
            return date;
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
    }
}
