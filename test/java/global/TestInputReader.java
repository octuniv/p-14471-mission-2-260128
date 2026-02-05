package global;

import com.ll.global.InputReader;

import java.util.Arrays;
import java.util.Iterator;

public class TestInputReader implements InputReader {
    private final Iterator<String> inputs;
    public TestInputReader(String... lines) {
        this.inputs = Arrays.asList(lines).iterator();
    }
    @Override
    public String readLine() {
        return inputs.next();
    }
}
