package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.LoanStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoanStatusMapperRequestUpdate extends ObjectMapper<LoanStatus, LoanStatusDTO.LoanStatusRequestUpdateDTO> {

    LoanStatusMapperRequestUpdate converter = Mappers.getMapper(LoanStatusMapperRequestUpdate.class);
}
