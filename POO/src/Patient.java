public class Patient {
    String name;
    String email;
    String address;
    String phoneNumber;
    String birthday;
    Double weight;
    Double height;
    String blood;

    Patient(String name, String email){
        System.out.println("El nombre del paciente es: " + name);
        this.name = name;
        this.email = email;
    }

}
