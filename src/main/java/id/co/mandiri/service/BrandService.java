package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.BrandDao;
import id.co.mandiri.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BrandService implements ServiceCrudDataTablesPattern<Brand, String> {

    @Autowired
    private BrandDao brandDao;

    @Override
    public Brand findId(String s) {
        return brandDao.findId(s);
    }

    @Override
    public List<Brand> findAll() {
        return brandDao.findAll();
    }

    @Override
    @Transactional
    public Brand save(Brand value) {
        return brandDao.save(value);
    }

    @Override
    @Transactional
    public Brand update(Brand value) {
        return brandDao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(Brand value) {
        return brandDao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return brandDao.removeById(s);
    }

    @Override
    public DataTablesResponse<Brand> datatables(DataTablesRequest<Brand> params) {
        List<Brand> values = brandDao.datatables(params);
        Long rowCount = brandDao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
