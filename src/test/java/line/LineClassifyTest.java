package line;

import static fit.parser.line.LineType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import fit.parser.line.Line;

public class LineClassifyTest {
	@Test
	public void testBlank() {
		assertNull(new Line("").getTokens().get("keyword"));
		
		assertEquals(BLANK, new Line("").getType());
		assertNotEquals(BLANK, new Line("a").getType());
		assertEquals(BLANK, new Line(" ").getType());
		assertNotEquals(BLANK, new Line(" a").getType());
	}
	
	@Test
	public void testComment() {
		assertEquals("#", new Line(" #this is a comment").getTokens().get("keyword"));
		
		assertEquals(COMMENT, new Line("#").getType());
		assertEquals(COMMENT, new Line(" #").getType());
		assertEquals(COMMENT, new Line(" #this is a comment").getType());
		assertEquals(COMMENT, new Line("### a comment ##").getType());
		assertEquals(COMMENT, new Line("#####").getType());
		
		assertNotEquals(COMMENT, new Line("a").getType());
		assertNotEquals(COMMENT, new Line(" a").getType());
	}
	
	@Test
	public void testName() {
		assertEquals("name", new Line(" name B12-A ").getTokens().get("keyword"));
		
		assertEquals(NAME, new Line("name ").getType());
		assertEquals(NAME, new Line(" name ").getType());
		assertEquals(NAME, new Line("name abc").getType());
		assertEquals(NAME, new Line("name B12-Sprint").getType());
		assertEquals(NAME, new Line("name b12 sprint").getType());
		
		assertNotEquals(NAME, new Line("NAME ").getType());
		assertNotEquals(NAME, new Line("workout name ").getType());
	}
	
	@Test
	public void testRepeat() {
		assertEquals("repeat", new Line(" repeat  previous 2 steps 10 times").getTokens().get("keyword"));
		
		assertEquals(REPEAT, new Line("repeat previous 2 steps 10 times").getType());
		assertEquals(REPEAT, new Line(" repeat previous   1  steps 0 times").getType());
		
		assertNotEquals(REPEAT, new Line("repeat previous 1 steps 10 times   ").getType());
		assertNotEquals(REPEAT, new Line("repeat previous 1 step 10 times").getType());
		assertNotEquals(REPEAT, new Line("repeat previous 10 steps 1 time").getType());
	}
	
	@Test
	public void testStep() {
		assertEquals("do", new Line("  do    30s    at n1-green \"85 RPM\"").getTokens().get("keyword"));
		
		assertEquals(STEP, new Line("do 30s at n1-green").getType());
		assertEquals(STEP, new Line("do 30s at n1-green \"85 RPM\"").getType());
		assertEquals(STEP, new Line("do 30m at n2-green").getType());
		assertEquals(STEP, new Line("do 30m at n2-red \"85 RPM\"").getType());
		assertEquals(STEP, new Line("do 30s at 123-130").getType());
		assertEquals(STEP, new Line("  do    30s    at n1-green \"85 RPM\"").getType());
				
		assertNotEquals(STEP, new Line("repeat previous 1 steps 10 times   ").getType());
		assertNotEquals(STEP, new Line("repeat previous 1 step 10 times").getType());
		assertNotEquals(STEP, new Line("repeat previous 10 steps 1 time").getType());
	}
	
	@Test
	public void testUnknown() {
		assertNull(new Line(" line with no valid content ").getTokens().get("keyword"));
		
		assertNotEquals(UNKNOWN, new Line("").getType());
		assertNotEquals(UNKNOWN, new Line("#").getType());
		assertNotEquals(UNKNOWN, new Line("name ").getType());
		assertNotEquals(UNKNOWN, new Line("repeat previous 2 steps 10 times").getType());
		assertNotEquals(UNKNOWN, new Line("do 30s at n1-green").getType());
		
		assertEquals(UNKNOWN, new Line("-").getType());
		assertEquals(UNKNOWN, new Line("-#").getType());
		assertEquals(UNKNOWN, new Line("NAME ").getType());
		assertEquals(UNKNOWN, new Line("repeat until previous 2 steps 10 times").getType());
		assertEquals(UNKNOWN, new Line("workout at 98 rpm").getType());
	}
}
