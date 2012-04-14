package pl.koziolekweb.warsjava2011.dataloader;

import static org.testng.Assert.assertNotEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestException;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataCheck extends TestListenerAdapter {

	public static void main(String[] args) throws FileNotFoundException {
		System.setOut(new PrintStream(new File("/dev/null")));
		TestNG testNG = new TestNG();
		testNG.addListener(new DataCheck());
		testNG.setTestClasses(new Class[] { DataCheck.class });
		testNG.run();
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		System.err.printf(
				"W linii %d brakuje jednego z pól: %12s | %12s | %12s \n\r",
				tr.getParameters());
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
		System.err
				.printf("Wczytano %d linii.\n\r Ilość błędnych: %d;\n\r ilość poprawnych: %d;\n\r",
						getNumberOfTests(testContext), testContext
								.getFailedTests().size(), testContext
								.getPassedTests().size());
	}

	private int getNumberOfTests(ITestContext testContext) {
		return testContext.getSkippedTests().size()
				+ testContext.getPassedTests().size()
				+ testContext.getFailedTests().size();
	}

	@Test(dataProvider = "testValidLoader")
	public void checkLine(int line, String firstName, String lastName,
			String email) {
		assertNotEquals(firstName, "");
		assertNotEquals(lastName, "");
		assertNotEquals(email, "");
	}

	@Test(dataProvider = "testInvalidLoader", expectedExceptions = {
			TestException.class, AssertionError.class })
	public void checkInvalidLine(int line, String firstName, String lastName,
			String email) {
		assertNotEquals(firstName, "");
		assertNotEquals(lastName, "");
		assertNotEquals(email, "");
	}

	@DataProvider
	public static Object[][] testValidLoader() {
		return new Object[][] { new Object[] { 1, "jan", "kowalski", "email" } };

	}

	@DataProvider
	public static Object[][] testInvalidLoader() {
		return new Object[][] { new Object[] { 1, "", "kowalski", "email" } };

	}

	@DataProvider
	public static Object[][] loader() {
		Object[][] obj = new Object[10000][4];
		for (int i = 0; i < 10000; i++) {
			obj[i] = new Object[] { i + 1, random(firstNames),
					random(lastNames), random(emails) };
		}
		return obj;
	}

	private static String random(String[] array) {
		int i = (int) (Math.random() * array.length);
		return array[i];
	}

	private static String[] firstNames = { "Jan", "Jakub", "Robert", "Jan",
			"Jakub", "Robert", "Jan", "Jakub", "Robert", "Jan", "Jakub",
			"Robert", "Jan", "Jakub", "Robert", "" };
	private static String[] lastNames = { "Obiektowy", "Interfejs",
			"Obiektowy", "Interfejs", "Obiektowy", "Interfejs", "Obiektowy",
			"Interfejs", "Obiektowy", "Interfejs", "Obiektowy", "Interfejs",
			"Enumerowalny", "" };
	private static String[] emails = { "a@domain.com", "b@domain.com",
			"a@domain.com", "b@domain.com", "a@domain.com", "b@domain.com",
			"a@domain.com", "b@domain.com", "a@domain.com", "b@domain.com",
			"a@domain.com", "b@domain.com", "c@domain.com", "" };

}
