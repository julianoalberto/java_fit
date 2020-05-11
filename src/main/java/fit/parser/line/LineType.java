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
    BLANK("", "(\\s*)"),

    /* A comment line.
    Tokens:
    0 keyword
    1 comment (all the rest of the line) */
    COMMENT("#", "^\\s*(#)(.*)"),

    /* Line with the name of the workout.
    Tokens:
    0 keyword
    1 name of the workout (all the rest of the line) */
    NAME("name", "^\\s*(name)\\s+(.*)"),

    /* Repeat step line.
    Tokens:
    0 keyword
    1 previous N steps to repeat
    2 repetition times */
    REPEAT("repeat", "^\\s*(repeat)\\s+previous\\s+(\\d+)\\s+steps\\s+(\\d+)\\s+times"),

    /* Step line.
    Tokens:
    0 keyword
    1 time
    2 time unit
    3 zone
    4 comment*/
    STEP("do", "^\\s*(do)\\s+(\\d+)([A-Za-z]){1}\\s+at\\s+([A-Za-z0-9]+[\\-]{1}[A-Za-z0-9]+|[A-Za-z0-9]+){1}(\\s*\".+\")*"),
    
    /* Unknown line. This is a special case used only to mark lines as not of any of the known
    types or blank.
    Its pattern should not be used because it will catch any line.
    Instead, check with all other patterns and set type as UNKNOWN in case all of them fail.
    Tokens:
    0 content of the line */
    UNKNOWN(null, "(.*)");    

    public final String keyword;
    public final Pattern pattern;

    LineType(String keyword, String pattern) {
        this.keyword = keyword;
        this.pattern = Pattern.compile(pattern);
    }
}