package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.Color;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ColorMapperRequestNew extends ObjectMapper<Color, ColorDTO.ColorRequestNewDTO> {

    ColorMapperRequestNew converter = Mappers.getMapper(ColorMapperRequestNew.class);

}
