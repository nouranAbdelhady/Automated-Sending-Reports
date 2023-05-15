package demo;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.PropertyConfigurator;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.FileInputStream;
import org.junit.BeforeClass;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Reports {
    
    private static File currentDirectory;
    private static String path;
    
    @BeforeClass
    static public void Init(){
        currentDirectory = new File(System.getProperty("user.dir"));
        path = currentDirectory.toString()+'\\';
        System.out.println("Current Directory: "+path);
    }
       
    public String getCellData(int row, int col) throws Exception {
        XSSFSheet ExcelWSheet;
        XSSFWorkbook ExcelWBook;

        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(path + "Sheet.xlsx");

            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet("Sheet 1");
            String cellData = ExcelWSheet.getRow(row).getCell(col).getStringCellValue();
            ExcelWBook.close();
            return cellData;
        } catch (IOException e) {
            return "Error in opening the file";
        }
    }

    @Test
    public void sendFromWhatsapp() throws Exception {

        Properties log4jProp = new Properties();
        log4jProp.setProperty("log4j.rootLogger", "WARN");
        PropertyConfigurator.configure(log4jProp);
        int studentID = 1;

        // Set Google Chrome Web Driver
        System.setProperty("webdriver.chrome.driver", path + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Puts an Implicit wait, will wait for 20 seconds before throwing exception
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        for (int i = 0; i < 39 ; i++) {
            String whatsappURL = getCellData(studentID, 2);
            String fullName = getCellData(studentID, 1);
            
            // Store reports in folder 'Files'
            String filePath = path + '\\'+"Files"+'\\' +fullName+".pdf";

            System.out.println("File Path: " + filePath);
            System.out.println("Whatsapp URL: " + whatsappURL);

            // Open chat 
            driver.navigate().to("http://" + whatsappURL);
            // Maximize the browser
            driver.manage().window().maximize();
            Thread.sleep(2000);

            // Click on "Continue to Chat"
            driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div[2]/div[1]/a")).click();
            Thread.sleep(2000);
            System.out.println("Continue to Chat");

            // Click on "use WhatsApp Web"
            driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div[3]/div/div/h4[2]/a")).click();
            Thread.sleep(8500);
            System.out.println("use WhatsApp Web");

            // Send message 1st
            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[4]/div/footer/div[1]/div/span[2]/div/div[2]/div[2]/button")).click();
            Thread.sleep(1000);
            System.out.println("sent message");

            // Send PDF File   
            // click to add
            driver.findElement(By.cssSelector("span[data-icon='clip']")).click();
            System.out.println("new attachment");
            Thread.sleep(2000);

            // add file to send by file path
            driver.findElement(By.cssSelector("input[type='file']")).sendKeys(filePath);
            System.out.println("file added");
            Thread.sleep(2000);

            // click to send
            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/span/div/span/div/div/div[2]/div/div[2]/div[2]/div/div/span")).click();
            System.out.println("sent file");
            Thread.sleep(5200);     //wait for message to be sent
            
            // next student
            studentID++; 
        }
        Thread.sleep(10000);
        driver.close();
    }

}
