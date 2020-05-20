package fit.parser;

import static fit.parser.line.LineType.BLANK;
import static fit.parser.line.LineType.COMMENT;
import static fit.parser.line.LineType.NAME;
import static fit.parser.line.LineType.REPEAT;
import static fit.parser.line.LineType.STEP;
import static fit.parser.line.LineType.UNKNOWN;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

import fit.parser.line.LineParser;
import fit.parser.line.Line;

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
    public void testParseLineName()  throws ParseException {
        String lineContent = "name B12-SPRINT";
        Line line = LineParser.parse(lineContent);
        System.out.println(line.getTokens());
    }
}