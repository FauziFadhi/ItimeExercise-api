package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.Color;
import id.co.mandiri.entity.LoanStatus;
import id.co.mandiri.repository.LoanStatusRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
        String baseQuery = "select ls.id, ls.name, c.name, c.code, ls.description, color_id\n" +
                "from loan_status ls left join color c on ls.color_id=c.id \n" +
                "where 1 = 1 ";

        LoanStatus param = params.getValue();

        LoanStatusQueryCompare compare = new LoanStatusQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        switch (params.getColOrder().intValue()) {
            case 0:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by id asc ");
                else
                    query.append(" order by id desc ");
                break;
            case 1:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by name asc ");
                else
                    query.append(" order by name desc ");
                break;
            case 2:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by description asc ");
                else
                    query.append(" order by description desc ");
                break;
            default:
            if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                query.append(" order by id asc ");
            else
                query.append(" order by id desc ");
            break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());

        return this.jdbcTemplate.query(query.toString(), values, (resultSet, i) ->
                new LoanStatus(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getObject("color_id", Color.class),
                        resultSet.getString("description")
                ));
    }

    @Override
    public Long datatables(LoanStatus param) {
        String baseQuery = "select count(id) as rows \n" +
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
            if (StringUtils.isNoneBlank(param.getId())) {
                query.append(" and lower(id) like :id ");
                parameterSource.addValue("id",
                        new StringBuilder("%")
                                .append(param.getId().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getName())) {
                query.append(" and lower(name) like :name ");
                parameterSource.addValue("name", new StringBuilder("%")
                        .append(param.getName().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getDescription())) {
                query.append(" and lower(description) like :description ");
                parameterSource.addValue("description", new StringBuilder("%")
                        .append(param.getDescription().toLowerCase())
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
