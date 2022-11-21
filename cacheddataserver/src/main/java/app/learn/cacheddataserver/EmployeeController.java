package app.learn.cacheddataserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/demo")
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService service;

	@PostMapping(path = "/add")
	public @ResponseBody String addNewEmployee(@RequestParam String name, @RequestParam String email) {
		service.saveEmployee(name, email);
		return "Saved";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Employee> getAllEmployees() {
		return service.getAll();
	}

	@PostMapping(path = "/find")
	public @ResponseBody String findById(@RequestParam String id) {

		long startTime = System.currentTimeMillis();

		String foundData = service.findData(id);

		long endTime = System.currentTimeMillis();

		logDuration(startTime, endTime);

		return foundData;
	}

	private void logDuration(long startTime, long endTime) {
		String message = "That took " + (endTime - startTime) + " milliseconds";
		logger.info(message);
	}

}
