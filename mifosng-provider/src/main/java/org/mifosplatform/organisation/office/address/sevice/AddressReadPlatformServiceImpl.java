package org.mifosplatform.organisation.office.address.sevice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.office.address.data.AddressData;
import org.mifosplatform.organisation.office.address.exception.AddressNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class AddressReadPlatformServiceImpl implements AddressReadPlatformService {

    private final JdbcTemplate jdbcTemplate;
    private final PlatformSecurityContext context;
    // private final CurrencyReadPlatformService currencyReadPlatformService;
    // private final static String nameDecoratedBaseOnHierarchy =
    // "concat(substring('........................................', 1,
    // ((LENGTH(o.hierarchy) - LENGTH(REPLACE(o.hierarchy, '.', '')) - 1) * 4)),
    // o.name)";

    @Autowired
    public AddressReadPlatformServiceImpl(final PlatformSecurityContext context, final RoutingDataSource dataSource) {
        this.context = context;

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class AddressMapper implements RowMapper<AddressData> {

        public String addressSchema() {
            return " a.id as id, a.line_1 as line_1, "
                    + " o.name as office_name, a.line_2 as line_2, a.city as city, a.state as state, a.type "
                    + "from m_address a  JOIN m_office AS office ON a.office_id = o.id ";
        }

        @Override
        public AddressData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long id = rs.getLong("id");
            final Long office_id = rs.getLong("office_id");
            final String line_1 = rs.getString("line_1");
            final String line_2 = rs.getString("line_2");
            final String city = rs.getString("city");
            final String state = rs.getString("state");
            final Integer type = JdbcSupport.getInteger(rs, "type");

            return new AddressData(id, office_id, line_1, line_2, city, state, type);
        }
    }

    @Override
    public Collection<AddressData> retrieveAddresses(Long officeId) {
        AddressMapper rm = new AddressMapper();

        final StringBuilder sqlBuilder = new StringBuilder(200);
        sqlBuilder.append("select ");
        sqlBuilder.append(rm.addressSchema());
        sqlBuilder.append(" where o.id = ? ");

        return this.jdbcTemplate.query(sqlBuilder.toString(), rm, new Object[] { officeId });
    }

    @Override
    public AddressData retrieveAddress(Long officeId,Long id) {
        try {
            this.context.authenticatedUser();

            final AddressMapper rm = new AddressMapper();
            final String sql = "select " + rm.addressSchema() + " where o.id = ? and a.id=?";

            final AddressData selectedOffice = this.jdbcTemplate.queryForObject(sql, rm, new Object[] { officeId , id});

            return selectedOffice;
        } catch (final EmptyResultDataAccessException e) {
            throw new AddressNotFoundException(officeId,id);
        }
    }

    @Override
    public AddressData retrieveNewAddressTemplate() {
        // TODO Auto-generated method stub
        return null;
    }
}
