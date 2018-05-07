package com.devglan.gatewayservice.constant;

/**
 * Created by abhinav.gupta on 8/2/2016.
 */

public enum EnumImageFormat {

    JPG(1L, "jpg" ),
    PNG(2L, "png");

    private Long id;
    private String extension;

    EnumImageFormat(Long id, String extension) {

        this.id = id;
        this.extension  = extension;
    }

    public static EnumImageFormat getImageFormatEnumById(Long imageFormatId) {
        for (EnumImageFormat enumImageFormat : EnumImageFormat.values()) {
            if (enumImageFormat.id.equals(imageFormatId)) {
                return enumImageFormat;
            }
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
