package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class ConditionDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ConditionRequestNewDTO {
        @NotNull
        private String conditionName;
        private String conditionDescription;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ConditionRequestUpdateDTO {
        @NotNull
        private String conditionId;
        @NotNull
        private String conditionName;
        private String conditionDescription;
    }

}
