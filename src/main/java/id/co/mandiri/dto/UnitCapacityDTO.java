package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class UnitCapacityDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitCapacityRequestNewDTO {
        @NotNull
        private String name;
        private String code;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitCapacityRequestUpdateDTO {
        @NotNull
        private String id;
        @NotNull
        private String name;
        private String code;
        private String description;
    }

}
