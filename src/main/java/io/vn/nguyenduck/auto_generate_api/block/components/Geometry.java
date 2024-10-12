package io.vn.nguyenduck.auto_generate_api.block.components;

import io.vn.nguyenduck.auto_generate_api.annotations.Property;
import io.vn.nguyenduck.auto_generate_api.json.JSONSerializable;
import org.jetbrains.annotations.Nullable;

public class Geometry extends JSONSerializable {
    private final String identifier;

    public Geometry(String identifier) {
        this.identifier = identifier;
    }

    @Property
    public String Identifier() {
        return identifier;
    }

    @Nullable
    @Property
    public Object BoneVisibility() {
        return null;
    }

    @Nullable
    @Property
    public String Culling() {
        return null;
    }

    @Override
    public Object toObject() {
        return BoneVisibility() == null && Culling() == null ? Identifier() : super.toObject();
    }
}
