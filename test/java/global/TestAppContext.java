package global;

import com.ll.App;
import com.ll.global.InputReader;
import com.ll.global.OutputWriter;
import com.ll.wiseSaying.controller.WiseSayingController;

public class TestAppContext implements AutoCloseable {
    public TestAppContext(InputReader reader, OutputWriter writer) {
        // 테스트 시작 시 싱글턴 강제 초기화
        App.resetForTesting();
        WiseSayingController.resetForTesting();

        // 새로운 인스턴스 생성 (다음 getInstance 호출 시)
        App.getInstance(reader, writer);
    }

    @Override
    public void close() {
        // 테스트 종료 시 다시 초기화 → 다음 테스트에 영향 없음
        App.resetForTesting();
        WiseSayingController.resetForTesting();
    }
}
