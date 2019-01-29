package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapperRequestNew extends ObjectMapper<Brand, BrandDTO.BrandRequestNewDTO> {

    BrandMapperRequestNew converter = Mappers.getMapper(BrandMapperRequestNew.class);

}
