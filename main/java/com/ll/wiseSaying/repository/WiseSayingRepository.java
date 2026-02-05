package com.ll.wiseSaying.repository;

import com.ll.wiseSaying.entity.WiseSaying;
import com.ll.wiseSaying.storage.FileWiseSayingStorage;
import com.ll.wiseSaying.storage.WiseSayingStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private static WiseSayingRepository instance;
//    private final WiseSayingToJsonAdapter adapter;
    private final WiseSayingStorage storage;
    private final List<WiseSaying> wisesList;

    // 기본 생성자 (프로덕션)
    private WiseSayingRepository() {
//        this.adapter = WiseSayingToJsonAdapter.getInstance();
//        this.wisesList = this.adapter.readFromFile();
        this.storage = new FileWiseSayingStorage();
        this.wisesList = this.storage.loadAll();
    }

    // 테스트용 생성자
    private WiseSayingRepository(WiseSayingStorage storage) {
        this.storage = storage;
        this.wisesList = storage.loadAll();
    }

    public static WiseSayingRepository getInstance() {
        if (instance == null) {
            instance = new WiseSayingRepository();
        }
        return instance;
    }

    // 테스트 전용: 싱글턴 재초기화 + 메모리 스토리지 주입
    public static void resetForTesting(WiseSayingStorage storage) {
        instance = new WiseSayingRepository(storage);
    }

    public void build() {
        this.storage.saveAll(this.wisesList);
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
        p.setModifyDate(LocalDateTime.now());
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

    public void clear() {
        this.wisesList.clear();
        WiseSaying.setClear();
        this.build();
    }
}
