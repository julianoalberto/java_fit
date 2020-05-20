package fit.parser.line;

import java.text.ParseException;
import java.util.HashMap;
import java.util.regex.Matcher;

import fit.parser.line.*;




// HOW TO PARSE tokens
// PARSE Line
// for each token type in line type
// get named group with token type name
// put token type group value

public class LineParser {
    public static Line parse(String line) throws ParseException {
        HashMap<String, String> tokens = new HashMap<String, String>();

        for (LineType lt : LineType.values()) {
            if (!lt.equals(LineType.UNKNOWN)) {
                Matcher m = lt.pattern.matcher(line);
                if (m.matches()) {
                    m.group();

                    TokenType[] tt = lt.tokens;
                    for (TokenType tokenType : tt) {
                        String tokenValue = m.group(tokenType.name);
                        Matcher tm = tokenType.pattern.matcher(tokenValue);
                        
                        if (tm.matches()) {
                            tokens.put(tokenType.name, tokenValue);
                        }
                        else {
                            throw new ParseException("invalid value <" + tokenValue +
                                "> for <" + tokenType.name, 0);
                        }
                    }
                    return new Line(lt, line, tokens);
                }
            }
        }

        // Unknown line type
        tokens.put("line", line);
        return new Line(LineType.UNKNOWN, line, tokens);
    }
}