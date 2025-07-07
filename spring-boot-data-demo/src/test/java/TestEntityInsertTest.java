import com.example.Main;
import com.example.entity.TestEntity;
import com.example.repository.TestEntityRepository;
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
public class TestEntityInsertTest {

    @Resource
    private TestEntityRepository testEntityRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_SQL = """
            insert into test_entity (id, version_id, table_id, table_schema, table_name, value_a, value_b, value_c)
            values (?, ?, ?, ?, ?, ?, ?, ?)
            """;

    @Test
    public void testInsert() {
        List<TestEntity> infos = getInfos();
        testEntityRepository.saveAllAndFlush(infos);
        }

    @Test
    public void testInsert2() {
        List<TestEntity> list = getInfos();
        for (int j = 0; j < list.size(); j = j + 5000) {
            List<TestEntity> currentList = list.subList(0, Math.min(5000, list.size()));
            jdbcTemplate.batchUpdate(INSERT_SQL, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    TestEntity entity = currentList.get(i);
                    ps.setString(1, entity.getId());
                    ps.setString(2, entity.getVersionId());
                    ps.setLong(3, entity.getTableId());
                    ps.setString(4, entity.getTableSchema());
                    ps.setString(5, entity.getTableName());
                    ps.setString(6, entity.getValueA());
                    ps.setString(7, entity.getValueB());
                    ps.setString(8, entity.getValueC());
                }

                @Override
                public int getBatchSize() {
                    return currentList.size();
                }
            });
            list.removeAll(currentList);
        }

    }

    private List<TestEntity> getInfos() {
        String versionId="20250701";

        List<TestEntity> list = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            TestEntity entity = new TestEntity();
            entity.setId(versionId+"-"+ (long) i);
            entity.setVersionId(versionId);
            entity.setTableId((long) i);
            entity.setTableSchema("table_schema");
            entity.setTableName("test-" + i);
            entity.setValueA("AAA-" + i);
            entity.setValueB("BBB-" + i);
            entity.setValueB("CCC-" + i);
            list.add(entity);
        }
        return list;
    }
}
