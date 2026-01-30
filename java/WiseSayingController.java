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
        System.out.println(newItem.getSeq() + "번 명언이 등록되었습니다.");
    }

    public void inquiry() {
        List<WiseSaying> wiseItems = this.service.getAllReversed();
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (WiseSaying w : wiseItems) {
            System.out.println(w);
        }
    }

    public void remove(int seq) {
        if (!this.service.isExistedById(seq)) {
            System.out.println(seq + "번 명언은 존재하지 않습니다.");
            return;
        }

        boolean result = this.service.remove(seq);
        if (result) System.out.println(seq + "번 명언이 삭제되었습니다.");
        else System.out.println("삭제 중 오류");
    }

    public void amend(int seq) {
        if (!this.service.isExistedById(seq)) {
            System.out.println(seq + "번 명언은 존재하지 않습니다.");
            return;
        }

        WiseSaying amendItem = this.service.getItem(seq);

        System.out.println("명언(기존) : " + amendItem.getWise());
        System.out.print("명언 : ");
        wise = sc.nextLine();
        System.out.println("작가(기존) : " + amendItem.getAuthor());
        System.out.print("작가 : ");
        author = sc.nextLine();

        boolean result = this.service.amend(seq, wise, author);
        if (!result) System.out.println("수정 중 오류");
    }
}
