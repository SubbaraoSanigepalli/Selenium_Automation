package jenkins.demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmadorSchool {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://dublinusd.org/");
		driver.findElement(By.xpath("//div[@id='tabs']/div[1]")).click();
		driver.findElement(By.xpath("//div[@id='tabs']/div[1] /ul/li[1]")).click();
		
		List <WebElement> nav=driver.findElements(By.cssSelector("nav[id='menu'] a"));
		for (WebElement option:nav) {
			if(option.getText().equalsIgnoreCase("about")){
				option.click();
				break;
			}
		}
		List <WebElement> about=driver.findElements(By.cssSelector("ul[id='nmM2'] a"));
		for (WebElement option:about) {
			if(option.getText().equalsIgnoreCase("Organizations →")){
				option.click();
				break;
			}
			
		}
		List <WebElement> organizations=driver.findElements(By.cssSelector("ul[id='nmM2'] a"));
		for (WebElement option:organizations) {
			if(option.getText().equalsIgnoreCase("Parent-Faculty Club")){
				option.click();
				break;
			}

			}
		
		
		String data=driver.findElement(By.xpath("//div[@class='en-editable-block-wrapper']/div[4]/div[3]")).getText();
		
		
		System.out.println(data);
		
		String[] lines = data.split("\n");
        List<String> nonEmptyLines = new ArrayList<>();
        
        
        
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                nonEmptyLines.add(line);
            }
        }
        System.out.println(nonEmptyLines);
        
        
       
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet1 = workbook.createSheet("Amador Elementary");
        
        
        XSSFRow headerRow = sheet1.createRow(0);
        headerRow.createCell(0).setCellValue("Designation");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Emaiaal");
        
        String[] parts=null;
        int rowNum = 1;
        for (String line : nonEmptyLines) {
            parts = line.split(" - ");
            if (parts.length == 3) {
                XSSFRow row = sheet1.createRow(rowNum++);
                for (int i = 0; i < 3; i++) {
                    row.createCell(i).setCellValue(parts[i]);
                }
            }
        }
        
        
        String filePath="/Users/bunnysanigepalli/Desktop/untitled folder 3/Assignment5.xlsx";
        FileOutputStream outputStream = new FileOutputStream(filePath); 
        workbook.write(outputStream);
        
              


	}

}
