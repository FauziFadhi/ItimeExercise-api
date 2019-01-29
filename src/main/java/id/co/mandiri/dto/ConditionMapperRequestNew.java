package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConditionMapperRequestNew extends ObjectMapper<Condition, ConditionDTO.ConditionRequestNewDTO> {

    ConditionMapperRequestNew converter = Mappers.getMapper(ConditionMapperRequestNew.class);

}
