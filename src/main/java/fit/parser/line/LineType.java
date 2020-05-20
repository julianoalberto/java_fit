package fit.parser.line;

import java.util.regex.Pattern;
import static fit.parser.line.TokenType.*;

/**
 * Identify each line based on keywords and holds the regex pattern
 * to parse such line in its tokens.
 */
public enum LineType {
    /* A blank line.
    Tokens:
    0 the line content */
    BLANK("", "(?<blankLine>\\s*)", new TokenType[]{BLANK_LINE}),

    /* A comment line.
    Tokens:
    0 comment */
    COMMENT("#", "^\\s*#(?<commentLine>.*)", new TokenType[]{COMMENT_LINE}),

    /* Line with the name of the workout.
    Tokens:
    0 name of the workout */
    NAME("name", "^\\s*name\\s+(?<workoutName>.*)", new TokenType[]{WORKOUT_NAME}),

    /* Repeat step line.
    Tokens:
    0 previous N steps to repeat
    1 repetition times */
    REPEAT("repeat", "^\\s*repeat\\s+previous\\s+(?<repeatSteps>\\d+)\\s+steps\\s+(?<repeatTimes>\\d+)\\s+times",
            new TokenType[]{REPEAT_STEPS, REPEAT_TIMES}),

    /* Step line.
    Tokens:
    0 time
    1 time unit
    2 zone
    3 comment*/
    STEP("do", "^\\s*do\\s+(?<stepTime>\\d+)(?<stepTimeUnit>[A-Za-z]){1}\\s+at\\s+(?<stepZone>[A-Za-z0-9]+[\\-]{1}[A-Za-z0-9]+|[A-Za-z0-9]+){1}(?<stepComment>\\s*\".+\")*",
            new TokenType[]{STEP_TIME, STEP_TIME_UNIT, STEP_ZONE, STEP_COMMENT}),
    
    /* Unknown line. This is a special case used only to mark lines as not of any of the known
    types or blank.
    Its pattern should not be used because it will catch any line.
    Instead, check with all other patterns and set type as UNKNOWN in case all of them fail.
    Tokens:
    0 content of the line */
    UNKNOWN(null, "(?<nonBlankLine>.*)", new TokenType[]{NON_BLANK_LINE});    

    public final String keyword;
    public final Pattern pattern;
    public final TokenType[] tokens;

    LineType(String keyword, String pattern, TokenType[] tokens) {
        this.keyword = keyword;
        this.pattern = Pattern.compile(pattern);
        this.tokens = tokens;
    }
}