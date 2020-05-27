package fit.parser.line;

import java.util.HashMap;
import java.util.regex.Matcher;

public class Line {
    private LineType type;
	private String lineContent;
    private HashMap<String, String> tokens;
    
    public Line(String lineContent) {
    	this.lineContent = lineContent;
        this.classify();
        this.tokenize();
    }
    
    private void classify() {
		for (LineType type : LineType.values()) {
			if (!type.equals(LineType.UNKNOWN)) { // skips UNKNOWN because this is a special case assigned when no other matches
                Matcher matcher = type.pattern.matcher(lineContent);
                if (matcher.matches()) {
                    this.type = type; // first match is picked up
                    return;  // remaining are ignored
                }
            }
        }
		// getting here means no other LineType matched the given line, so it is classified as UNKNOWN
		this.type = LineType.UNKNOWN;
	}

	private void tokenize() {
		tokens = new HashMap<String, String>();
		Matcher matcher = type.pattern.matcher(lineContent);
		matcher.find();
		
		for (String tokenName : type.groupNames) {
			tokens.put(tokenName, matcher.group(tokenName));
		}
		System.out.println(tokens);
	}
	
	public LineType getType() {
		return this.type;
	}
	
	public String getLineContent() {
		return this.lineContent;
	}
	
	public HashMap<String, String> getTokens() {
		return this.tokens;
	}
}