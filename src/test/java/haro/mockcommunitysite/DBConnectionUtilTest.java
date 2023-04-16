package haro.mockcommunitysite;

import static org.assertj.core.api.Assertions.*;

import haro.mockcommunitysite.utility.DBConnectionUtil;
import java.sql.Connection;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class DBConnectionUtilTest {

    @Test
    void connection() {
        Connection connection = DBConnectionUtil.getConnection();
        assertThat(connection).isNotNull();
    }
}