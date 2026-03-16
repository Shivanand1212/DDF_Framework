package Com.Automation.base;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

public class Utility extends Base {

	private static final int DEFAULT_TIMEOUT = 30; // seconds

	
	public static void Click(WebElement element, String element_name) {
		
		try {
			
			if(element!=null&& element.isDisplayed() && element.isEnabled())
			{
				element.click();
				LoggerUtil.logReport("successfully clicked"+ element_name , Status.PASS);
			}else {
				LoggerUtil.logReport("Failed to click "+ element_name  , Status.FAIL);
			}
		} catch (Exception e) {
		LoggerUtil.logReport("Failed to click on "+ element_name +"_"+e.getMessage(), Status.FAIL);
		}
	}
	
	public static void enterValueinTextBox(WebElement element, String input, String element_name) {
		try {
			
			
			if(element!=null && element.isDisplayed()&& element.isEnabled()) {
				element.sendKeys(input);
				LoggerUtil.logReport("successfully Entered text in"+element_name, Status.PASS);
			}
			else {
				LoggerUtil.logReport("Failed to Enter text in"+element_name, Status.FAIL);
			}
		} catch (Exception e) {
			LoggerUtil.logReport("Exception while entering text in: "+element_name+"_"+e.getMessage(), Status.FAIL);

		}
		
	}
	
	public static WebElement waitForVisibility(WebDriver driver, WebElement element, String elementName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
            LoggerUtil.logReport("Element visible: " + elementName, Status.PASS);
            return visibleElement;
        } catch (Exception e) {
            LoggerUtil.logReport("Element not visible: " + elementName + " - " + e.getMessage(), Status.FAIL);
            return null;
        }
    }

	
	public static WebElement waitForClickable(WebDriver driver, WebElement element, String elementName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
            LoggerUtil.logReport("Element clickable: " + elementName, Status.PASS);
            return clickableElement;
        } catch (Exception e) {
            LoggerUtil.logReport("Element not clickable: " + elementName + " - " + e.getMessage(), Status.FAIL);
            return null;
        }
    }
   
	
	public static void selectDropdownOption(WebElement element, String elementName, String selectionType, Object selectionValue) {
	    try {
	        if (element != null && element.isDisplayed() && element.isEnabled()) {
	            Select select = new Select(element);

	            switch (selectionType.toLowerCase()) {
	                case "value":
	                    select.selectByValue(selectionValue.toString());
	                    break;
	                case "text":
	                    select.selectByVisibleText(selectionValue.toString());
	                    break;
	                case "index":
	                    if (selectionValue instanceof Integer) {
	                        select.selectByIndex((Integer) selectionValue);
	                    } else {
	                        throw new IllegalArgumentException("Index must be an integer");
	                    }
	                    break;
	                default:
	                    throw new IllegalArgumentException("Invalid selection type: " + selectionType);
	            }

	            LoggerUtil.logReport("Selected option (" + selectionType + " = " + selectionValue + ") from dropdown: " + elementName, Status.PASS);
	        } else {
	            LoggerUtil.logReport("Dropdown not interactable: " + elementName, Status.FAIL);
	        }
	    } catch (Exception e) {
	        LoggerUtil.logReport("Unable to select option from dropdown: " + elementName + " - " + e.getMessage(), Status.FAIL);
	    }
	}	
	
	public static void verifytext(WebElement element , String expectedText) {
		try {
			
			String text=element.getText();
				
				if(text.equals(expectedText)) {
		            LoggerUtil.logReport("Text Verified: " + expectedText , Status.PASS);
				}
			     else {
		            LoggerUtil.logReport("Text is not matched: " + expectedText , Status.FAIL);

				}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void moveToElement(WebElement element, String elementName) {
	    try {
	        Actions actions = new Actions(getDriver());
	        actions.moveToElement(element).perform();
	        LoggerUtil.logReport("Moved to element: " + elementName, Status.PASS);
	    } catch (Exception e) {
	        LoggerUtil.logReport("Failed to move to element: " + elementName + " - " + e.getMessage(), Status.FAIL);
	        throw e;
	    }
	}

	public static void contextClick(WebElement element, String elementName) {
	    try {
	        Actions actions = new Actions(getDriver());
	        actions.contextClick(element).perform();
	        LoggerUtil.logReport("Context clicked on element: " + elementName, Status.PASS);
	    } catch (Exception e) {
	        LoggerUtil.logReport("Failed to context click on element: " + elementName + " - " + e.getMessage(), Status.FAIL);
	        throw e;
	    }
	}
   
	public static void doubleClick(WebElement element, String elementName) {
	    try {
	        Actions actions = new Actions(getDriver());
	        actions.doubleClick(element).perform();
	        LoggerUtil.logReport("Double clicked on element: " + elementName, Status.PASS);
	    } catch (Exception e) {
	        LoggerUtil.logReport("Failed to double click on element: " + elementName + " - " + e.getMessage(), Status.FAIL);
	        throw e;
	    }
	}
	
	
    public static String getCellData(String excelPath, String sheetName, int rowNum, int colNum) {
        String cellData = null;
        try (FileInputStream fis = new FileInputStream(excelPath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);
            cellData = cell.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellData;
    }
    
    public static void switchtoframebyindex(int index) {
    	getDriver().switchTo().frame(index);

    }
    public static void switchtoframebyname(String name ) {
    	getDriver().switchTo().frame(name);

    }
    public static void switchtoframebyelment(String elment ) {
    	getDriver().switchTo().frame(elment);

    }
}
