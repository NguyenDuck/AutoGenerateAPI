package io.vn.nguyenduck.auto_generate_api;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

public class SemVer implements Comparable<SemVer> {
    int Major;
    int Minor;
    int Patch;

    public SemVer(int minor) {
        this(minor, 0);
    }

    public SemVer(int minor, int patch) {
        this(1, minor, patch);
    }

    public SemVer(int major, int minor, int patch) {
        Major = major;
        Minor = minor;
        Patch = patch;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SemVer && Objects.equals(toString(), obj.toString());
    }

    @Override
    public int compareTo(@NotNull SemVer o) {
        var m1 = Major - o.Major;
        if (m1 != 0)
            return m1;
        var m2 = Minor - o.Minor;
        if (m2 != 0)
            return m2;
        return Patch - o.Patch;
    }

    @Override
    public String toString() {
        return "%d.%d.%d".formatted(Major, Minor, Patch);
    }
}
