package com.ll.wiseSaying.storage;

import com.ll.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class FileWiseSayingStorage implements WiseSayingStorage {
    private final WiseSayingToJsonAdapter adapter = WiseSayingToJsonAdapter.getInstance();

    @Override
    public ArrayList<WiseSaying> loadAll() {
        return adapter.readFromFile(); // 기존 로직 그대로
    }

    @Override
    public void saveAll(List<WiseSaying> items) {
        adapter.writeToFile(items);
    }
}
