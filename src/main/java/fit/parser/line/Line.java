package fit.parser.line;

import java.util.HashMap;
import java.util.Map;

public class Line {
    private final LineType type;
    private final String rawContent;
    private final HashMap<String, String> tokens;
    
    public Line(LineType type, String rawContent, HashMap<String, String> tokens) {
        this.type = type;
        this.rawContent = rawContent;
        this.tokens = tokens;
    }

    public LineType getType() {
        return type;
    }

    public String getRawContent() {
        return rawContent;
    }

    public Map<String, String> getTokens() {
        return tokens;
    }

    

}