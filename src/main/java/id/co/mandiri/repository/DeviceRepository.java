package id.co.mandiri.repository;

import id.co.mandiri.entity.Device;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Integer> {
    
    public List<Device> findByName(String name);
    
}
