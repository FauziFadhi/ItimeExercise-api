package id.co.mandiri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "conditions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Condition {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "id", nullable = false, length = 64)
    private String id;
    
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    
    @Lob
    @Type(type = "text")
    @Column(name = "description")
    private String description;
}
