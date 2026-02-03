public class WiseSaying {
    private int id;
    private String wise;
    private String author;
    private boolean deleted;
    private static int nextId = 1;

    public WiseSaying(String wise, String author) {
        this.id = nextId;
        this.wise = wise;
        this.author = author;
        this.deleted = false;
        nextId++;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWise(String wise) {
        this.wise = wise;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDeleted() {
        this.deleted = true;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public static void undoNextId() { nextId--; }

    public int getId() {
        return this.id;
    }

    public String getWise() {
        return this.wise;
    }

    public String getAuthor() {
        return this.author;
    }

    public boolean getDeleted() {
        return this.deleted;
    }

    public static int getNextId() {
        return nextId;
    }

    public String toString() {
        if (this.deleted) {
            return this.id + "번 명언은 존재하지 않습니다.";
        }
        return this.id +
                " / " +
                this.author +
                " / " +
                this.wise;
    }
}
