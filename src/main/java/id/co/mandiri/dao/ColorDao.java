package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.Color;
import id.co.mandiri.repository.ColorRepository;
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
public class ColorDao implements DaoCrudDataTablesPattern<Color, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public Color findId(String s) {
        return colorRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<Color> findAll() {
        return null;
    }

    @Override
    public Color save(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public Color update(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public boolean remove(Color color) {
        colorRepository.delete(color);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        colorRepository.delete(s);
        return true;
    }

    @Override
    public List<Color> datatables(DataTablesRequest<Color> params) {
        String baseQuery = "select * \n" +
                "from color\n" +
                "where 1 = 1 ";

        Color param = params.getValue();

        ColorQueryCompare compare = new ColorQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        String order = "desc";
        if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
            order="asc";
        
        switch (params.getColOrder().intValue()) {
            case 0:
                    query.append(" order by color_id ").append(order).append(" ");
                break;
            case 1:
                    query.append(" order by color_name ").append(order).append(" ");
                break;
            case 2:
                    query.append(" order by color_code ").append(order).append(" ");
                break;
            case 3:
                    query.append(" order by color_description ").append(order).append(" ");
                break;
            default:
                query.append(" order by color_id ").append(order).append(" ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());
 //auto Mapping to entity
        return this.jdbcTemplate.query(
                query.toString(), 
                values, 
                new BeanPropertyRowMapper(Color.class));
    }

    @Override
    public Long datatables(Color param) {
        String baseQuery = "select count(color_id) as rows \n" +
                "from color\n" +
                "where 1 = 1 ";

        ColorQueryCompare compare = new ColorQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong("rows")
        );

    }

    private class ColorQueryCompare implements QueryComparator<Color> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        ColorQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(Color param) {
            if (StringUtils.isNoneBlank(param.getColorId())) {
                query.append(" and lower(color_id) like :id ");
                parameterSource.addValue("id",
                        new StringBuilder("%")
                                .append(param.getColorId().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getColorName())) {
                query.append(" and lower(color_name) like :name ");
                parameterSource.addValue("name", new StringBuilder("%")
                        .append(param.getColorName().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getColorDescription())) {
                query.append(" and lower(color_description) like :description ");
                parameterSource.addValue("description", new StringBuilder("%")
                        .append(param.getColorDescription().toLowerCase())
                        .append("%")
                        .toString());
            }
            
            if (StringUtils.isNoneBlank(param.getColorCode())) {
                query.append(" and lower(color_code) like :code ");
                parameterSource.addValue("code", new StringBuilder("%")
                        .append(param.getColorCode().toLowerCase())
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
