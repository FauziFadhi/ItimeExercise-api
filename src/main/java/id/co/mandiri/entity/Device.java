package id.co.mandiri.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "device")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    // @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    // @GeneratedValue(generator = "uuid_gen")
    @Id
    @Column(name = "device_number", nullable = false, length = 64)
    private int deviceNumber;
    
    @Column(name = "device_name", nullable = false, length = 20)
    private String deviceName;
    
    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;
    
    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
    
    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "unit_capacity_id", nullable = false)
    private UnitCapacity unitCapacity;
    
    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "category_device_id", nullable = false)
    private CategoryDevice categoryDevice;
    
    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "condition_id", nullable = false)
    private Condition condition;
    
    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "loan_status_id", nullable = false)
    private LoanStatus LoanStatus;
    
    @Lob
    @Type(type = "text")
    @Column(name = "device_description")
    private String deviceDescription;
}
