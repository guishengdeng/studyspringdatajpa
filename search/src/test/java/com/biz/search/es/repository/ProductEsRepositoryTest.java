import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * @author david-liu
 * @date 2017年04月12日
 * @reviewer
 */
@RunWith(Arquillian.class)
public class ProductEsRepositoryTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(com.biz.search.es.repository.ProductEsRepository.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
