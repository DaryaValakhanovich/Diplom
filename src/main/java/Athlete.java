import java.time.LocalDate;
import java.util.ArrayList;

public class Athlete {
    private long id;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthday;
    private Gender gender;
    private String sport;
    private DominantHand dominantHand;
    private String qualification;
    private ArrayList<String> csvFiles;

    public Athlete() {
        csvFiles = new ArrayList<>();
    }

    public Athlete(String name, String surname, String patronymic, LocalDate birthday,
                   Gender gender, String sport, DominantHand dominantHand, String qualification) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.gender = gender;
        this.sport = sport;
        this.dominantHand = dominantHand;
        this.qualification = qualification;
        csvFiles = new ArrayList<>();
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public ArrayList<String> getCsvFiles() {
        return csvFiles;
    }

    public void setCsvFiles(ArrayList<String> csvFiles) {
        this.csvFiles = csvFiles;
    }

    public void addCsvFile(String file){
        csvFiles.add(file);
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", sport='" + sport + '\'' +
                ", dominantHand=" + dominantHand +
                ", qualification='" + qualification + '\'' +
                ", csvFiles=" + csvFiles +
                '}';
    }
}
