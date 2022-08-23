package model;

public class Patient extends User{
    private String birthday;
    private Double weight;
    private Double height;
    String blood;

    public Patient(String name, String email){
        super( name, email);
        //System.out.println("El nombre del paciente es: " + name);

    }

    @Override
    public void showDataUser() {
        System.out.println("Paciente del hospital: 1");
    }

    public String getWeight(){
        return this.weight + " Kg.";
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    public String getHeight() {
        return height + "Mts.";
    }

    public void setHeight(double height) {
        this.height = height;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }
}
