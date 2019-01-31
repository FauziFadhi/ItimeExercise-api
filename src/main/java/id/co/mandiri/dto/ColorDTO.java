package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class ColorDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ColorRequestNewDTO {
        @NotNull
        private String colorName;
        private String colorCode;
        private String colorDescription;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ColorRequestUpdateDTO {
        @NotNull
        private String id;
        @NotNull
        private String colorName;
        private String colorCode;
        private String colorDescription;
    }

}
