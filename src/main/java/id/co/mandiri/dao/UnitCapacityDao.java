package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.UnitCapacity;
import id.co.mandiri.repository.UnitCapacityRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UnitCapacityDao implements DaoCrudDataTablesPattern<UnitCapacity, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private UnitCapacityRepository unitCapacityRepository;

    @Override
    public UnitCapacity findId(String s) {
        return unitCapacityRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<UnitCapacity> findAll() {
        return null;
    }

    @Override
    public UnitCapacity save(UnitCapacity unitCapacity) {
        return unitCapacityRepository.save(unitCapacity);
    }

    @Override
    public UnitCapacity update(UnitCapacity unitCapacity) {
        return unitCapacityRepository.save(unitCapacity);
    }

    @Override
    public boolean remove(UnitCapacity unitCapacity) {
        unitCapacityRepository.delete(unitCapacity);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        unitCapacityRepository.delete(s);
        return true;
    }

    @Override
    public List<UnitCapacity> datatables(DataTablesRequest<UnitCapacity> params) {
        String baseQuery = "select id, name, code, description\n" +
                "from unit_capacity\n" +
                "where 1 = 1 ";

        UnitCapacity param = params.getValue();

        UnitCapacityQueryCompare compare = new UnitCapacityQueryCompare(baseQuery);
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
            case 3:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by code asc ");
                else
                    query.append(" order by code desc ");
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
                new UnitCapacity(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code"),
                        resultSet.getString("description")
                ));
    }

    @Override
    public Long datatables(UnitCapacity param) {
        String baseQuery = "select count(id) as rows \n" +
                "from unit_capacity\n" +
                "where 1 = 1 ";

        UnitCapacityQueryCompare compare = new UnitCapacityQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong("rows")
        );

    }

    private class UnitCapacityQueryCompare implements QueryComparator<UnitCapacity> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        UnitCapacityQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(UnitCapacity param) {
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
            
            if (StringUtils.isNoneBlank(param.getDescription())) {
                query.append(" and lower(code) like :code ");
                parameterSource.addValue("code", new StringBuilder("%")
                        .append(param.getCode().toLowerCase())
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
