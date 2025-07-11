import cn.hutool.json.JSONObject;
import com.example.Main;
import com.example.entity.EmbeddedTest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.*;
import java.util.Iterator;
import java.util.stream.Stream;

@SpringBootTest(classes = Main.class)
@Slf4j
public class JdbcTemplateTest {

    @Resource
    private JdbcTemplate jdbcTemplate;

    private static final String sql = "select * from embedded_test";

    @Test
    public void queryForStreamTest() {
        RowMapper<EmbeddedTest> rowMapper = new RowMapper<>() {
            @Nullable
            @Override
            public EmbeddedTest mapRow(ResultSet rs, int rowNum) throws SQLException {
                return EmbeddedTest.builder()
                        .versionId(rs.getString("version_id"))
                        .tableId(rs.getLong("table_id"))
                        .tableSchema(rs.getString("table_schema"))
                        .tableName(rs.getString("table_name"))
                        .valueA(rs.getString("value_a"))
                        .valueB(rs.getString("value_b"))
                        .valueC(rs.getString("value_c"))
                        .build();
            }
        };
        try (Stream<EmbeddedTest> stream = jdbcTemplate.queryForStream(sql, rowMapper)){
            Iterator<EmbeddedTest> iterator = stream.iterator();
            while (iterator.hasNext()) {
                EmbeddedTest embeddedTest = iterator.next();
                System.out.println(embeddedTest);
            }
//            stream.forEach(System.out::println);
        }
    }

    @Test
    public void queryForStreamTest2() {
        jdbcTemplate.execute(new ConnectionCallback<EmbeddedTest>() {
            @Override
            public EmbeddedTest doInConnection(Connection con) throws SQLException, DataAccessException {
                ResultSetExtractor<EmbeddedTest> rse = new ResultSetExtractor<EmbeddedTest>() {
                    @Override
                    public EmbeddedTest extractData(ResultSet rs) throws SQLException, DataAccessException {
                        ResultSetMetaData resultSetMetaData = rs.getMetaData();// 该表数据结构
                        while (rs.next()) {
                            // 处理每条数据
                            System.out.println(new JSONObject(rs));
                        }
                        return new JSONObject(rs).toBean(EmbeddedTest.class);
                    }
                };
                PreparedStatement ps = null;
                try {

                    ResultSet rs = null;
                    try {
                        // 设置只能往后并且只读
                        ps = con.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                        return rse.extractData(rs);
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                        throw new RuntimeException(e.getMessage(), e);
                    } finally {
                        if (rs != null) {
                            rs.close();
                        }
                    }
                } finally {
                    if (ps != null) {
                        ps.close();
                    }
                }
            }
        });

    }
}
