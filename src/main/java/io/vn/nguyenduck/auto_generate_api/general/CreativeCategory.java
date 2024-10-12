package io.vn.nguyenduck.auto_generate_api.general;

public enum CreativeCategory {
    Construction("construction"),
    Items("items"),
    Nature("nature"),
    None("none"),
    Equipment("equipment"),
    Commands("commands");

    public final String value;

    CreativeCategory(String v) {
        this.value = v;
    }

    @Override
    public String toString() {
        return value;
    }
}
