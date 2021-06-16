import org.junit.Test;

public class CoreTest {
    @Test
    public void timeServerTest() throws InterruptedException {
        new TimeServer().build(8080);
    }
    @Test
    public void timeClientTest() throws InterruptedException {
        new TimeClient().connect(8080,"127.0.0.1");
    }
}
