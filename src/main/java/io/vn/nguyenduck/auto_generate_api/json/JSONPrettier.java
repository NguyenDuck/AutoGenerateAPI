package io.vn.nguyenduck.auto_generate_api.json;

public abstract class JSONPrettier {
    public JSONPrettier() {
    }

    public String toString(int indent) {
        return this.toString(indent, " ");
    }

    public String toString(int indent, String indentCharacter) {
        if (indent == 0) {
            return this.toString();
        } else {
            String chars = indentCharacter.repeat(indent);
            StringBuilder prettyJson = new StringBuilder();
            int indentLevel = 0;
            boolean inQuotes = false;
            char[] var10;
            int var9 = (var10 = this.toString().toCharArray()).length;

            for(int var8 = 0; var8 < var9; ++var8) {
                char charFromJson = var10[var8];
                switch (charFromJson) {
                    case ' ':
                        if (inQuotes) {
                            prettyJson.append(charFromJson);
                        }
                        break;
                    case '"':
                        inQuotes = !inQuotes;
                        prettyJson.append(charFromJson);
                        break;
                    case ',':
                        prettyJson.append(charFromJson);
                        if (!inQuotes) {
                            prettyJson.append('\n');
                            this.addIndentation(prettyJson, indentLevel, chars);
                        }
                        break;
                    case ':':
                        prettyJson.append(charFromJson);
                        if (!inQuotes) {
                            prettyJson.append(' ');
                        }
                        break;
                    case '[':
                    case '{':
                        prettyJson.append(charFromJson);
                        if (!inQuotes) {
                            prettyJson.append('\n');
                            ++indentLevel;
                            this.addIndentation(prettyJson, indentLevel, chars);
                        }
                        break;
                    case ']':
                    case '}':
                        if (!inQuotes) {
                            prettyJson.append('\n');
                            --indentLevel;
                            this.addIndentation(prettyJson, indentLevel, chars);
                        }

                        prettyJson.append(charFromJson);
                        break;
                    default:
                        prettyJson.append(charFromJson);
                }
            }

            return prettyJson.toString();
        }
    }

    private void addIndentation(StringBuilder builder, int indentLevel, String chars) {
        builder.append(chars.repeat(Math.max(0, indentLevel)));
    }
}