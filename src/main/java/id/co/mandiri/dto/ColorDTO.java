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
        private String name;
        private String code;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ColorRequestUpdateDTO {
        @NotNull
        private String id;
        @NotNull
        private String name;
        private String code;
        private String description;
    }

}
