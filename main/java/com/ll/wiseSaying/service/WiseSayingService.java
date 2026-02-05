package com.ll.wiseSaying.service;

import com.ll.wiseSaying.entity.WiseSaying;
import com.ll.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {
    private static WiseSayingService instance;
    private final WiseSayingRepository repository;

    private WiseSayingService() {
        this.repository = WiseSayingRepository.getInstance();
    }

    public static WiseSayingService getInstance() {
        if (instance == null) {
            instance = new WiseSayingService();
        }
        return instance;
    }

    public void build() {
        this.repository.build();
    }

    public WiseSaying register(String wise, String author) {
        return repository.add(wise, author);
    }

    public List<WiseSaying> getAllReversed() {
        return this.repository.getAll().reversed();
    }

    public boolean checkById(int seq) {
        return this.repository.isExist(seq);
    }

    public WiseSaying getItem(int seq) {
        return this.repository.get(seq);
    }

    public boolean remove(int seq) {
        return this.repository.remove(seq);
    }

    public boolean amend(int seq, String wise, String author) {
        return this.repository.amend(seq, wise, author);
    }

    public void clear() {
        this.repository.clear();
    }
}
