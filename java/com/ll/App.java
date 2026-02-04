package com.ll;

import com.ll.wiseSaying.controller.WiseSayingController;
import com.ll.wiseSaying.WiseSayingRequester;

import java.util.Scanner;

public class App {
    private static App instance;
    private final WiseSayingController controller;

    private App() {
        controller = WiseSayingController.getInstance();
    }

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }

        return instance;
    }

    public void execute() {
        Scanner sc = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            WiseSayingRequester rq = new WiseSayingRequester(cmd);
            String action = rq.getAction();

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
