package io.vn.nguyenduck.auto_generate_api.json.tokenizer;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONTokenizer implements Iterable<JSONToken> {

    private static final Pattern TOKEN_PATTERN = Pattern.compile(
            "\\s*(//.*|/\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*/|\\{|}|\\[|]|:|,|\"(?:\\\\.|[^\\\\\"])*\"|-?\\d+(?:\\.\\d+)?(?:[eE][+-]?\\d+)?|true|false|null)\\s*"
    );

    private final Matcher matcher;

    public JSONTokenizer(String json) {
        this.matcher = TOKEN_PATTERN.matcher(json);
    }

    public boolean hasMoreTokens() {
        return matcher.find();
    }

    public JSONToken nextToken() {
        final String tokenValue = matcher.group(1);
        final TokenType tokenType = determineTokenType(tokenValue);
        return new JSONToken() {
            @Override
            public TokenType getType() {
                return tokenType;
            }

            @Override
            public String getValue() {
                return tokenValue;
            }
        };
    }

    private TokenType determineTokenType(String tokenValue) {
        switch (tokenValue) {
            case "{":
                return TokenType.LEFT_BRACE;
            case "}":
                return TokenType.RIGHT_BRACE;
            case "[":
                return TokenType.LEFT_BRACKET;
            case "]":
                return TokenType.RIGHT_BRACKET;
            case ":":
                return TokenType.COLON;
            case ",":
                return TokenType.COMMA;
            default:
                if (tokenValue.matches("-?\\d+(\\.\\d+)?([eE][+-]?\\d+)?")) {
                    return TokenType.NUMBER;
                } else if (tokenValue.equals("true") || tokenValue.equals("false")) {
                    return TokenType.BOOLEAN;
                } else if (tokenValue.equals("null")) {
                    return TokenType.NULL;
                } else {
                    return TokenType.STRING;
                }
        }
    }

    @Override
    public @NotNull Iterator<JSONToken> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return hasMoreTokens();
            }

            @Override
            public JSONToken next() {
                return nextToken();
            }
        };
    }
}
