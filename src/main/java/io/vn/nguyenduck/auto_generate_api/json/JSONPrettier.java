package io.vn.nguyenduck.auto_generate_api.json;

import io.vn.nguyenduck.auto_generate_api.json.tokenizer.JSONToken;
import io.vn.nguyenduck.auto_generate_api.json.tokenizer.JSONTokenizer;
import io.vn.nguyenduck.auto_generate_api.json.tokenizer.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class JSONPrettier {

    private Stream<String> getValueOf(List<JSONToken> tokens) {
        return tokens.stream().map(JSONToken::getValue);
    }

    public String toString(int indent) {
        return toString(indent, " ");
    }

    public String toString(int indent, String indentChar) {
        if (indent <= 0) return toString();

        JSONTokenizer tokenizer = new JSONTokenizer(toString());

        StringBuilder prettyJson = new StringBuilder();
        int currentIndent = 0;
        boolean inArray = false;
        List<JSONToken> arrayElements = new ArrayList<>();

        for (JSONToken token : tokenizer) {
            switch (token.getType()) {
                case LEFT_BRACE:
                case LEFT_BRACKET:
                    prettyJson.append(token.getValue());

                    if (token.getType() == TokenType.LEFT_BRACKET) inArray = true;
                    else prettyJson.append("\n").append(indentChar.repeat(currentIndent += indent));
                    break;
                case RIGHT_BRACE:
                case RIGHT_BRACKET:
                    if (inArray && token.getType() == TokenType.RIGHT_BRACKET) {
                        inArray = false;

                        boolean needLineWrap = getValueOf(arrayElements).mapToInt(String::length).sum() <= 50;

                        if (!needLineWrap) prettyJson.append("\n").append(indentChar.repeat(currentIndent += indent));

                        final int i = currentIndent;
                        arrayElements.forEach(t -> prettyJson.append(t.getValue())
                                .append(t.getType() == TokenType.COMMA ? needLineWrap ? " " : "\n" + indentChar.repeat(i) : "")
                        );
                        arrayElements.clear();

                        if (!needLineWrap) prettyJson.append("\n").append(indentChar.repeat(currentIndent -= indent));
                    } else prettyJson.append("\n").append(indentChar.repeat(currentIndent -= indent));

                    prettyJson.append(token.getValue());
                    break;
                case COLON:
                case COMMA:
                case STRING:
                case NUMBER:
                case BOOLEAN:
                case NULL:
                    if (inArray) arrayElements.add(token);
                    else {
                        prettyJson.append(token.getValue());
                        switch (token.getType()) {
                            case COLON -> prettyJson.append(" ");
                            case COMMA -> prettyJson.append("\n").append(indentChar.repeat(currentIndent));
                        }
                    }
                    break;
                case COMMENT:
                    prettyJson.append("\n").append(indentChar.repeat(currentIndent)).append(token.getValue());
                    break;
            }
        }

        return prettyJson.toString();
    }
}
