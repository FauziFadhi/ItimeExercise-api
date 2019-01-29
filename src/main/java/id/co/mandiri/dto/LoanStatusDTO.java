package id.co.mandiri.dto;

import id.co.mandiri.entity.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class LoanStatusDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoanStatusRequestNewDTO {
        @NotNull
        private String name;
        private String description;
        private Color color;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoanStatusRequestUpdateDTO {
        @NotNull
        private String id;
        @NotNull
        private String name;
        private String description;
        private Color color;
        
    }

}
