package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.CategoryDevice;
import id.co.mandiri.repository.CategoryDeviceRepository;
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
public class CategoryDeviceDao implements DaoCrudDataTablesPattern<CategoryDevice, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private CategoryDeviceRepository categoryDeviceRepository;

    @Override
    public CategoryDevice findId(String s) {
        return categoryDeviceRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<CategoryDevice> findAll() {
        return null;
    }

    @Override
    public CategoryDevice save(CategoryDevice categoryDevice) {
        return categoryDeviceRepository.save(categoryDevice);
    }

    @Override
    public CategoryDevice update(CategoryDevice categoryDevice) {
        return categoryDeviceRepository.save(categoryDevice);
    }

    @Override
    public boolean remove(CategoryDevice categoryDevice) {
        categoryDeviceRepository.delete(categoryDevice);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        categoryDeviceRepository.delete(s);
        return true;
    }

    @Override
    public List<CategoryDevice> datatables(DataTablesRequest<CategoryDevice> params) {
        String baseQuery = "select * \n" +
                "from device_category \n" +
                "where 1 = 1 ";

        CategoryDevice param = params.getValue();

        CategoryDeviceQueryCompare compare = new CategoryDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();
        
        String order = "desc";
        if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
            order="asc";
        
        switch (params.getColOrder().intValue()) {
            case 0:
                    query.append(" order by id ").append(order).append(" ");
                break;
            case 1:
                    query.append(" order by name ").append(order).append(" ");
                break;
            case 2:
                    query.append(" order by description ").append(order).append(" ");
                break;
            default:
                query.append(" order by id ").append(order).append(" ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());

        //auto Mapping to entity
        return this.jdbcTemplate.query(
                query.toString(), 
                values, 
                new BeanPropertyRowMapper(CategoryDevice.class));
    }

    @Override
    public Long datatables(CategoryDevice param) {
        String baseQuery = "select count(id) as rows \n" +
                "from device_category\n" +
                "where 1 = 1 ";

        CategoryDeviceQueryCompare compare = new CategoryDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong("rows")
        );

    }

    private class CategoryDeviceQueryCompare implements QueryComparator<CategoryDevice> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        CategoryDeviceQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(CategoryDevice param) {
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
