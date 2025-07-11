import com.example.Main;
import com.example.req.EnumDemoReq;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;

@SpringBootTest(classes = Main.class)
public class EnumTest {

    @Test
    public void test() {
        try {
            String str = "{\"type\":\"AND\"}";
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(str);

            Iterator<JsonNode> type = jsonNode.get("type").elements();

            System.out.println(type.next());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mapperTest() {
        try {
            String str = """
                    {
                        "type": "AND",
                        "params": [{"type": "AND", "field": "aaa", "operator": "EQ", "value": "1"}]
                    }
                    """;
            ObjectMapper objectMapper = new ObjectMapper();
            EnumDemoReq enumDemoReq = objectMapper.readValue(str, EnumDemoReq.class);
            System.out.println(enumDemoReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
