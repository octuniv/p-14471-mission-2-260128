public class WiseSaying {
    int seq;
    String wise;
    String author;
    boolean deleted;
    static int nextSeq = 1;

    public WiseSaying(String wise, String author) {
        this.seq= nextSeq;
        this.wise = wise;
        this.author = author;
        this.deleted = false;
        nextSeq++;
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

    public int getSeq() {
        return this.seq;
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

    public static int getNextSeq() {
        return nextSeq;
    }

    public String toString() {
        if (this.deleted) {
            return this.seq + "번 명언은 존재하지 않습니다.";
        }
        return this.seq +
                " / " +
                this.author +
                " / " +
                this.wise;
    }
}
