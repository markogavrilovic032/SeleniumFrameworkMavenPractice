package marko;

import marko.TestComponents.BaseTest;
import marko.pageobjects.CartPage;
import marko.pageobjects.CheckoutPage;
import marko.pageobjects.ConfirmationPage;
import marko.pageobjects.ProductCatalogPage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;


public class SubmitOrderExcel extends BaseTest {

    DataFormatter formatter = new DataFormatter();

    @Test(dataProvider = "driveTest")
    public void submitOrderWithExelData(String email, String password , String productName) throws IOException, InterruptedIOException {

        String contryName = "Canada";

        ProductCatalogPage productCatalogPage = landingPage.loginApplication(email, password);

        productCatalogPage.addProducttoCart(productName);
        CartPage cartPage = productCatalogPage.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry(contryName);
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @DataProvider(name ="driveTest")
    public Object[][] getData() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\D\\GIT\\SeleniumFrameworkMavenPractice\\src\\test\\java\\marko\\data\\excelDriven.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int colCount = row.getLastCellNum();
        Object Data[][] = new Object[rowCount-1][colCount];

        for(int i=0; i<rowCount-1; i++){
            row=sheet.getRow(i+1);
            for (int j=0; j<colCount; j++){
                XSSFCell cell = row.getCell(j);

                Data[i][j] = formatter.formatCellValue(cell);
            }
        }
        return Data;
    }
}
