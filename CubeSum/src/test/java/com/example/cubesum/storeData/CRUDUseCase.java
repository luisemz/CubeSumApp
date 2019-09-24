package com.example.cubesum.storeData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cubesum.data.StoreData;
import com.example.cubesum.data.StoreDataRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CRUDUseCase {
	
	@Autowired
	private StoreDataRepository storeDataRepository;
	
	public CRUDUseCase(StoreDataRepository storeDataRepository) {
		this.storeDataRepository = storeDataRepository;
	}
	
	public StoreData createStoreData(StoreData storeData) {
		return storeDataRepository.save(storeData);
	}
	
	public StoreData updateStoreData(StoreData storeData) {
		return storeDataRepository.save(storeData);
	}
}
