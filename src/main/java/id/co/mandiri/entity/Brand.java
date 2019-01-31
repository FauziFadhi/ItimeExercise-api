package id.co.mandiri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "brand")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "brand_id", nullable = false, length = 64)
    private String brandId;
    
    @Column(name = "brand_name", nullable = false, length = 20)
    private String brandName;
    
    @Lob
    @Type(type = "text")
    @Column(name = "brand_description")
    private String brandDescription;
}
