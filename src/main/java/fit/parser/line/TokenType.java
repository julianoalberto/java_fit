package fit.parser.line;

import java.util.regex.Pattern;

public enum TokenType {
    BLANK_LINE("blankLine", "(\\s*)"),
    COMMENT_LINE("commentLine", "(.*)"),
    WORKOUT_NAME("workoutName", "([A-Z0-9\\-]{1,12})"),
    REPEAT_STEPS("repeatSteps", "(\\d{1,2})"),
    REPEAT_TIMES("repeatTimes", "(\\d{1,2})"),
    STEP_TIME("stepTime", "(\\d{1,3})"),
    STEP_TIME_UNIT("stepTimeUnit", "([s|m]{1})"),
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