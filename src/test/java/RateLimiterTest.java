import org.example.UserBucket;
import org.testng.Assert;
import org.testng.annotations.Test;

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
    public void ShouldRateLimitHappyCaseMultipleClient(){
        UserBucket ub = new UserBucket();
        ub.addUserBucket("C1", 2, 1);
        ub.addUserBucket("C2", 3, 1);
        Assert.assertEquals(true, ub.allowAccess("C1"));
        Assert.assertEquals(true, ub.allowAccess("C1"));
        Assert.assertEquals(false, ub.allowAccess("C1"));
        Assert.assertEquals(false, ub.allowAccess("C1"));
        Assert.assertEquals(false, ub.allowAccess("C1"));
        Assert.assertEquals(true, ub.allowAccess("C2"));
        Assert.assertEquals(true, ub.allowAccess("C2"));
        Assert.assertEquals(true, ub.allowAccess("C2"));
        Assert.assertEquals(false, ub.allowAccess("C1"));
        Assert.assertEquals(false, ub.allowAccess("C1"));

    }
}
