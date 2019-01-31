package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class BrandDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BrandRequestNewDTO {
        @NotNull
        private String brandName;
        private String brandDescription;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BrandRequestUpdateDTO {
        @NotNull
        private String id;
        @NotNull
        private String brandName;
        private String brandDescription;
    }

}
