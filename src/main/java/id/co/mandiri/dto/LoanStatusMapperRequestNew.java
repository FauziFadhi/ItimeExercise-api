package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.LoanStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoanStatusMapperRequestNew extends ObjectMapper<LoanStatus, LoanStatusDTO.LoanStatusRequestNewDTO> {

    LoanStatusMapperRequestNew converter = Mappers.getMapper(LoanStatusMapperRequestNew.class);

}
