package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.ColorDao;
import id.co.mandiri.entity.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ColorService implements ServiceCrudDataTablesPattern<Color, String> {

    @Autowired
    private ColorDao colorDao;

    @Override
    public Color findId(String s) {
        return colorDao.findId(s);
    }

    @Override
    public List<Color> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Color save(Color value) {
        return colorDao.save(value);
    }

    @Override
    @Transactional
    public Color update(Color value) {
        return colorDao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(Color value) {
        return colorDao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return colorDao.removeById(s);
    }

    @Override
    public DataTablesResponse<Color> datatables(DataTablesRequest<Color> params) {
        List<Color> values = colorDao.datatables(params);
        Long rowCount = colorDao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
