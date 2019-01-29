package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.LoanStatusDao;
import id.co.mandiri.entity.LoanStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LoanStatusService implements ServiceCrudDataTablesPattern<LoanStatus, String> {

    @Autowired
    private LoanStatusDao loanStatusDao;

    @Override
    public LoanStatus findId(String s) {
        return loanStatusDao.findId(s);
    }

    @Override
    public List<LoanStatus> findAll() {
        return loanStatusDao.findAll();
    }

    @Override
    @Transactional
    public LoanStatus save(LoanStatus value) {
        return loanStatusDao.save(value);
    }

    @Override
    @Transactional
    public LoanStatus update(LoanStatus value) {
        return loanStatusDao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(LoanStatus value) {
        return loanStatusDao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return loanStatusDao.removeById(s);
    }

    @Override
    public DataTablesResponse<LoanStatus> datatables(DataTablesRequest<LoanStatus> params) {
        List<LoanStatus> values = loanStatusDao.datatables(params);
        Long rowCount = loanStatusDao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
