package io.vn.nguyenduck.auto_generate_api.block.description;

import io.vn.nguyenduck.auto_generate_api.general.CreativeCategory;
import io.vn.nguyenduck.auto_generate_api.annotations.Property;
import io.vn.nguyenduck.auto_generate_api.json.JSONSerializable;
import org.jetbrains.annotations.Nullable;

public class MenuCategory extends JSONSerializable {

    @Nullable
    @Property
    public CreativeCategory Category;

    @Nullable
    @Property
    public Boolean IsHiddenInCommands;
}

