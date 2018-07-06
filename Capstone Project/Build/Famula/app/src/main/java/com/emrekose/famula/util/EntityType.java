package com.emrekose.famula.util;

public enum EntityType {
    CITY("city"),
    SUBZONE("subzone"),
    ZONE("zone"),
    LANDMARK("landmark"),
    METRO("metro"),
    GROUP("group");

    private String type;

    public String getType() {
        return type;
    }

    EntityType(String type) {
        this.type = type;
    }
}
