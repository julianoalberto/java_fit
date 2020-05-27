package fit.parser.line;

import java.util.regex.Pattern;

/**
 * Identify each line based on keywords and holds the regex pattern
 * to parse such line in its tokens.
 */
public enum LineType {
    /* A blank line.
    Tokens:
    0 the line content */
    BLANK("(?<content>\\s*)", new String[]{"content"}),

    /* A comment line.
    Tokens:
    0 comment */
    COMMENT("^\\s*(?<keyword>#)(?<comment>.*)", new String[]{"keyword", "comment"}),

    /* Line with the name of the workout.
    Tokens:
    0 name of the workout */
    NAME("^\\s*(?<keyword>name)\\s+(?<name>.*)", new String[]{"keyword", "name"}),

    /* Repeat step line.
    Tokens:
    0 previous N steps to repeat
    1 repetition times */
    REPEAT("^\\s*(?<keyword>repeat)\\s+previous\\s+(?<steps>\\d+)\\s+steps\\s+(?<times>\\d+)\\s+times",
            new String[]{"keyword", "steps", "times"}),

    /* Step line.
    Tokens:
    0 time
    1 time unit
    2 zone
    3 comment*/
    STEP("^\\s*(?<keyword>do)\\s+(?<time>\\d+)(?<unit>[A-Za-z]){1}\\s+at\\s+(?<zone>[A-Za-z0-9]+[\\-]{1}[A-Za-z0-9]+|[A-Za-z0-9]+){1}\\s*(?<comment>\".+\")*",
            new String[]{"keyword", "time", "unit", "zone", "comment"}),
    
    /* Unknown line. This is a special case used only to mark lines as not of any of the known
    types or blank.
    Its pattern should not be used because it will catch any line.
    Instead, check with all other patterns and set type as UNKNOWN in case all of them fail.
    Tokens:
    0 content of the line */
    UNKNOWN("(?<content>.*)", new String[]{"content"});    

    public final Pattern pattern;
    public final String[] groupNames;

    LineType(String pattern, String[] groupNames) {
        this.pattern = Pattern.compile(pattern);
        this.groupNames = groupNames;
    }
}