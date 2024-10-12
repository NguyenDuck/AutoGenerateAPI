package io.vn.nguyenduck.auto_generate_api.block.description;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import io.vn.nguyenduck.auto_generate_api.utils.Utils;
import org.json.JSONObject;

import static io.vn.nguyenduck.auto_generate_api.utils.Utils.createRangeArray;

public class States extends JSONObject {

    @SuppressWarnings("unchecked")
    public States(Object[][] values) {
        super(new TreeMap<>());
        for (var v : values) {
            int length = v.length;
            String name = (String) v[0];
            Object value = v[1];
            if (value instanceof Boolean) {
                value = List.of(value, !(Boolean) value);
            } else if (value instanceof Number) {
                value = switch (length - 1) {
                    case 1 -> Utils.createRangeArray((int) value);
                    case 2 -> Utils.createRangeArray((int) value, (int) v[2]);
                    case 3 -> Utils.createRangeArray((int) value, (int) v[2], (int) v[3]);
                    default -> value;
                };
                if (((List<?>) value).isEmpty()) {
                    value = new Object[length - 1];
                    System.arraycopy(v, 1, value, 0, length - 1);
                }
            } else if (Enum.class.isAssignableFrom((Class<?>) value)) {
                value = getEnumAvailable((Class<? extends Enum<?>>) value);
            } else {
                value = new Object[length - 1];
                System.arraycopy(v, 1, value, 0, length - 1);
            }
            put(name, value);
        }
    }

    private <E extends Enum<?>> List<String> getEnumAvailable(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants()).map(Enum::toString).toList();
    }
}
