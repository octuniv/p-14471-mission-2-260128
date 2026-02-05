package com.ll.storage;

import com.ll.wiseSaying.entity.WiseSaying;
import com.ll.wiseSaying.storage.WiseSayingStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoryWiseSayingStorage implements WiseSayingStorage {
    private final List<WiseSaying> data = new ArrayList<>();

    public MemoryWiseSayingStorage(WiseSaying... initialItems) {
        data.addAll(Arrays.asList(initialItems));
    }

    @Override
    public ArrayList<WiseSaying> loadAll() {
        return new ArrayList<>(data);
    }

    @Override
    public void saveAll(List<WiseSaying> items) {
        data.clear();
        data.addAll(items);
    }
}
