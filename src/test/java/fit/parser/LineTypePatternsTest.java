package fit.parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.regex.Matcher;

import org.junit.Test;

import fit.parser.line.LineType;

public class LineTypePatternsTest {
    @Test
    public void testBlankPattern() {
        String input = "";
        Matcher m = LineType.BLANK.pattern.matcher(input);
        assertTrue(m.matches());

        printGroups(m);
    }

    @Test
    public void testCommentPattern() {
        String input = "#    ##test my comment     #       ";
        Matcher m = LineType.COMMENT.pattern.matcher(input);
        assertTrue(m.matches());

        printGroups(m);
    }

    @Test
    public void testNamePattern() {
        String input = " name B12- SPRINT";
        Matcher m = LineType.NAME.pattern.matcher(input);
        assertTrue(m.matches());

        printGroups(m);
    }

    @Test
    public void testRepeatPattern() {
        String input = "repeat previous 2 steps 10 times";
        Matcher m = LineType.REPEAT.pattern.matcher(input);
        assertTrue(m.matches());

        printGroups(m);
    }

    @Test
    public void testStepPattern() {
        String input = "do 633d at N1-black \"85 RPM\"";
        Matcher m = LineType.STEP.pattern.matcher(input);
        assertTrue(m.matches());

        printGroups(m);
    }

    private void printGroups(Matcher m) {
        m.group();
        for (int i = 1; i <= m.groupCount(); i++) {
            System.out.println(i + " '" + m.group(i) + "'");
        }
    }

    @Test
    public void testValidBlank() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(" ");
        lines.add(" ");
        lines.add("  ");
        lines.add("     ");

        
        for (String line : lines) {
            Matcher m = LineType.BLANK.pattern.matcher(line);
            assertTrue(line, m.matches());
            printGroups(m);
        }
    }

    @Test
    public void testInvalidBlank() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("a");
        lines.add(" a");
        lines.add("a  ");
        lines.add("   a  ");


        for (String line : lines) {
            Matcher m = LineType.BLANK.pattern.matcher(line);
            assertFalse(line, m.matches());
        }
    }

    @Test
    public void testValidComment() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("#");
        lines.add(" #");
        lines.add("###");
        lines.add("# comment");

        for (String line : lines) {
            Matcher m = LineType.COMMENT.pattern.matcher(line);
            assertTrue(line, m.matches());
            printGroups(m);
        }
    }

    @Test
    public void testInvalidComment() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("a#");
        lines.add(" -");
        lines.add("a  ");
        lines.add("    -# ");

        for (String line : lines) {
            assertFalse(line, LineType.COMMENT.pattern.matcher(line).matches());
        }
    }

    @Test
    public void testValidName() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("name B12-SPRINT");
        lines.add("   name B12-SPRINT");
        lines.add("name N5-3030X 10");

        for (String line : lines) {
            Matcher m = LineType.NAME.pattern.matcher(line);
            assertTrue(line, m.matches());
            printGroups(m);
        }
    }

    @Test
    public void testInvalidName() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("aname B12-SPRINT ");
        lines.add("B12_SPRINT");
        lines.add("  name");

        for (String line : lines) {
            assertFalse(line, LineType.NAME.pattern.matcher(line).matches());
        }
    }

    @Test
    public void testValidRepeat() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("repeat previous 2 steps 5 times");
        lines.add("repeat previous 2 steps 10000 times");

        for (String line : lines) {
            Matcher m = LineType.REPEAT.pattern.matcher(line);
            assertTrue(line, m.matches());
            printGroups(m);
        }
    }

    @Test
    public void testInvalidRepeat() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("repeat n steps x times");
        lines.add("repeat 2 steps x times");
        lines.add("do previous 2 steps 4 times");
        lines.add("repeat previous 0 steps 0 times ");

        for (String line : lines) {
            assertFalse(line, LineType.REPEAT.pattern.matcher(line).matches());
        }
    }

    @Test
    public void testValidStep() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("do 45m at n1 \"85 RPM\"");
        lines.add("do 45s at n1-green");
        lines.add("do 45s at 135-200");
        lines.add("do 45m at N1-red  \"85 rpm up\"");

        for (String line : lines) {
            Matcher m = LineType.STEP.pattern.matcher(line);
            assertTrue(line, m.matches());
            printGroups(m);
        }
    }

    @Test
    public void testInvalidStep() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("do 45m n1 black");
        lines.add("do 45m n1.green 85 rpm nx UP too long for step");
        lines.add("do 45h N6.black 85 rpm nx UP");
        lines.add("do 45m N7.green");
        lines.add("do 45m N6.");
        lines.add("do 45m N6");
        lines.add("do 45m N6.gr");

        for (String line : lines) {
            assertFalse(line, LineType.STEP.pattern.matcher(line).matches());
        }
    }
}