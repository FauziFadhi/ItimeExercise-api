package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.DeviceDao;
import id.co.mandiri.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DeviceService implements ServiceCrudDataTablesPattern<Device, Integer> {

    @Autowired
    private DeviceDao deviceDao;

    @Override
    public Device findId(Integer s) {
        return deviceDao.findId(s);
    }

    @Override
    public List<Device> findAll() {
        return deviceDao.findAll();
    }

    @Override
    @Transactional
    public Device save(Device value) {
        return deviceDao.save(value);
    }

    @Override
    @Transactional
    public Device update(Device value) {
        return deviceDao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(Device value) {
        return deviceDao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(Integer s) {
        return deviceDao.removeById(s);
    }

    @Override
    public DataTablesResponse<Device> datatables(DataTablesRequest<Device> params) {
        List<Device> values = deviceDao.datatables(params);
        Long rowCount = deviceDao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }

   
}
