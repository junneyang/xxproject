package com.xcompany.xproject.auth.server.serializers;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DeviceDto {
    @NotNull
    @Size(min = 15, max=20)
    private String imei;

    @NotNull
    @Size(min = 1)
    private String name;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("DeviceDto [imei=").append(imei).append(", name=").append(name).append("]");
        return builder.toString();
    }
}

