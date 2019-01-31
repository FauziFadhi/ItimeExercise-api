package id.co.mandiri.dto;

import id.co.mandiri.entity.Color;
import id.co.mandiri.entity.Brand;
import id.co.mandiri.entity.CategoryDevice;
import id.co.mandiri.entity.Condition;
import id.co.mandiri.entity.UnitCapacity;
import id.co.mandiri.entity.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class DeviceDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceRequestNewDTO {
        @NotNull
        private String deviceName;
        private String deviceDescription;
        private Color color;
        private Brand brand;
        private CategoryDevice categoryDevice;
        private Condition condition;
        private UnitCapacity unitCapacity;
        private LoanStatus LoanStatus;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceRequestUpdateDTO {
        @NotNull
        private int deviceNumber;
        @NotNull
        private String deviceName;
        private String deviceDescription;
        private Color color;
        private Brand brand;
        private CategoryDevice categoryDevice;
        private Condition condition;
        private UnitCapacity unitCapacity;
        private LoanStatus loanStatus;
    }

}
