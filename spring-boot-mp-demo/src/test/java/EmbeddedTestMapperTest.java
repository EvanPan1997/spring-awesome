import com.example.Application;
import com.example.entity.EmbeddedTest;
import com.example.mapper.EmbeddedTestMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = Application.class)
public class EmbeddedTestMapperTest {
    @Resource
    private EmbeddedTestMapper embeddedTestMapper;

    @Test
    public void test() {
        List<EmbeddedTest> embeddedTests = embeddedTestMapper.selectList(null);
        System.out.println(embeddedTests.size());
    }
}
