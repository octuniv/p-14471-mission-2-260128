package com.ll.wiseSaying.controller;

import com.ll.wiseSaying.entity.WiseSaying;
import com.ll.wiseSaying.WiseSayingRequester;
import com.ll.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private static WiseSayingController instance;
    private final WiseSayingService service;
    // 스캐너 주의해서 볼 것
    private static final Scanner sc = new Scanner(System.in);
    private String wise;
    private String author;

    private WiseSayingController() {
        this.service = WiseSayingService.getInstance();
    }

    public static WiseSayingController getInstance() {
        if (instance == null) {
            instance = new WiseSayingController();
        }

        return instance;
    }

    public void register() {
        System.out.print("명언 : ");
        wise = sc.nextLine();
        System.out.print("작가 : ");
        author = sc.nextLine();
        WiseSaying newItem = this.service.register(wise, author);
        System.out.println(newItem.getId() + "번 명언이 등록되었습니다.");
    }

    public void inquiry() {
        List<WiseSaying> wiseItems = this.service.getAllReversed();
        System.out.println("번호 / 작가 / 명언 / 작성일 / 수정일");
        System.out.println("----------------------");
        for (WiseSaying w : wiseItems) {
            System.out.println(w);
        }
    }

    public void remove(WiseSayingRequester rq) {
        int id = rq.getParamAsInt("id", -1);
        if(id == -1) {
            System.out.println("id를 제대로 입력해주세요.");
            return;
        }
        if (!this.service.checkById(id)) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        boolean result = this.service.remove(id);
        if (result) System.out.println(id + "번 명언이 삭제되었습니다.");
        else System.out.println(id + "번 명언이 삭제 중 오류 발생");
    }

    public void amend(WiseSayingRequester rq) {
        int id = rq.getParamAsInt("id", -1);
        if(id == -1) {
            System.out.println("id를 제대로 입력해주세요.");
            return;
        }
        if (!this.service.checkById(id)) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        WiseSaying amendItem = this.service.getItem(id);

        System.out.println("명언(기존) : " + amendItem.getWise());
        System.out.print("명언 : ");
        wise = sc.nextLine();
        System.out.println("작가(기존) : " + amendItem.getAuthor());
        System.out.print("작가 : ");
        author = sc.nextLine();

        boolean result = this.service.amend(id, wise, author);
        if (!result) System.out.println("수정 중 오류");
    }

    public void build() {
        this.service.build();
        System.out.println("data.json 파일의 내용이 갱신되었습니다.");
    }
}
