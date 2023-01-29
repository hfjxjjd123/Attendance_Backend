import com.proj252.AIstopwatch.proj252.repository.JpaReportRepo
import com.proj252.AIstopwatch.proj252.repository.ReportRepo
import com.proj252.AIstopwatch.proj252.service.ReportService
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
public class SpringConfig {

    private val reportRepo: ReportRepo

    @Autowired
    constructor(reportRepo: ReportRepo){
        this.reportRepo = reportRepo
    }

    @Bean
    public fun reportService(): ReportService{
        return ReportService(reportRepo)
    }
//    @Bean
//    public fun memberRepo(): ReportRepo {
//        return JpaReportRepo(em)
//    }

}