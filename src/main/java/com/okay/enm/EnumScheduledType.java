package com.okay.enm;

public enum EnumScheduledType {

    MIN1(1, "MIN"),
    MIN15(15, "MIN"),
    MIN30(30, "MIN"),
    MIN60(60, "MIN");

    public String name;
    public int duration;
    public String type;

    EnumScheduledType(int duration, String type) {
        this.duration = duration;
        this.type = type;
    }

    public static EnumScheduledType valueOfLabel(String label) {
        for (EnumScheduledType e : values()) {
            if (e.name.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
