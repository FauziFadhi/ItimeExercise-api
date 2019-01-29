package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.ConditionDao;
import id.co.mandiri.entity.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ConditionService implements ServiceCrudDataTablesPattern<Condition, String> {

    @Autowired
    private ConditionDao conditionDao;

    @Override
    public Condition findId(String s) {
        return conditionDao.findId(s);
    }

    @Override
    public List<Condition> findAll() {
        return conditionDao.findAll();
    }

    @Override
    @Transactional
    public Condition save(Condition value) {
        return conditionDao.save(value);
    }

    @Override
    @Transactional
    public Condition update(Condition value) {
        return conditionDao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(Condition value) {
        return conditionDao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return conditionDao.removeById(s);
    }

    @Override
    public DataTablesResponse<Condition> datatables(DataTablesRequest<Condition> params) {
        List<Condition> values = conditionDao.datatables(params);
        Long rowCount = conditionDao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
