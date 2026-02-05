package com.ll.wiseSaying.storage;

import com.ll.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public interface WiseSayingStorage {
    ArrayList<WiseSaying> loadAll();
    void saveAll(List<WiseSaying> wiseList);
}
