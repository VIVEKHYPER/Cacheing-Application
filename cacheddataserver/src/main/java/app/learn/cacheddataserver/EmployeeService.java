package app.learn.cacheddataserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	private Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	private final EmployeeRepository dataRepository;

	private final CacheDataRepository cacheDataRepository;

	public EmployeeService(EmployeeRepository dataRepository, CacheDataRepository cacheDataRepository) {
		this.dataRepository = dataRepository;
		this.cacheDataRepository = cacheDataRepository;
	}

	public Iterable<Employee> getAll() {
		return dataRepository.findAll();
	}

	public void saveEmployee(String name, String email) {
		Employee n = new Employee();
		n.setName(name);
		n.setEmail(email);
		dataRepository.save(n);
	}

	public String findData(String id) {
		return cacheDataRepository.findById(id).map(cacheData -> getCachedValue(cacheData))
				.orElseGet(() -> getFromDB(id));
	}

	private String getCachedValue(CacheData cacheData) {
		logCacheHit(cacheData);
		return cacheData.getValue();
	}

	public String getFromDB(String id) {

		logCacheMiss();

		
		
		Employee fetchedEmployee = dataRepository.findById(asInteger(id))
				.orElseThrow(() -> new RuntimeException("Employee not found"));

		CacheData cacheData = createCacheObject(fetchedEmployee);

		
		
		logCacheInsert(fetchedEmployee);

		cacheDataRepository.save(cacheData);

		return fetchedEmployee.toString();
	}

	private CacheData createCacheObject(Employee fetchedEmployee) {
		Integer id2 = fetchedEmployee.getId();
		return new CacheData(id2.toString(), fetchedEmployee.toString());
	}

	private int asInteger(String id) {
		return Integer.parseInt(id);
	}

	private void logCacheHit(CacheData cacheData) {
		String string = "Cache hit...!!!!!! for " + cacheData.getValue();
		logger.error(string);
	}

	private void logCacheMiss() {
		String string = "Cache miss...";
		logger.info(string);
	}

	private void logCacheInsert(Employee fetchedEmployee) {
		String string2 = "Cache Inserting.." + fetchedEmployee;
		logger.info(string2);
	}

}
