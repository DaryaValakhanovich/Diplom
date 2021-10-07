public enum DominantHand {
    RIGHT("Правша"),  LEFT("Левша");

    private String title;

    DominantHand(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
