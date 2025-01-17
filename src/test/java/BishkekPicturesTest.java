import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class BishkekPicturesTest {
    private static final String expectedApiKey = "L2cjkOp3FsfwyDcD8LxJ6OxnpCofhSaTzW3pLXZ6";
    private static final double expectedBishkekLAT = 42.8844;
    private static final double expectedBishkekLON = 74.5766;
    private static final double expectedDim = 0.03;
    private static final String expectedFolderToSave = "./bishkek_images/";


@Test
    public void testApiKey() {
    String actualApiKey = BishkekPictures.getApiKey();
    Assert.assertEquals(actualApiKey, expectedApiKey);
}

@Test
    public void testLat() {
    double actualLat = BishkekPictures.getBishkekLAT();
    Assert.assertEquals(actualLat, expectedBishkekLAT);
}

@Test
    public void testLon() {
    double actualLon = BishkekPictures.getBishkekLON();
    Assert.assertEquals(actualLon, expectedBishkekLON);

}
@Test
    public void testDim() {
    double actualDim = BishkekPictures.getDim();
    Assert.assertEquals(actualDim, expectedDim);
}

@Test
    public void testFolderToSave() {
    String actualFolderToSave = BishkekPictures.getFolderToSave();
    Assert.assertEquals(actualFolderToSave, expectedFolderToSave);
}

}
