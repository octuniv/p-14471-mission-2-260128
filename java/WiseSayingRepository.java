import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private static WiseSayingRepository instance;
    private final List<WiseSaying> wisesList;

    private WiseSayingRepository() {
        this.wisesList = new ArrayList<>();
    }

    public static WiseSayingRepository getInstance() {
        if (instance == null) {
            instance = new WiseSayingRepository();
        }
        return instance;
    }

    public WiseSaying add(String wise, String author) {
        WiseSaying wiseItem = new WiseSaying(wise, author);
        this.wisesList.add(wiseItem);
        return wiseItem;
    }

    public List<WiseSaying> getAll() {
        List<WiseSaying> ret = new ArrayList<>();
        for (WiseSaying w : this.wisesList) {
            if (w.getDeleted()) continue;
            ret.add(w);
        }
        return ret;
    }

    public boolean remove(int seq) {
        if (!this.isExist(seq)) {
            return false;
        }

        this.get(seq).setDeleted();
        return true;
    }

    public boolean amend(int seq, String wise, String author) {
        if (!this.isExist(seq)) {
            return false;
        }

        WiseSaying p = this.get(seq);
        p.setWise(wise);
        p.setAuthor(author);
        return true;
    }

    public boolean isExist(int seq) {
        return this.wisesList.size() >= seq && !this.wisesList.get(seq - 1).getDeleted();
    }

    public WiseSaying get(int seq) {
        if (!this.isExist((seq))) return null;
        return this.wisesList.get(seq - 1);
    }


}
