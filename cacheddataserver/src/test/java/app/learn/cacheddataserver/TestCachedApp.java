package app.learn.cacheddataserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.testng.annotations.Test;

public class TestCachedApp {

	@Test(enabled = true)
	private void testCachedApp() throws IOException {

		String command = "curl localhost:8080/demo/find -d id=5";
		Process process = Runtime.getRuntime().exec(command);

		long startTime = System.currentTimeMillis();

		try (InputStream inputStream = process.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
			String result = bufferedReader.lines().collect(Collectors.joining("\n"));
			System.out.println(result);
		} finally {
			process.destroy();
		}

		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");

	}

}