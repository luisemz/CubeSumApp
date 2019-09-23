package com.example.cubesum.app;

import com.example.cubesum.data.Point3D;
import com.example.cubesum.data.Point3DRepository;
import com.example.cubesum.data.StoreData;
import com.example.cubesum.data.StoreDataRepository;
import com.example.cubesum.model.Operation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class CubeSumController {
	final Logger logger = LoggerFactory.getLogger(CubeSumController.class);
	private final StoreDataRepository repositoryData;
	private final Point3DRepository repositoryPoint;

	public CubeSumController(StoreDataRepository repositoryData, Point3DRepository repositoryPoint) {
		this.repositoryData = repositoryData;
		this.repositoryPoint = repositoryPoint;
	}
	
	@PutMapping("/cubesum/start")
	StoreData start(@RequestBody StoreData data) {
		return repositoryData.findById(1L).map(storeData -> {
			storeData.setNumberTest(data.getNumberTest());
			repositoryData.save(storeData);
			return storeData;
		})
		.orElseThrow(() -> new CubeSumNotFoundException(1L));
	}
	
	@PutMapping("/cubesum/ponitsAndOperations")
	StoreData pointsAndOperations(@RequestBody StoreData data) {
		return repositoryData.findById(1L).map(storeData -> {
			storeData.setNumberPoints(data.getNumberPoints());
			storeData.setNumberOperations(data.getNumberOperations());
			repositoryData.save(storeData);
			return storeData;
		})
		.orElseThrow(() -> new CubeSumNotFoundException(1L));
	}
	
	@PostMapping("/cubesum/operation")
	StoreData operation(@RequestBody Operation opt) {
		return repositoryData.findById(1L).map(storeData -> {
			int points = storeData.getNumberPoints();
			String[] lines = opt.getOperation().split(" ");
            if (lines[0].equals("UPDATE")) {
            	if (lines.length == 5) {
                    int x = Integer.parseInt(lines[1]);
                    int y = Integer.parseInt(lines[2]);
                    int z = Integer.parseInt(lines[3]);
                    long value = Long.parseLong(lines[4]);
                    
                    if (x > points || y > points || z > points ||
                    		x < 1 || y < 1 || z < 1) {
                    	storeData.setResponse("Point out od range");
                    }
                    
                    boolean find = false;
                    List<Point3D> matrix = repositoryPoint.findAll();
                    for (Point3D point:matrix) {
                        if(point.getX() == x && point.getY() == y && point.getZ() == z){
                            point.setValue(value);
                            find = true;
                            repositoryPoint.save(point);
                        }
                    }
                    if (!find) {
                        repositoryPoint.save(new Point3D(x, y, z, value));
                    }
                    
                	storeData.setResponse("Point update");
            	} else {
                	storeData.setResponse("Bad operation, syntax error!");
            	}
            } else if (lines[0].equals("QUERY")) {
            	if (lines.length == 7) {
            		long sum = 0;
                    int x1 = Integer.parseInt(lines[1]);
                    int y1 = Integer.parseInt(lines[2]);
                    int z1 = Integer.parseInt(lines[3]);
                    int x2 = Integer.parseInt(lines[4]);
                    int y2 = Integer.parseInt(lines[5]);
                    int z2 = Integer.parseInt(lines[6]);
                    
                    if (x2 > points || y2 > points || z2 > points ||
                    		x1 < 1 || y1 < 1 || z1 < 1) {
                    	storeData.setResponse("Point out od range");
                    }

                    List<Point3D> matrix = repositoryPoint.findAll();
                    for (Point3D point:matrix) {
                        if (point.getX() >= x1 && point.getY() >= y1 && point.getZ() >= z1
                        		&& point.getX() <= x2 && point.getY() <= y2 && point.getZ() <= z2) {
                            sum += point.getValue();
                        }
                    }

                	storeData.setResponse(Long.toString(sum));
            	} else {
                	storeData.setResponse("Bad operation, syntax error!");
            	}
            } else {
            	storeData.setResponse("Bad operation, syntax error!");
        	}
			repositoryData.save(storeData);
			
			return storeData;
		})
		.orElseThrow(() -> new CubeSumNotFoundException(1L));
	}
	
	@PutMapping("/cubesum/restart")
	StoreData restart(@RequestBody StoreData data) {
		return repositoryData.findById(1L).map(storeData -> {
			storeData.setNumberTest(0);
			storeData.setNumberPoints(0);
			storeData.setNumberOperations(0);
			storeData.setResponse("");
			repositoryData.save(storeData);
			repositoryPoint.deleteAll();
			return storeData;
		})
		.orElseThrow(() -> new CubeSumNotFoundException(1L));
	}
	
	@GetMapping("/cubesum")
	List<StoreData> allData() {
		return repositoryData.findAll();
	}
	
	@GetMapping("/cubesum/matrix")
	List<Point3D> allPoints() {
		return repositoryPoint.findAll();
	}
	
	@GetMapping("/cubesum/matrix/restart")
	String restartPoints() {
		repositoryPoint.deleteAll();
		return "Matrix restarted";
	}
}
