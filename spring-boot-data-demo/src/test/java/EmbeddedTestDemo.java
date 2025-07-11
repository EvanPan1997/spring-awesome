import com.example.Main;
import com.example.entity.EmbeddedTest;
import com.example.repository.EmbeddedTestRepository;
import com.example.service.EmbeddedTestService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = Main.class)
public class EmbeddedTestDemo {

    @Resource
    private EmbeddedTestRepository embeddedTestRepository;

    @Resource
    private EmbeddedTestService embeddedTestService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_SQL = """
            insert into embedded_test (version_id, table_id, table_schema, table_name, value_a, value_b, value_c)
            values (?, ?, ?, ?, ?, ?, ?)
            """;

    @Test
    public void test() {
        List<EmbeddedTest> embeddedTestList = embeddedTestRepository.findAll();
        System.out.println(embeddedTestList.toString());
    }

    @Test
    public void insertTest() {
        List<EmbeddedTest> list = getInfos();
        embeddedTestRepository.saveAll(list);
    }

    @Test
    public void insertTest2() {
        // 7.183 s
        List<EmbeddedTest> list = getInfos();
        embeddedTestService.batchInsert(list);
    }

    @Test
    public void insertTest3() {
        List<EmbeddedTest> list = getInfos();
        jdbcTemplate.batchUpdate(INSERT_SQL, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                EmbeddedTest embeddedTest = list.get(i);
                ps.setString(1, embeddedTest.getVersionId());
                ps.setLong(2, embeddedTest.getTableId());
                ps.setString(3, embeddedTest.getTableSchema());
                ps.setString(4, embeddedTest.getTableName());
                ps.setString(5, embeddedTest.getValueA());
                ps.setString(6, embeddedTest.getValueB());
                ps.setString(7, embeddedTest.getValueC());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

    @Test
    public void deleteTest() {
        embeddedTestRepository.deleteAll();
    }

    @Test
    public void deleteTest2() {
        // 0.763 s
        jdbcTemplate.update("delete from embedded_test where version_id=?", "20250701");
    }

    @Test
    public void deleteTest3() {
        // 0.707 s
        embeddedTestRepository.deleteByVersionId("20250701");
    }

    private List<EmbeddedTest> getInfos() {
        List<EmbeddedTest> list = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            EmbeddedTest embeddedTest = new EmbeddedTest();
            embeddedTest.setVersionId("20250701");
            embeddedTest.setTableId((long) i);
            embeddedTest.setTableSchema("table_schema");
            embeddedTest.setTableName("test-" + i);
            embeddedTest.setValueA("AAA-" + i);
            embeddedTest.setValueB("BBB-" + i);
            embeddedTest.setValueC("CCC-" + i);
            list.add(embeddedTest);
        }
        return list;
    }
}
