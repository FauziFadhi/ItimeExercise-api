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
    @Column(name = "condition_id", nullable = false, length = 64)
    private String conditionId;
    
    @Column(name = "condition_name", nullable = false, length = 20)
    private String conditionName;
    
    @Lob
    @Type(type = "text")
    @Column(name = "condition_description")
    private String conditionDescription;
}
