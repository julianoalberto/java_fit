package fit.parser.line;

import java.util.regex.Pattern;

public enum TokenType {
    BLANK_LINE("blankLine", "(\\s*)"),
    COMMENT_LINE("commentLine", "(.*)"),
    WORKOUT_NAME("workoutName", "([A-Z0-9\\-]{1,12})"),
    REPEAT_STEPS("repeatSteps", "(\\d+)"),
    REPEAT_TIMES("repeatTimes", "(\\d+)"),
    STEP_TIME("stepTime", "(.+)"),
    STEP_TIME_UNIT("stepTimeUnit", "(.+)"),
    STEP_ZONE("stepZone", "(.+)"),
    STEP_COMMENT("stepComment", "(.+)"),
    NON_BLANK_LINE("nonBlankLine", "(.+)");

    public final String name;
    public final Pattern pattern;

    TokenType(String name, String pattern) {
        this.name = name;
        this.pattern = Pattern.compile(pattern);
    }
}