package id.co.mandiri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "color")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Color {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "color_id", nullable = false, length = 64)
    private String colorId;
    
    @Column(name = "color_name", nullable = false, length = 20)
    private String colorName;
    
    @Column(name = "color_code", nullable = false, length = 10)
    private String colorCode;
    
    @Lob
    @Type(type = "text")
    @Column(name = "color_description")
    private String colorDescription;
}
