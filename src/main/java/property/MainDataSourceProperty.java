package property;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ToString
@Getter @Setter
@ConfigurationProperties("spring.datasource.main")
public class MainDataSourceProperty {
    private String username;
    private String password;
    private Database master;
    private List<Database> slaves;

    @ToString
    @Setter
    @Getter
    public static class Database {
        private String name;
        private String url;
    }
}
