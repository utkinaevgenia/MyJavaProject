import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass
{
    @Test
    public void TestGetLocalNumber()
        {
            int a = this.getLocalNumber();
            Assert.assertTrue("Test failed", a == 14);
        }
}
