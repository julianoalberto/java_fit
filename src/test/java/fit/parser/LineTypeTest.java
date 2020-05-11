package fit.parser;

import static fit.parser.line.LineType.BLANK;
import static fit.parser.line.LineType.COMMENT;
import static fit.parser.line.LineType.NAME;
import static fit.parser.line.LineType.REPEAT;
import static fit.parser.line.LineType.STEP;
import static fit.parser.line.LineType.UNKNOWN;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fit.parser.line.LineType;

public class LineTypeTest {
    @Test
    public void testKeywork() {
        assertEquals("", LineType.BLANK.keyword);
        assertEquals("#", LineType.COMMENT.keyword);
        assertEquals("repeat", LineType.REPEAT.keyword);
        assertEquals("do", LineType.STEP.keyword);
        assertEquals(null, LineType.UNKNOWN.keyword);
    }


    @Test
    public void testLineParse() {
        String line = "";

        assertEquals(line, BLANK, parseLine(line));

        line = "";
        assertEquals(line, BLANK, parseLine(line));
        line = " p";
        assertEquals(line, UNKNOWN, parseLine(line));

        line = "# comment";
        assertEquals(line, COMMENT, parseLine(line));
        line = "not a comment #";
        assertEquals(line, UNKNOWN, parseLine(line));

        line = "name B12-SPRINT";
        assertEquals(line, NAME, parseLine(line));
        line = "names B12-SPRINT ";
        assertEquals(line, UNKNOWN, parseLine(line));

        line = "do 45m at N1-black \"85 RPM\"";
        assertEquals(line, STEP, parseLine(line));
        line = "do 45m at N1-black 85 RPM";
        assertEquals(line, UNKNOWN, parseLine(line));

        line = "repeat previous 2 steps 10 times";
        assertEquals(line, REPEAT, parseLine(line));
        line = "repeat 2 steps 10 times";
        assertEquals(line, UNKNOWN, parseLine(line));

        line = "ride 45m at N1-black";
        assertEquals(line, UNKNOWN, parseLine(line));

    }

    private LineType parseLine(String line) {
        for (LineType lt : LineType.values()) {
            if (!lt.equals(LineType.UNKNOWN)) {
                if (lt.pattern.matcher(line).matches()) {
                    return lt;
                }
            }
        }

        return LineType.UNKNOWN;
    }
}