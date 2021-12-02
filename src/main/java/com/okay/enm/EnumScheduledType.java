package com.okay.enm;

public enum EnumScheduledType {

    MIN_1("1MIN", 1, "MIN"),
    MIN_15("15MIN", 15, "MIN"),
    MIN_30("30MIN", 30, "MIN"),
    MIN_60("60MIN", 60, "MIN");

    public String value;
    public String name;
    public int duration;
    public String type;

    EnumScheduledType(String value, int duration, String type) {
        this.value = value;
        this.duration = duration;
        this.type = type;
    }

    public static EnumScheduledType valueOfLabel(String label) {
        for (EnumScheduledType e : values()) {
            if (e.value.equals(label)) {
                return e;
            }
        }
        return null;
    }

    public String getName() {
        return value;
    }
}
