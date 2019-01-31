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
        private String unitCapacityName;
        private String unitCapacityCode;
        private String unitCapacityDescription;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitCapacityRequestUpdateDTO {
        @NotNull
        private String unitCapacityId;
        @NotNull
        private String unitCapacityName;
        private String unitCapacityCode;
        private String unitCapacityDescription;
    }

}
