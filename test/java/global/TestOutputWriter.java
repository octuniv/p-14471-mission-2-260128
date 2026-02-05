package global;

import com.ll.global.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TestOutputWriter implements OutputWriter {
    private final List<String> outputs = new ArrayList<>();
    @Override
    public void println(String s) { outputs.add(s); }
    @Override
    public void print(String s) { outputs.add(s); }
    public List<String> getOutputs() { return outputs; }
    public String getAllOutput() { return String.join("\n", outputs); }
}
