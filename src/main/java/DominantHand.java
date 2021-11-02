public enum DominantHand {
    RIGHT("RIGHT"),  LEFT("LEFT");

    private String title;

    DominantHand(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
