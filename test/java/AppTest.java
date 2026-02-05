import com.ll.App;
import com.ll.storage.MemoryWiseSayingStorage;
import com.ll.wiseSaying.repository.WiseSayingRepository;
import global.TestAppContext;
import global.TestInputReader;
import global.TestOutputWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    @DisplayName("10단계 프롬프트 테스트")
    void t1() {
        MemoryWiseSayingStorage storage = new MemoryWiseSayingStorage();
        TestInputReader input = new TestInputReader(
                "등록", "현재를 사랑하라.", "작자미상",
                "등록", "과거에 집착하지 마라.", "작자미상",
                "목록",
                "삭제?id=1",
                "삭제?id=1",
                "수정?id=2",
                "현재와 자신을 사랑하라.",
                "홍길동",
                "목록",
                "빌드",
                "종료"
        );
        TestOutputWriter output = new TestOutputWriter();

        WiseSayingRepository.resetForTesting(storage);
        App.resetForTesting();

        try (var _ = new TestAppContext(input, output)) {
            App.getInstance(input, output).run(); // 실제 앱 실행

            List<String> results = output.getOutputs();
            List<String> expected = new ArrayList<>();
            expected.add("1번 명언이 등록되었습니다.");
            expected.add("2번 명언이 등록되었습니다.");
            expected.add("번호 / 작가 / 명언");
            expected.add("2 / 작자미상 / 과거에 집착하지 마라.");
            expected.add("1 / 작자미상 / 현재를 사랑하라.");
            expected.add("1번 명언이 삭제되었습니다.");
            expected.add("1번 명언은 존재하지 않습니다.");
            expected.add("명언(기존) : 과거에 집착하지 마라.");
            expected.add("작가(기존) : 작자미상");
            expected.add("번호 / 작가 / 명언");
            expected.add("2 / 홍길동 / 현재와 자신을 사랑하라.");
            expected.add("data.json 파일의 내용이 갱신되었습니다.");
            
            int matchCase = 0;
            for (String result: results) {
                if (matchCase == expected.size()) break;
                String e = expected.get(matchCase);
                if (result.contains(e)) matchCase += 1;
            }
            assertEquals(matchCase, expected.size());
        }

    }
}
