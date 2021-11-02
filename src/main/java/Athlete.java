

import java.time.LocalDate;
import java.util.ArrayList;

public class Athlete {

    private long id;
    private String name;
    private String surname;
    private String patronymic;
    private int age;
    private Gender gender;
    private String sport;
    private DominantHand dominantHand;
    private Qualification qualification;
    private ArrayList<String> csvFiles;

    public Athlete() {
        csvFiles = new ArrayList<>();
    }

    public Athlete(String name, String surname, String patronymic, int age,
                   Gender gender, String sport, DominantHand dominantHand, Qualification qualification) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.gender = gender;
        this.sport = sport;
        this.dominantHand = dominantHand;
        this.qualification = qualification;
        csvFiles = new ArrayList<>();
    }

    public void addCsvFile(String file){
        csvFiles.add(file);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public DominantHand getDominantHand() {
        return dominantHand;
    }

    public void setDominantHand(DominantHand dominantHand) {
        this.dominantHand = dominantHand;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public ArrayList<String> getCsvFiles() {
        return csvFiles;
    }

    public void setCsvFiles(ArrayList<String> csvFiles) {
        this.csvFiles = csvFiles;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", sport='" + sport + '\'' +
                ", dominantHand=" + dominantHand +
                ", qualification=" + qualification +
                ", csvFiles=" + csvFiles +
                '}';
    }
}

