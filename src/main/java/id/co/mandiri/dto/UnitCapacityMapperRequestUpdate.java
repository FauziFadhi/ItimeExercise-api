package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.UnitCapacity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UnitCapacityMapperRequestUpdate extends ObjectMapper<UnitCapacity, UnitCapacityDTO.UnitCapacityRequestUpdateDTO> {

    UnitCapacityMapperRequestUpdate converter = Mappers.getMapper(UnitCapacityMapperRequestUpdate.class);
}
