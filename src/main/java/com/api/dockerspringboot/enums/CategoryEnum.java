package com.api.dockerspringboot.enums;

public enum CategoryEnum {
    ELETRONICS(0, "eletronics"),AUTOMOTIVE(1, "automotive")
    ,BABY(1, "baby"),HEALTH(1, "health");

    private final int value;
    private final String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    private CategoryEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static CategoryEnum getEnum(int value) {
        for (CategoryEnum a : CategoryEnum.values()) {

            if (a.getValue() == value) {
                return a;
            }
        }
        return null;
    }

    public static CategoryEnum getEnumByDescription(String value) {
        for (CategoryEnum a : CategoryEnum.values()) {

            if (a.getDescription().equalsIgnoreCase(value)) {
                return a;
            }
        }
        return null;
    }
}
