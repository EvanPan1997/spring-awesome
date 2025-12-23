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
    private static final RowMapper<EmbeddedTest> embeddedTestRowMapper = new RowMapper<>() {
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
    @Test
    public void queryForStreamTest() {
        try (Stream<EmbeddedTest> stream = jdbcTemplate.queryForStream(sql, embeddedTestRowMapper)){
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
                ResultSetExtractor<EmbeddedTest> rse = rs -> {
                    ResultSetMetaData resultSetMetaData = rs.getMetaData();// 该表数据结构
                    int columnCount = resultSetMetaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.println(resultSetMetaData.getColumnName(i));
                    }

                    while (rs.next()) {
                        // 处理每条数据
//                        System.out.println(rs.getString(1));
                    }
                    return null;
                };
                // 初始化为 null
                try (PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY); ResultSet rs = ps.executeQuery()) {
                    // 设置只能往后并且只读
                    // 执行查询并获取 ResultSet
                    return rse.extractData(rs);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        });

    }

    @Test
    public void testMethod() {
        Stream<EmbeddedTest> embeddedTestStream = jdbcTemplate.queryForStream(sql, embeddedTestRowMapper);
        System.out.println(embeddedTestStream.toList().size());
    }

    private void executeMethod() {

    }

}
