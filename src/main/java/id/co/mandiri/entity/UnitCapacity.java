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
    @Column(name = "id", nullable = false, length = 64)
    private String id;
    
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "code", nullable = false, length = 10)
    private String code;
    
    @Lob
    @Type(type = "text")
    @Column(name = "description")
    private String description;
}
