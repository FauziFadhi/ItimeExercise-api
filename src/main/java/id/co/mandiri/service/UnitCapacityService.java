package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.UnitCapacityDao;
import id.co.mandiri.entity.UnitCapacity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UnitCapacityService implements ServiceCrudDataTablesPattern<UnitCapacity, String> {

    @Autowired
    private UnitCapacityDao unitCapacityDao;

    @Override
    public UnitCapacity findId(String s) {
        return unitCapacityDao.findId(s);
    }

    @Override
    public List<UnitCapacity> findAll() {
        return unitCapacityDao.findAll();
    }

    @Override
    @Transactional
    public UnitCapacity save(UnitCapacity value) {
        return unitCapacityDao.save(value);
    }

    @Override
    @Transactional
    public UnitCapacity update(UnitCapacity value) {
        return unitCapacityDao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(UnitCapacity value) {
        return unitCapacityDao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return unitCapacityDao.removeById(s);
    }

    @Override
    public DataTablesResponse<UnitCapacity> datatables(DataTablesRequest<UnitCapacity> params) {
        List<UnitCapacity> values = unitCapacityDao.datatables(params);
        Long rowCount = unitCapacityDao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
