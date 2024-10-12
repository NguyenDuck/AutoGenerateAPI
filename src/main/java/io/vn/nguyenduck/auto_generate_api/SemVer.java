package io.vn.nguyenduck.auto_generate_api;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

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
        this.Major = major;
        this.Minor = minor;
        this.Patch = patch;
    }

    public boolean equals(Object obj) {
        return obj instanceof SemVer && Objects.equals(this.toString(), obj.toString());
    }

    public int compareTo(@NotNull SemVer o) {
        int m1 = this.Major - o.Major;
        if (m1 != 0) {
            return m1;
        } else {
            int m2 = this.Minor - o.Minor;
            return m2 != 0 ? m2 : this.Patch - o.Patch;
        }
    }

    public String toString() {
        return "%d.%d.%d".formatted(this.Major, this.Minor, this.Patch);
    }
}