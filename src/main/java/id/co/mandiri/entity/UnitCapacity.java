package id.co.mandiri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "unit_capacity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitCapacity {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "unit_capacity_id", nullable = false, length = 64)
    private String unitCapacityId;
    
    @Column(name = "unit_capacity_name", nullable = false, length = 20)
    private String unitCapacityName;

    @Column(name = "unit_capacity_code", nullable = false, length = 10)
    private String unitCapacityCode;
    
    @Lob
    @Type(type = "text")
    @Column(name = "unit_capacity_description")
    private String unitCapacityDescription;
}
