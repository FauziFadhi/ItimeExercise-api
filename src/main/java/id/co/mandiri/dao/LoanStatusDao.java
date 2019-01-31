package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.Color;
import id.co.mandiri.entity.LoanStatus;
import id.co.mandiri.repository.LoanStatusRepository;
import id.co.mandiri.utils.QueryComparator;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class LoanStatusDao implements DaoCrudDataTablesPattern<LoanStatus, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private LoanStatusRepository loanStatusRepository;

    @Override
    public LoanStatus findId(String s) {
        return loanStatusRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<LoanStatus> findAll() {
        return null;
    }

    @Override
    public LoanStatus save(LoanStatus loanStatus) {
        return loanStatusRepository.save(loanStatus);
    }

    @Override
    public LoanStatus update(LoanStatus loanStatus) {
        return loanStatusRepository.save(loanStatus);
    }

    @Override
    public boolean remove(LoanStatus loanStatus) {
        loanStatusRepository.delete(loanStatus);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        loanStatusRepository.delete(s);
        return true;
    }

    @Override
    public List<LoanStatus> datatables(DataTablesRequest<LoanStatus> params) {
        String baseQuery = "select *\n" +
                "from loan_status left join color on loan_status.color_id=color.color_id \n" +
                "where 1 = 1 ";

        LoanStatus param = params.getValue();

        LoanStatusQueryCompare compare = new LoanStatusQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        switch (params.getColOrder().intValue()) {
            case 0:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by loan_status_id asc ");
                else
                    query.append(" order by loan_status_id desc ");
                break;
            case 1:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by loan_status_name asc ");
                else
                    query.append(" order by loan_status_name desc ");
                break;
            case 2:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by loan_status_description asc ");
                else
                    query.append(" order by loan_status_description desc ");
                break;
            default:
            if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                query.append(" order by loan_status_id asc ");
            else
                query.append(" order by loan_status_id desc ");
            break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());

//        return this.jdbcTemplate.query(query.toString(),values,new BeanPropertyRowMapper());
        return this.jdbcTemplate.query(query.toString(), values, new RowMapper<LoanStatus>() {
            @Override
            public LoanStatus mapRow(ResultSet resultSet, int i) throws SQLException {
                Color color = (new BeanPropertyRowMapper<>(Color.class)).mapRow(resultSet, i);
                LoanStatus loanStatus = (new BeanPropertyRowMapper<>(LoanStatus.class)).mapRow(resultSet, i);
                loanStatus.setColor(color);
                return loanStatus;
            }
        });
    }

    @Override
    public Long datatables(LoanStatus param) {
        String baseQuery = "select count(loan_status_id) as rows \n" +
                "from loan_status\n" +
                "where 1 = 1 ";

        LoanStatusQueryCompare compare = new LoanStatusQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong("rows")
        );

    }

    private class LoanStatusQueryCompare implements QueryComparator<LoanStatus> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        LoanStatusQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(LoanStatus param) {
            if (StringUtils.isNoneBlank(param.getLoanStatusId())) {
                query.append(" and lower(loan_status_id) like :id ");
                parameterSource.addValue("id",
                        new StringBuilder("%")
                                .append(param.getLoanStatusId().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getLoanStatusName())) {
                query.append(" and lower(loan_status_name) like :name ");
                parameterSource.addValue("name", new StringBuilder("%")
                        .append(param.getLoanStatusName().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getLoanStatusDescription())) {
                query.append(" and lower(loan_status_description) like :description ");
                parameterSource.addValue("description", new StringBuilder("%")
                        .append(param.getLoanStatusDescription().toLowerCase())
                        .append("%")
                        .toString());
            }
            
            return query;
        }

        @Override
        public MapSqlParameterSource getParameters() {
            return this.parameterSource;
        }
    }
}
