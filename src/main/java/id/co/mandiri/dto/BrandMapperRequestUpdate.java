package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapperRequestUpdate extends ObjectMapper<Brand, BrandDTO.BrandRequestUpdateDTO> {

    BrandMapperRequestUpdate converter = Mappers.getMapper(BrandMapperRequestUpdate.class);
}
