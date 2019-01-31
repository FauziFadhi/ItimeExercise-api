package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.Condition;
import id.co.mandiri.repository.ConditionRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.util.Optional;

@Repository
public class ConditionDao implements DaoCrudDataTablesPattern<Condition, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private ConditionRepository conditionRepository;

    @Override
    public Condition findId(String s) {
        return conditionRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<Condition> findAll() {
        return null;
    }

    @Override
    public Condition save(Condition condition) {
        return conditionRepository.save(condition);
    }

    @Override
    public Condition update(Condition condition) {
        return conditionRepository.save(condition);
    }

    @Override
    public boolean remove(Condition condition) {
        conditionRepository.delete(condition);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        conditionRepository.delete(s);
        return true;
    }

    @Override
    public List<Condition> datatables(DataTablesRequest<Condition> params) {
        String baseQuery = "select *\n" +
                "from conditions\n" +
                "where 1 = 1 ";

        Condition param = params.getValue();

        ConditionQueryCompare compare = new ConditionQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        String order = "desc";
        if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
            order="asc";
        
        switch (params.getColOrder().intValue()) {
            case 0:
                    query.append(" order by condition_id ").append(order).append(" ");
                break;
            case 1:
                    query.append(" order by condition_name ").append(order).append(" ");
                break;
            case 2:
                    query.append(" order by condition_description ").append(order).append(" ");
                break;
            default:
                query.append(" order by condition_id ").append(order).append(" ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());
 //auto Mapping to entity
        return this.jdbcTemplate.query(
                query.toString(), 
                values, 
                new BeanPropertyRowMapper(Condition.class));
    }

    @Override
    public Long datatables(Condition param) {
        String baseQuery = "select count(condition_id) as rows \n" +
                "from conditions\n" +
                "where 1 = 1 ";

        ConditionQueryCompare compare = new ConditionQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong("rows")
        );

    }

    private class ConditionQueryCompare implements QueryComparator<Condition> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        ConditionQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(Condition param) {
            if (StringUtils.isNoneBlank(param.getConditionId())) {
                query.append(" and lower(condition_id) like :id ");
                parameterSource.addValue("id",
                        new StringBuilder("%")
                                .append(param.getConditionId().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getConditionName())) {
                query.append(" and lower(condition_name) like :name ");
                parameterSource.addValue("name", new StringBuilder("%")
                        .append(param.getConditionName().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getConditionDescription())) {
                query.append(" and lower(condition_description) like :description ");
                parameterSource.addValue("description", new StringBuilder("%")
                        .append(param.getConditionDescription().toLowerCase())
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
