package fit.parser;

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
}