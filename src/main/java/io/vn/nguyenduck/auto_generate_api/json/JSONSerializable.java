package io.vn.nguyenduck.auto_generate_api.json;

import io.vn.nguyenduck.auto_generate_api.annotations.Property;
import io.vn.nguyenduck.auto_generate_api.utils.Utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class JSONSerializable extends JSONPrettier {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public JSONSerializable() {
    }

    private IProperties<?>[] getProperties(Object obj) {
        return this.getProperties(obj, this.getClass());
    }

    private IProperties<?>[] getProperties(Object obj, Class<?> clazz) {
        return this.getProperties(obj, new HashSet<>(), clazz);
    }

    private IProperties<?>[] getProperties(final Object obj, Set<IProperties<?>> set, Class<?> clazz) {
        ArrayList<String> parent_path = new ArrayList<>();
        Property parent = (Property)clazz.getAnnotation(Property.class);
        if (parent != null) {
            if (parent.value().length > 0) {
                parent_path.addAll(List.of(parent.value()));
            } else {
                parent_path.add(Utils.toSnakeCase(clazz.getName()));
            }
        }

        this.logger.info(clazz.toString());
        Method[] methods = clazz.getMethods();
        Method[] var10 = methods;
        int var9 = methods.length;

        for(int var8 = 0; var8 < var9; ++var8) {
            final Method method = var10[var8];
            if (method.isAnnotationPresent(Property.class)) {
                Property method_annotation = method.getAnnotation(Property.class);
                ArrayList<String> l = new ArrayList<>(parent_path);
                if (method_annotation.value().length > 0) {
                    l.addAll(List.of(method_annotation.value()));
                } else {
                    l.add(Utils.toSnakeCase(method.getName()));
                }

                final String[] path = l.toArray(String[]::new);

                final String name = method.getName();
                set.add(new IProperties<>() {
                    public String[] getPath() {
                        return path;
                    }

                    public String getName() {
                        return name;
                    }

                    public Object getValue() {
                        try {
                            return method.invoke(obj);
                        } catch (Exception ignored) {
                            return null;
                        }
                    }
                });
            }
        }

        Field[] fields = clazz.getFields();
        Field[] var20 = fields;
        int var19 = fields.length;

        for(var9 = 0; var9 < var19; ++var9) {
            final Field field = var20[var9];
            if (field.isAnnotationPresent(Property.class)) {
                Property field_annotation = (Property)field.getAnnotation(Property.class);
                ArrayList<String> l = new ArrayList(parent_path);
                if (field_annotation.value().length > 0) {
                    l.addAll(List.of(field_annotation.value()));
                } else {
                    l.add(Utils.toSnakeCase(field.getName()));
                }

                final String[] path = (String[])l.toArray((var0) -> {
                    return new String[var0];
                });
                final String name = field.getName();
                set.add(new IProperties<Object>() {
                    public String[] getPath() {
                        return path;
                    }

                    public String getName() {
                        return name;
                    }

                    public Object getValue() {
                        try {
                            return field.get(obj);
                        } catch (Exception var2) {
                            return null;
                        }
                    }
                });
            }
        }

        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {
            this.getProperties(set, superClass);
        }

        return (IProperties[])set.toArray((var0) -> {
            return new IProperties[var0];
        });
    }

    private Object[] getKeyType(String raw, Object value) {
        char firstChar = raw.charAt(0);
        String key = raw;
        switch (firstChar) {
            case 'A':
                key = raw.substring(1);
                value = new JSONArray();
                break;
            case 'O':
                key = raw.substring(1);
                value = new JSONObject(new TreeMap());
        }

        return new Object[]{key, value};
    }

    public String toString() {
        return this.toObject().toString();
    }

    private void writeData(Object o) {
        IProperties[] properties = this.getProperties(this);
        IProperties[] var6 = properties;
        int var5 = properties.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            IProperties<?> p = var6[var4];
            String[] path = p.getPath();
            this.logger.log(Level.INFO, "Path: {0}", Arrays.toString(p.getPath()));
            if (path.length == 0) {
                path = new String[]{Utils.toSnakeCase(p.getName())};
            }

            Object o1 = o;
            Object method_value = p.getValue();
            String[] var13 = path;
            int var12 = path.length;

            for(int var11 = 0; var11 < var12; ++var11) {
                String raw = var13[var11];
                Object[] keyType = this.getKeyType(raw, method_value);
                String key = (String)keyType[0];
                Object val = keyType[1];
                if (val instanceof JSONSerializable jSONSerializable) {
                    val = jSONSerializable.toObject();
                } else if (val instanceof Enum) {
                    val = val.toString();
                }

                if (o1 instanceof JSONObject jSONObject) {
                    Object o2 = jSONObject.opt(key);
                    if (o2 == null) {
                        jSONObject.put(key, val);
                        o2 = val;
                    }

                    if (o2 instanceof JSONObject || o2 instanceof JSONArray) {
                        o1 = o2;
                    }
                } else {
                    ((JSONArray)o1).put(val);
                    if (val instanceof JSONObject || val instanceof JSONArray) {
                        o1 = val;
                    }
                }
            }
        }

    }

    public Object toObject() {
        JSONObject o = new JSONObject(new TreeMap());
        this.writeData(o);
        return o;
    }

    interface IProperties<T> {
        String[] getPath();

        String getName();

        T getValue();
    }
}