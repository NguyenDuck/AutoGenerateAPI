package io.vn.nguyenduck.auto_generate_api.block.description;

import io.vn.nguyenduck.auto_generate_api.annotations.Property;
import io.vn.nguyenduck.auto_generate_api.json.JSONSerializable;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Traits extends JSONSerializable {

    public static class PlacementDirection extends JSONSerializable {

        public PlacementDirection(List<String> enabledStates) {
            EnabledStates = enabledStates;
        }

        public PlacementDirection(List<String> enabledStates, Number yRotationOffset) {
            EnabledStates = enabledStates;
            YRotationOffset = yRotationOffset;
        }

        @Property
        public List<String> EnabledStates;

        @Nullable
        @Property("y_rotation_offset")
        public Number YRotationOffset;
    }

    public static class PlacementPosition extends JSONSerializable {

        public PlacementPosition(List<String> enabledStates) {
            EnabledStates = enabledStates;
        }

        @Property
        public List<String> EnabledStates;
    }

    @Nullable
    @Property("minecraft:placement_direction")
    public PlacementDirection PlacementDirection;

    @Nullable
    @Property("minecraft:placement_position")
    public PlacementPosition PlacementPosition;
}
