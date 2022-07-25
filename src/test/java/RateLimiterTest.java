import org.example.UserBucket;
import org.junit.*;


public class RateLimiterTest {
    @Test
    public void ShouldRateLimitHappyCaseOneClient(){
        UserBucket ub = new UserBucket();
        ub.addUserBucket("C1", 2, 1);
        Assert.assertEquals(true, ub.allowAccess("C1"));
        Assert.assertEquals(true, ub.allowAccess("C1"));
        Assert.assertEquals(false, ub.allowAccess("C1"));
        Assert.assertEquals(false, ub.allowAccess("C1"));
        Assert.assertEquals(false, ub.allowAccess("C1"));
    }

    @Test
    public void ShouldRateLimitHappyCaseMultipleClient1() throws InterruptedException {
        UserBucket ub = new UserBucket();
        ub.addUserBucket("C1", 1, 1);
        ub.addUserBucket("C2", 1, 1);
        Assert.assertEquals(true, ub.allowAccess("C1"));
        Assert.assertEquals(false, ub.allowAccess("C1"));
        Thread.sleep(1500);
        Assert.assertEquals(true, ub.allowAccess("C1"));
        Assert.assertEquals(false, ub.allowAccess("C1"));
        Thread.sleep(1000);
        Assert.assertEquals(true, ub.allowAccess("C1"));
        Assert.assertEquals(true, ub.allowAccess("C2"));
        Assert.assertEquals(false, ub.allowAccess("C2"));
        Thread.sleep(1000);
        Assert.assertEquals(true, ub.allowAccess("C2"));
        Thread.sleep(1000);
        Assert.assertEquals(true, ub.allowAccess("C1"));
        Assert.assertEquals(false, ub.allowAccess("C1"));

    }

    @Test
    public void TestRateLimitDifferentTimeHits(){
        UserBucket ub = new UserBucket();
        ub.addUserBucket("C1", 2, 1);
        ub.addUserBucket("C2", 3, 1);


    }
}
