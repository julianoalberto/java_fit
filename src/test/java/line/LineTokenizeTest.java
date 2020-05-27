package line;

import static fit.parser.line.LineType.BLANK;
import static fit.parser.line.LineType.COMMENT;
import static fit.parser.line.LineType.NAME;
import static fit.parser.line.LineType.REPEAT;
import static fit.parser.line.LineType.STEP;
import static fit.parser.line.LineType.UNKNOWN;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import fit.parser.line.Line;

public class LineTokenizeTest {
	@Test
	public void testBlank() {
		Line l = new Line("");
		assertEquals(BLANK, l.getType());
		assertEquals("", l.getLineContent());
		assertEquals(1, l.getTokens().size());
		assertEquals("", l.getTokens().get("content"));
	}
	
	@Test
	public void testComment() {
		Line l = new Line(" ## do on saturdays ");
		assertEquals(COMMENT, l.getType());
		assertEquals(" ## do on saturdays ", l.getLineContent());
		assertEquals(2, l.getTokens().size());
		assertEquals("#", l.getTokens().get("keyword"));
		assertEquals("# do on saturdays ", l.getTokens().get("comment"));
	}
	
	@Test
	public void testName() {
		Line l = new Line("name B12-SPRINT");
		assertEquals(NAME, l.getType());
		assertEquals("name B12-SPRINT", l.getLineContent());
		assertEquals(2, l.getTokens().size());
		assertEquals("name", l.getTokens().get("keyword"));
		assertEquals("B12-SPRINT", l.getTokens().get("name"));
	}
	
	@Test
	public void testRepeat() {
		Line l = new Line("repeat previous 2 steps 10 times");
		assertEquals(REPEAT, l.getType());
		assertEquals("repeat previous 2 steps 10 times", l.getLineContent());
		assertEquals(3, l.getTokens().size());
		assertEquals("repeat", l.getTokens().get("keyword"));
		assertEquals("2", l.getTokens().get("steps"));
		assertEquals("10", l.getTokens().get("times"));
	}
	
	@Test
	public void testStep() {
		Line l = new Line("do 30s at n1-red \"85 RPM\"");
		assertEquals(STEP, l.getType());
		assertEquals("do 30s at n1-red \"85 RPM\"", l.getLineContent());
		assertEquals(5, l.getTokens().size());
		assertEquals("do", l.getTokens().get("keyword"));
		assertEquals("30", l.getTokens().get("time"));
		assertEquals("s", l.getTokens().get("unit"));
		assertEquals("n1-red", l.getTokens().get("zone"));
		assertEquals("\"85 RPM\"", l.getTokens().get("comment"));
	}
	
	@Test
	public void testUnknown() {
		Line l = new Line("this line is not known");
		assertEquals(UNKNOWN, l.getType());
		assertEquals("this line is not known", l.getLineContent());
		assertEquals(1, l.getTokens().size());
		assertNull(l.getTokens().get("keyword"));
		assertEquals("this line is not known", l.getTokens().get("content"));
	}
}
