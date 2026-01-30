import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Commander {
    final List<WiseSaying> wiseSayings;
    static final String removePattern = "^삭제\\?id=\\d$";
    static final String fixPattern = "^수정\\?id=\\d$";

    public Commander() {
        wiseSayings = new ArrayList<WiseSaying>();
    }

    public void command() {
        Scanner sc = new Scanner(System.in);
        WiseSaying wiseItem;
        String author;
        String wise;

        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if (cmd.equals("등록")) {
                System.out.print("명언 : " );
                wise = sc.nextLine();
                System.out.print("작가 : ");
                author = sc.nextLine();
                wiseItem = new WiseSaying(wise, author);
                wiseSayings.add(wiseItem);
                System.out.println(wiseItem.getSeq() + "번 명언이 등록되었습니다.");
                continue;
            }

            if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (WiseSaying w: wiseSayings.reversed()) {
                    if (w.getDeleted()) continue;
                    System.out.println(w);
                }
            }

            if (cmd.matches(removePattern)) {
                int seq = Integer.parseInt(cmd.replaceAll("\\D", ""));
                if (wiseSayings.size() < seq || wiseSayings.get(seq-1).getDeleted()) {
                    System.out.println(seq + "번 명언은 존재하지 않습니다.");
                    continue;
                }

                wiseSayings.get(seq-1).setDeleted();

                System.out.println(seq + "번 명언이 삭제되었습니다.");
                continue;
            }

            if (cmd.matches(fixPattern)) {
                int seq = Integer.parseInt(cmd.replaceAll("\\D", ""));

                if (wiseSayings.size() < seq || wiseSayings.get(seq-1).getDeleted()) {
                    System.out.println(seq + "번 명언은 존재하지 않습니다.");
                    continue;
                }

                WiseSaying p = wiseSayings.get(seq-1);

                System.out.println("명언(기존) : " + p.getWise());
                System.out.print("명언 : " );
                wise = sc.nextLine();
                p.setWise(wise);
                System.out.println("작가(기존) : " + p.getAuthor());
                System.out.print("작가 : ");
                author = sc.nextLine();
                p.setAuthor(author);
                continue;
            }

            if(cmd.equals("종료")) {
                break;
            }
        }
    }
}
