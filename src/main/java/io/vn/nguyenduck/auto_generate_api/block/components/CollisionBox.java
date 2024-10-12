package io.vn.nguyenduck.auto_generate_api.block.components;

import io.vn.nguyenduck.auto_generate_api.annotations.Property;
import io.vn.nguyenduck.auto_generate_api.json.JSONSerializable;
import org.jetbrains.annotations.Nullable;

public abstract class CollisionBox extends JSONSerializable {

    private boolean hasCollisionBox = true;
    private Number[] size;
    private Number[] origin;

    public CollisionBox create() {
        return null;
    }

    public CollisionBox(boolean hasCollisionBox) {
        this.hasCollisionBox = hasCollisionBox;
    }

    public CollisionBox(Number sizeX, Number sizeY, Number sizeZ) {
        size = new Number[]{sizeX, sizeY, sizeZ};
    }

    public CollisionBox(Number sizeX, Number sizeY, Number sizeZ, Number originX, Number originY, Number originZ) {
        this(sizeX, sizeY, sizeZ);
        origin = new Number[]{originX, originY, originZ};
    }

    @Nullable
    @Property
    public Number[] Size() {
        return size;
    }

    @Nullable
    @Property
    public Number[] Origin() {
        return origin;
    }

    @Override
    public Object toObject() {
        if (!hasCollisionBox) return false;
        if (size == null && origin == null) return null;
        return super.toObject();
    }
}
