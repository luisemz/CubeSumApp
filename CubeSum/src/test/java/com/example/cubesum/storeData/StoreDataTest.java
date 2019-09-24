package com.example.cubesum.storeData;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cubesum.data.StoreData;
import com.example.cubesum.data.StoreDataRepository;

@RunWith(SpringRunner.class)
public class StoreDataTest {
	
	@Mock
	private StoreDataRepository storeDataRepository;
	
	private CRUDUseCase crudUseCase;
	
	@Before
	public void initUseCase() {
		crudUseCase = new CRUDUseCase(storeDataRepository);
	}
	
	@Test
	public void createStoreData() {
		StoreData storeData = crudUseCase.createStoreData(new StoreData());
		
		assertThat(storeData.getNumberTest()).isEqualTo(0);
		assertThat(storeData.getNumberPoints()).isEqualTo(0);
		assertThat(storeData.getNumberOperations()).isEqualTo(0);
		assertThat(storeData.getResponse()).isEqualTo("");
	}

	@Test
	public void updateStoreData() {
		StoreData storeData = storeDataRepository.findById(1L).orElse(new StoreData());
		
		storeData.setNumberTest(10);
		storeData.setResponse("TEST");
		StoreData newStoreData = crudUseCase.updateStoreData(storeData);

		assertThat(newStoreData.getNumberTest()).isEqualTo(10);
		assertThat(newStoreData.getResponse()).isEqualTo("TEST");
	}
}
