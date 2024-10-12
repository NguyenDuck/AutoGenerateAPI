package io.vn.nguyenduck.auto_generate_api.block;

import io.vn.nguyenduck.auto_generate_api.SemVer;
import io.vn.nguyenduck.auto_generate_api.annotations.RequireFormatVersion;
import io.vn.nguyenduck.auto_generate_api.block.components.*;
import io.vn.nguyenduck.auto_generate_api.annotations.Prefix;
import io.vn.nguyenduck.auto_generate_api.annotations.Property;
import io.vn.nguyenduck.auto_generate_api.block.components.*;
import io.vn.nguyenduck.auto_generate_api.block.description.MenuCategory;
import io.vn.nguyenduck.auto_generate_api.block.description.States;
import io.vn.nguyenduck.auto_generate_api.block.description.Traits;
import io.vn.nguyenduck.auto_generate_api.json.JSONSerializable;
import org.jetbrains.annotations.Nullable;

public class BlockBehavior extends JSONSerializable {

    public BlockBehavior(SemVer format_version, String identifier) {
        FormatVersion = format_version;
        Identifier = identifier;
    }

    @Property
    public SemVer FormatVersion;

    @Property({"Ominecraft:block", "Odescription"})
    public String Identifier;

    @Nullable
    @Property({"Ominecraft:block", "Odescription"})
    public MenuCategory MenuCategory;

    @Nullable
    @Property({"Ominecraft:block", "Odescription"})
    public States States;

    @Nullable
    @Property({"Ominecraft:block", "Odescription"})
    @RequireFormatVersion({1, 21, 0})
    public Traits Traits;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 19, 50})
    public CollisionBox CollisionBox;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 19, 50})
    public io.vn.nguyenduck.auto_generate_api.block.components.CraftingTable CraftingTable;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 21, 10})
    public io.vn.nguyenduck.auto_generate_api.block.components.CustomComponents CustomComponents;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 19, 20})
    public io.vn.nguyenduck.auto_generate_api.block.components.DestructibleByExplosion DestructibleByExplosion;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 21, 30})
    public io.vn.nguyenduck.auto_generate_api.block.components.DestructibleByMining DestructibleByMining;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 19, 60})
    public io.vn.nguyenduck.auto_generate_api.block.components.DisplayName DisplayName;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 21, 10})
    public EntityFallOn EntityFallOn;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 19, 10})
    public Flammable Flammable;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 19, 20})
    public Friction Friction;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 20, 60})
    public Geometry Geometry;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    public String Loot;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 19, 40})
    public LightDampening LightDampening;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 19, 40})
    public LightEmission LightEmission;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    public String MapColor;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 19, 40})
    public MaterialInstances MaterialInstances;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 19, 60})
    public PlacementFilter PlacementFilter;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 21, 30})
    public RedstoneConductivity RedstoneConductivity;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 19, 60})
    public SelectionBox SelectionBox;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 21, 10})
    public Tick Tick;

    @Nullable
    @Prefix
    @Property({"Ominecraft:block", "Ocomponents"})
    @RequireFormatVersion({1, 19, 80})
    public Transformation Transformation;

    @Override
    public Object toObject() {
        return super.toObject();
    }
}
