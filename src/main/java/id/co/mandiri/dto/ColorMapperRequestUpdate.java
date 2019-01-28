package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.Color;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ColorMapperRequestUpdate extends ObjectMapper<Color, ColorDTO.ColorRequestUpdateDTO> {

    ColorMapperRequestUpdate converter = Mappers.getMapper(ColorMapperRequestUpdate.class);
}
