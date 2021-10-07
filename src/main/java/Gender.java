public enum Gender {
    MALE("Мужчина"), FEMALE("Женщина");

    private String title;

    Gender(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
