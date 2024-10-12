package io.vn.nguyenduck.auto_generate_api.block.components;

import io.vn.nguyenduck.auto_generate_api.annotations.Property;
import io.vn.nguyenduck.auto_generate_api.json.JSONSerializable;

public class DisplayName extends JSONSerializable {

    @Property
    public String value;

    public DisplayName(String value) {
        this.value = value;
    }

    @Override
    public Object toObject() {
        if (value != null) return value;
        return null;
    }
}
