package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.Brand;
import id.co.mandiri.repository.BrandRepository;
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
public class BrandDao implements DaoCrudDataTablesPattern<Brand, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand findId(String s) {
        return brandRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<Brand> findAll() {
        return null;
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public boolean remove(Brand brand) {
        brandRepository.delete(brand);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        brandRepository.delete(s);
        return true;
    }

    @Override
    public List<Brand> datatables(DataTablesRequest<Brand> params) {
        String baseQuery = "select *\n" +
                "from brand \n" +
                "where 1 = 1 ";

        Brand param = params.getValue();

        BrandQueryCompare compare = new BrandQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        String order = "desc";
        if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
            order="asc";
        
        switch (params.getColOrder().intValue()) {
            case 0:
                    query.append(" order by brand_id ").append(order).append(" ");
                break;
            case 1:
                    query.append(" order by brand_name ").append(order).append(" ");
                break;
            case 2:
                    query.append(" order by brand_description ").append(order).append(" ");
                break;
            default:
                query.append(" order by brand_id ").append(order).append(" ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());
 //auto Mapping to entity
        return this.jdbcTemplate.query(
                query.toString(), 
                values, 
                new BeanPropertyRowMapper(Brand.class));
    }

    @Override
    public Long datatables(Brand param) {
        String baseQuery = "select count(brand_id) as rows \n" +
                "from brand\n" +
                "where 1 = 1 ";

        BrandQueryCompare compare = new BrandQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong("rows")
        );

    }

    private class BrandQueryCompare implements QueryComparator<Brand> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        BrandQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(Brand param) {
            if (StringUtils.isNoneBlank(param.getBrandId())) {
                query.append(" and lower(brand_id) like :id ");
                parameterSource.addValue("id",
                        new StringBuilder("%")
                                .append(param.getBrandId().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getBrandName())) {
                query.append(" and lower(brand_name) like :name ");
                parameterSource.addValue("name", new StringBuilder("%")
                        .append(param.getBrandName().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getBrandDescription())) {
                query.append(" and lower(brand_description) like :description ");
                parameterSource.addValue("description", new StringBuilder("%")
                        .append(param.getBrandDescription().toLowerCase())
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
