package com.emrekose.famula.model.establisments;

import com.google.gson.annotations.SerializedName;

public class Establishment {

    @SerializedName("establishment")
    private EstablishmentItem establishment;

    public EstablishmentItem getEstablishment() {
        return establishment;
    }

    public void setEstablishment(EstablishmentItem establishment) {
        this.establishment = establishment;
    }
}
