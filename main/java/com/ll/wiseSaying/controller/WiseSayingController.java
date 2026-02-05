package com.ll.wiseSaying.controller;

import com.ll.global.InputReader;
import com.ll.global.OutputWriter;
import com.ll.global.WiseSayingRequester;
import com.ll.wiseSaying.entity.WiseSaying;
import com.ll.wiseSaying.service.WiseSayingService;

import java.util.List;

public class WiseSayingController {
    private static WiseSayingController instance;
    private final WiseSayingService service;
    private final InputReader reader;
    private final OutputWriter writer;
    private String wise;
    private String author;

    private WiseSayingController(InputReader reader, OutputWriter writer) {
        this.service = WiseSayingService.getInstance();
        this.reader = reader;
        this.writer = writer;
    }

    public static WiseSayingController getInstance(InputReader reader, OutputWriter writer) {
        if (instance == null) {
            instance = new WiseSayingController(reader, writer);
        }

        return instance;
    }

    // 테스트 전용: 인스턴스 강제 초기화
    public static void resetForTesting() {
        instance = null;
    }

    public void register() {
        writer.print("명언 : ");
        wise = reader.readLine();
        writer.print("작가 : ");
        author = reader.readLine();
        WiseSaying newItem = this.service.register(wise, author);
        writer.println(newItem.getId() + "번 명언이 등록되었습니다.");
    }

    public void inquiry() {
        List<WiseSaying> wiseItems = this.service.getAllReversed();
        writer.println("번호 / 작가 / 명언 / 작성일 / 수정일");
        writer.println("----------------------");
        for (WiseSaying w : wiseItems) {
            writer.println(w.toString());
        }
    }

    public void remove(WiseSayingRequester rq) {
        int id = rq.getParamAsInt("id", -1);
        if(id == -1) {
            writer.println("id를 제대로 입력해주세요.");
            return;
        }
        if (!this.service.checkById(id)) {
            writer.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        boolean result = this.service.remove(id);
        if (result) writer.println(id + "번 명언이 삭제되었습니다.");
        else writer.println(id + "번 명언이 삭제 중 오류 발생");
    }

    public void amend(WiseSayingRequester rq) {
        int id = rq.getParamAsInt("id", -1);
        if(id == -1) {
            writer.println("id를 제대로 입력해주세요.");
            return;
        }
        if (!this.service.checkById(id)) {
            writer.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        WiseSaying amendItem = this.service.getItem(id);

        writer.println("명언(기존) : " + amendItem.getWise());
        writer.print("명언 : ");
        wise = reader.readLine();
        writer.println("작가(기존) : " + amendItem.getAuthor());
        writer.print("작가 : ");
        author = reader.readLine();

        boolean result = this.service.amend(id, wise, author);
        if (!result) writer.println("수정 중 오류");
    }

    public void build() {
        this.service.build();
        writer.println("data.json 파일의 내용이 갱신되었습니다.");
    }

    public void clear() {
        this.service.clear();
    }

}
