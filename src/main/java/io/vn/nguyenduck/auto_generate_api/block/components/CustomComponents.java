package io.vn.nguyenduck.auto_generate_api.block.components;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomComponents extends ArrayList<String> {
    public CustomComponents(String ...elements) {
        super(Arrays.stream(elements).toList());
    }
}
