import java.util.Scanner;

public class App {
    private static App instance;
    private final WiseSayingController controller;
    private static final String removePattern = "^삭제\\?id=\\d$";
    private static final String fixPattern = "^수정\\?id=\\d$";

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

            if (cmd.equals("등록")) {
                this.controller.register();
                continue;
            }

            if (cmd.equals("목록")) {
                this.controller.inquiry();
                continue;
            }

            if (cmd.equals("빌드")) {
                this.controller.build();
                continue;
            }

            if (cmd.matches(removePattern)) {
                int seq = Integer.parseInt(cmd.replaceAll("\\D", ""));
                this.controller.remove(seq);
                continue;
            }

            if (cmd.matches(fixPattern)) {
                int seq = Integer.parseInt(cmd.replaceAll("\\D", ""));
                this.controller.amend(seq);
                continue;
            }

            if(cmd.equals("종료")) {
                break;
            }
        }
    }
}
