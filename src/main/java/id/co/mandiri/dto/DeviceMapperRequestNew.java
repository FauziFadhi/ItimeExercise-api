package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.Device;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceMapperRequestNew extends ObjectMapper<Device, DeviceDTO.DeviceRequestNewDTO> {

    DeviceMapperRequestNew converter = Mappers.getMapper(DeviceMapperRequestNew.class);

}
