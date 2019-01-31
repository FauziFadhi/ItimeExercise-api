package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.Device;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceMapperRequestUpdate extends ObjectMapper<Device, DeviceDTO.DeviceRequestUpdateDTO> {

    DeviceMapperRequestUpdate converter = Mappers.getMapper(DeviceMapperRequestUpdate.class);
}
