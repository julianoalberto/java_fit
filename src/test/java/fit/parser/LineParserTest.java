package fit.parser;

import static fit.parser.line.LineType.BLANK;
import static fit.parser.line.LineType.COMMENT;
import static fit.parser.line.LineType.NAME;
import static fit.parser.line.LineType.REPEAT;
import static fit.parser.line.LineType.STEP;
import static fit.parser.line.LineType.UNKNOWN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Map;

import org.junit.Test;

import fit.parser.line.Line;
import fit.parser.line.LineParser;
import fit.parser.line.LineType;
import fit.parser.line.TokenType;

public class LineParserTest {
    
    @Test
    public void testParseLine() throws ParseException {
        String line = "";

        assertEquals(line, BLANK, LineParser.parse(line).getType());

        line = "";
        assertEquals(line, BLANK, LineParser.parse(line).getType());
        line = " p";
        assertEquals(line, UNKNOWN, LineParser.parse(line).getType());

        line = "# comment";
        assertEquals(line, COMMENT, LineParser.parse(line).getType());
        line = "not a comment #";
        assertEquals(line, UNKNOWN, LineParser.parse(line).getType());

        line = "name B12-SPRINT";
        assertEquals(line, NAME, LineParser.parse(line).getType());
        System.out.println(LineParser.parse(line).getTokens());

        line = "names B12-SPRINT ";
        assertEquals(line, UNKNOWN, LineParser.parse(line).getType());

        line = "do 45m at N1-black \"85 RPM\"";
        assertEquals(line, STEP, LineParser.parse(line).getType());
        line = "do 45m at N1-black 85 RPM";
        assertEquals(line, UNKNOWN, LineParser.parse(line).getType());

        line = "repeat previous 2 steps 10 times";
        assertEquals(line, REPEAT, LineParser.parse(line).getType());
        line = "repeat 2 steps 10 times";
        assertEquals(line, UNKNOWN, LineParser.parse(line).getType());

        line = "ride 45m at N1-black";
        assertEquals(line, UNKNOWN, LineParser.parse(line).getType());
        System.out.println(LineParser.parse(line).getTokens());
    }

    
    @Test
    public void testParseLineTokensName()  throws ParseException {
        String name = "B12-SPRINT";
    	String lineContent = "name " + name;
        Line line = LineParser.parse(lineContent);
        Map<String, String> tokens = line.getTokens();
        
        assertEquals(LineType.NAME, line.getType());
        assertEquals(1, tokens.size());
        assertTrue(TokenType.WORKOUT_NAME.name, tokens.containsKey(TokenType.WORKOUT_NAME.name));
        assertEquals(name, tokens.get(TokenType.WORKOUT_NAME.name));
    }
    
    @Test
    public void testParseLineTokensComment()  throws ParseException {
        String comment = "fazer em subida quando possível";
    	String lineContent = "#" + comment;
        Line line = LineParser.parse(lineContent);
        Map<String, String> tokens = line.getTokens();
        
        assertEquals(LineType.COMMENT, line.getType());
        assertEquals(1, tokens.size());
        assertTrue(TokenType.COMMENT_LINE.name, tokens.containsKey(TokenType.COMMENT_LINE.name));
        assertEquals(comment, tokens.get(TokenType.COMMENT_LINE.name));
    }
    
//    @Test
//    public void testParseLineComment()  throws ParseException {
//        String lineContent = "name B12-SPRINT";
//        Line line = LineParser.parse(lineContent);
//        System.out.println(line.getTokens());
//        
//        for (TokenType tt : line.getType().tokens) {
//			System.out.println(tt.name);
//		}
//    }
}