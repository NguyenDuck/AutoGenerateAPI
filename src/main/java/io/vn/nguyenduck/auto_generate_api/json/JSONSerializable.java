package io.vn.nguyenduck.auto_generate_api.json;

import io.vn.nguyenduck.auto_generate_api.utils.Utils;
import io.vn.nguyenduck.auto_generate_api.annotations.Prefix;
import io.vn.nguyenduck.auto_generate_api.annotations.Property;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

public abstract class JSONSerializable extends JSONPrettier {

    private int getType(String raw) {
        return raw.startsWith("O") ? 1 : raw.startsWith("A") ? 2 : 0;
    }

    @Override
    public String toString() {
        return toObject().toString();
    }

    private Method[] getMethodOf(Class<?> clazz) {
        var methods = clazz.getMethods();
        var method_set = new HashSet<Method>();
        for (var method : methods) {
            if (method.isAnnotationPresent(Property.class)) {
                method_set.add(method);
            }
        }
        return method_set.toArray(Method[]::new);
    }

    private Field[] getFieldOf(Class<?> clazz) {
        var fields = clazz.getFields();
        var field_set = new HashSet<Field>();
        for (var field : fields) {
            if (field.isAnnotationPresent(Property.class)) {
                field_set.add(field);
            }
        }
        return field_set.toArray(Field[]::new);
    }

    private boolean hasPropertyAnnotation(Object o) {
        return getPropertyOf(o) != null;
    }

    private Property getPropertyOf(Object o) {
        return o.getClass().getAnnotation(Property.class);
    }

    private Object getJsonObject(Object o1, ArrayList<String> path) {
        for (var p : path) {

            var type = getType(p);
            var key = p;

            Object jsonType = switch (type) {
                case 1 -> new JSONObject(new TreeMap<>());
                case 2 -> new JSONArray();
                default -> null;
            };

            if (jsonType != null) key = p.substring(1);

            if (((JSONObject) o1).has(key)) {
                o1 = ((JSONObject) o1).get(key);
            } else if (jsonType != null) {
                ((JSONObject) o1).put(key, jsonType);
                o1 = ((JSONObject) o1).get(key);
            }
        }
        return o1;
    }

    private void handleType(Object o1, Object val, ArrayList<String> path) {

        var last_path = path.getLast();
        if (getType(last_path) > 0) {
            System.out.println(o1);
            System.out.println(path);
            System.out.println(val);
            return;
        }

        switch (val) {
            case JSONSerializable v -> {
                if (o1 instanceof JSONObject o2) {
                    o2.put(last_path, v.toObject());
                } else if (o1 instanceof JSONArray o2) {
                    o2.put(v.toObject());
                }
            }
            case Object[] v -> {
                if (o1 instanceof JSONObject o2) {
                    var o3 = new JSONArray();
                    o2.put(last_path, o3);
                    o3.putAll(v);
                } else if (o1 instanceof JSONArray o2) {
                    o2.putAll(v);
                }
            }
            case Enum<?> v -> ((JSONObject) o1).put(last_path, v.toString());
            case null, default -> ((JSONObject) o1).put(last_path, val);
        }
    }

    @SneakyThrows
    public Object toObject() {
        JSONObject o = new JSONObject(new TreeMap<>());

        var currentClass = getClass();
        var methods = getMethodOf(currentClass);

        var parent_path = new ArrayList<String>();
        {
            var class_annotation = currentClass.getAnnotation(Property.class);
            if (class_annotation != null) {
                if (class_annotation.value().length > 0) {
                    parent_path.addAll(List.of(class_annotation.value()));
                } else {
                    parent_path.add("O" + Utils.toSnakeCase(currentClass.getSimpleName()));
                }
            }
        }

        for (var method : methods) {
            var method_value = method.invoke(this);
            if (method_value == null) continue;
            var path = new ArrayList<>(parent_path);

            var method_annotation = method.getAnnotation(Property.class);
            if (method_annotation.value().length > 0) {
                path.addAll(List.of(method_annotation.value()));
            } else {
                path.add(Utils.toSnakeCase(method.getName()));
            }

            if (hasPropertyAnnotation(method_value)) {
                var value_annotation = getPropertyOf(method_value).value();
                if (value_annotation.length > 0) {
                    path.addAll(List.of(value_annotation));
                } else {
                    path.add(method_value.getClass().getName());
                }
            }

            handleType(getJsonObject(o, path), method_value, path);
        }

        var fields = getFieldOf(currentClass);
        for (var field : fields) {
            var field_value = field.get(this);
            if (field_value == null) continue;
            var path = new ArrayList<>(parent_path);

            var field_annotation = field.getAnnotation(Property.class).value();
            if (field_annotation.length > 0) {
                path.addAll(List.of(field_annotation));
                if (getType(path.getLast()) > 0) path.add(Utils.toSnakeCase(field.getName()));
            } else {
                path.add(Utils.toSnakeCase(field.getName()));
            }
            if (field.isAnnotationPresent(Prefix.class)) {
                path.set(path.size() - 1, field.getAnnotation(Prefix.class).value() + ":" + path.getLast());
            }

            if (hasPropertyAnnotation(field_value)) {
                var value_annotation = getPropertyOf(field_value).value();
                if (value_annotation.length > 0) {
                    path.addAll(List.of(value_annotation));
                } else {
                    path.add(Utils.toSnakeCase(field_value.getClass().getSimpleName()));
                }
            }

            handleType(getJsonObject(o, path), field_value, path);
        }

        return o;
    }
}
