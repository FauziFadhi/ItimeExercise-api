package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.Device;
import id.co.mandiri.repository.DeviceRepository;
import id.co.mandiri.utils.QueryComparator;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import id.co.mandiri.entity.Color;
import id.co.mandiri.entity.Brand;
import id.co.mandiri.entity.CategoryDevice;
import id.co.mandiri.entity.Condition;
import id.co.mandiri.entity.UnitCapacity;
import id.co.mandiri.entity.LoanStatus;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class DeviceDao implements DaoCrudDataTablesPattern<Device, Integer> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private DeviceRepository deviceRepository;

    // public List<Device> findByName(String n){
    //     String query = "select *"
    //             + "from device"
    //             + "where device_name like %"+n+"%";
    //     return this.jdbcTemplate.query(query,new BeanPropertyRowMapper(Device.class));

    // }
    
    @Override
    public Device findId(Integer s) {
        return deviceRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<Device> findAll() {
        return null;
    }

    @Override
    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Device update(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public boolean remove(Device device) {
        deviceRepository.delete(device);
        return true;
    }

    @Override
    public boolean removeById(Integer s) {
        deviceRepository.delete(s);
        return true;
    }

    @Override
    public List<Device> datatables(DataTablesRequest<Device> params) {
        String baseQuery = "select *\n" +
                "from device "+
                "left join color on device.color_id = color.color_id " +
                "left join conditions on device.condition_id = conditions.condition_id " +
                "left join loan_status on device.loan_status_id = loan_status.loan_status_id " +
                "left join unit_capacity on device.unit_capacity_id = unit_capacity.unit_capacity_id " +
                "left join brand on device.brand_id = brand.brand_id " +
                "left join device_category on device.category_device_id = device_category.id " +
                "where 1 = 1 ";

        Device param = params.getValue();

        DeviceQueryCompare compare = new DeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        String order = "desc";
        if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
            order="asc";    
        
        switch (params.getColOrder().intValue()) {
            case 0:
                    query.append(" order by device_number ").append(order).append(" ");
                break;
            case 1:
                    query.append(" order by device_name ").append(order).append(" ");
                break;
            case 2:
                    query.append(" order by loan_status_name ").append(order).append(" ");
                break;
            default:
                query.append(" order by device_id ").append(order).append(" ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());

//        return this.jdbcTemplate.query(query.toString(),values,new BeanPropertyRowMapper());
        System.out.println("qureyyyyyyyyyyyyy"+query);
        return this.jdbcTemplate.query(query.toString(), values, new RowMapper<Device>() {
            @Override
            public Device mapRow(ResultSet resultSet, int i) throws SQLException {
                Color color = (new BeanPropertyRowMapper<>(Color.class)).mapRow(resultSet, i);
                LoanStatus loanStatus = (new BeanPropertyRowMapper<>(LoanStatus.class)).mapRow(resultSet, i);
                Condition condition = (new BeanPropertyRowMapper<>(Condition.class)).mapRow(resultSet, i);
                CategoryDevice categoryDevice = (new BeanPropertyRowMapper<>(CategoryDevice.class)).mapRow(resultSet, i);
                UnitCapacity unitCapacity = (new BeanPropertyRowMapper<>(UnitCapacity.class)).mapRow(resultSet, i);
                Brand brand = (new BeanPropertyRowMapper<>(Brand.class)).mapRow(resultSet, i);
                Device device = (new BeanPropertyRowMapper<>(Device.class)).mapRow(resultSet, i);
                device.setColor(color);
                device.setBrand(brand);
                device.setCategoryDevice(categoryDevice);
                device.setCondition(condition);
                device.setUnitCapacity(unitCapacity);
                device.setLoanStatus(loanStatus);
                return device;
            }
        });
    }

    @Override
    public Long datatables(Device param) {
        String baseQuery = "select count(device_number) as rows \n" +
                "from device\n" +
                "where 1 = 1 ";

        DeviceQueryCompare compare = new DeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();
        System.out.print("GASASSAS"+query);
        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong("rows")
        );

    }

    private class DeviceQueryCompare implements QueryComparator<Device> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        DeviceQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(Device param) {
            if (param.getDeviceNumber() > 0) {
                query.append(" and device_number like :id ");
                parameterSource.addValue("id",
                        new StringBuilder("%")
                                .append(param.getDeviceNumber())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getDeviceName())) {
                query.append(" and lower(device_name) like :name ");
                parameterSource.addValue("name", new StringBuilder("%")
                        .append(param.getDeviceName().toLowerCase())
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
