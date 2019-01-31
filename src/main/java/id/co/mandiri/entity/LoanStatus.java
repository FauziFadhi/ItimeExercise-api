package id.co.mandiri.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "loan_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanStatus {

    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    @Column(name = "loan_status_id", nullable = false, length = 64)
    private String loanStatusId;
    
    @Column(name = "loan_status_name", nullable = false, length = 20)
    private String loanStatusName;
    
    @JsonIgnoreProperties
    @OneToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;
    
    @Lob
    @Type(type = "text")
    @Column(name = "loan_status_description")
    private String loanStatusDescription;
}
