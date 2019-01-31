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
import org.springframework.jdbc.core.BeanPropertyRowMapper;

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
        String baseQuery = "select * \n" +
                "from unit_capacity \n" +
                "where 1 = 1 ";

        UnitCapacity param = params.getValue();

        UnitCapacityQueryCompare compare = new UnitCapacityQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        String order = "desc";
        if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
            order="asc";
        
        switch (params.getColOrder().intValue()) {
            case 0:
                    query.append(" order by unit_capacity_id ").append(order).append(" ");
                break;
            case 1:
                    query.append(" order by unit_capacity_name ").append(order).append(" ");
                break;
            case 2:
                    query.append(" order by unit_capacity_code ").append(order).append(" ");
                break;
            case 3:
                    query.append(" order by unit_capacity_description ").append(order).append(" ");
                break;
            default:
                query.append(" order by unit_capacity_id ").append(order).append(" ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());
 //auto Mapping to entity
        return this.jdbcTemplate.query(
                query.toString(), 
                values, 
                new BeanPropertyRowMapper(UnitCapacity.class));
    }

    @Override
    public Long datatables(UnitCapacity param) {
        String baseQuery = "select count(unit_capacity_id) as rows \n" +
                "from unit_capacity \n" +
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
            if (StringUtils.isNoneBlank(param.getUnitCapacityId())) {
                query.append(" and lower(unit_capacity_id) like :id ");
                parameterSource.addValue("id",
                        new StringBuilder("%")
                                .append(param.getUnitCapacityId().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getUnitCapacityName())) {
                query.append(" and lower(unit_capacity_name) like :name ");
                parameterSource.addValue("name", new StringBuilder("%")
                        .append(param.getUnitCapacityName().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getUnitCapacityDescription())) {
                query.append(" and lower(unit_capacity_description) like :description ");
                parameterSource.addValue("description", new StringBuilder("%")
                        .append(param.getUnitCapacityDescription().toLowerCase())
                        .append("%")
                        .toString());
            }
            
            if (StringUtils.isNoneBlank(param.getUnitCapacityCode())) {
                query.append(" and lower(unit_capacity_code) like :code ");
                parameterSource.addValue("code", new StringBuilder("%")
                        .append(param.getUnitCapacityCode().toLowerCase())
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
