package com.ll;

import com.ll.global.InputReader;
import com.ll.global.OutputWriter;
import com.ll.global.WiseSayingRequester;
import com.ll.wiseSaying.controller.WiseSayingController;

public class App {
    private static App instance;
    private final WiseSayingController controller;
    private final InputReader reader;
    private final OutputWriter writer;

    private App(InputReader reader, OutputWriter writer) {
        controller = WiseSayingController.getInstance(reader, writer);
        this.reader = reader;
        this.writer = writer;
    }

    public static App getInstance(InputReader reader, OutputWriter writer) {
        if (instance == null) {
            instance = new App(reader, writer);
        }

        return instance;
    }

    // 테스트 전용: 인스턴스 강제 초기화
    public static void resetForTesting() {
        instance = null;
    }

    public void run() {
        writer.println("== 명언 앱 ==");

        while (true) {
            writer.print("명령) ");
            String cmd = reader.readLine();

            WiseSayingRequester rq = new WiseSayingRequester(cmd);
            String action = rq.getActionName();

            switch (action) {
                case "등록":
                    this.controller.register();
                    continue;
                case "목록":
                    this.controller.inquiry();
                    continue;
                case "빌드":
                    this.controller.build();
                    continue;
                case "삭제": {
                    this.controller.remove(rq);
                    continue;
                }
                case "수정": {
                    this.controller.amend(rq);
                    continue;
                }
                case "종료":
                    return;
            }

        }
    }
}
