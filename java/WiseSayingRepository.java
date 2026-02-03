import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private static WiseSayingRepository instance;
    private final WiseSayingToJsonAdapter adapter;
    private final List<WiseSaying> wisesList;

    private WiseSayingRepository() {
        this.adapter = WiseSayingToJsonAdapter.getInstance();
        this.wisesList = this.adapter.readFromFile();
    }

    public static WiseSayingRepository getInstance() {
        if (instance == null) {
            instance = new WiseSayingRepository();
        }
        return instance;
    }

    public void build() {
        try {
            this.adapter.writeToFile(this.wisesList);
        } catch (IOException e) {
            e.printStackTrace();
//            System.exit(-1);
        }
    }

    public WiseSaying add(String wise, String author) {
        WiseSaying wiseItem = new WiseSaying(wise, author);
        this.wisesList.add(wiseItem);
        this.build();
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
        this.build();
        return true;
    }

    public boolean amend(int seq, String wise, String author) {
        if (!this.isExist(seq)) {
            return false;
        }

        WiseSaying p = this.get(seq);
        p.setWise(wise);
        p.setAuthor(author);
        this.build();
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
