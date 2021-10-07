public enum Qualification {
M("МСИУ"), MC("МС"), d("КМС"), k("1 разряд"), kl("2 разряд"),
    kfl("3 разряд"), kgl("1 юн.разряд"), kkgl("2 юн.разряд"),krhl("без разряда");

    private String title;

    Qualification(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
