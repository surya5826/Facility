package com.miracle.facility.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.miracle.facility.entity.Facility;
import com.miracle.facility.repository.FacilityRepository;

@Service
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	FacilityRepository facilityRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;

	// get all facility
	@Override
	public ResponseEntity<List<Facility>> getAll() {
		List<Facility> facility = facilityRepository.findAll();
		return new ResponseEntity<List<Facility>>(facility, new HttpHeaders(), HttpStatus.OK);
	}
	
	//get by facilityId
	@Override
	public List<Facility> getById(String facilityId){
		List<Facility> facility = new ArrayList<>();
		facility.add(facilityRepository.findByFacilityId(facilityId));
		return facility;
	}
	
	
	// post a a new Facility
	@Override
	public Facility addNewFacility(Facility facility) throws Exception {
		Facility facilities = facilityRepository.findByFacilityId(facility.getFacilityId());
		if (facilities == null) {
			Facility newFacility = facilityRepository.save(facility);
			return newFacility;
		}
		throw new Exception("duplicate facility id");
	}

	@Override
	public Facility updateFacility(String facilityId, Facility facility) {
		try {
			Facility facilities = facilityRepository.findByFacilityId(facilityId);

			System.out.println("the id is" + facilities.getAddressLine1());

			facilities.setAddressLine1(facility.getAddressLine1());
			facilities.setAddressLine2(facility.getAddressLine2());
			facilities.setAddressLine3(facility.getAddressLine3());
			facilities.setCity(facility.getCity());
			facilities.setFacilityName(facility.getFacilityName());
			facilities.setPostalCode(facility.getPostalCode());
			facilities.setState(facility.getState());
			facilities.setCountry(facility.getCountry());
			facilities.setPhone(facility.getPhone());
			facilities.setFacilityManager(facility.getFacilityManager());
			facilities.setCategory(facility.getCategory());
			facilityRepository.save(facilities);

			return facilities;
		} catch (Exception e) {
			return facility ;
		}
	}

	@Override
	public void deleteFacility(String FacilityId) {
		facilityRepository.deleteByFacilityId(FacilityId);
	}
	
	//get facility by city
	@Override
	public List<Facility> getFacilityByCity(String facilityCity) {
		
		return facilityRepository.getFacilityByCity(facilityCity);
	}
	
	//get facility by name
	@Override
	public List<Facility> getFacilityByName(String facilityName){
		return facilityRepository.getFacilityByName(facilityName);
	}
	
	//get facility by postal code
	@Override
	public List<Facility> getFacilityByCode(String postalCode) {
		return facilityRepository.getFacilityByPostalCode(postalCode);
	}
	
	//get facility by state
	@Override
	public List<Facility> getFacilityByState(String facilityState){
		return facilityRepository.getFacilityByState(facilityState);
	}
	
	//get facility by country
	@Override
	public List<Facility> getFacilityByCountry(String facilityCountry){
		return facilityRepository.getFacilityByCountry(facilityCountry);
	}
	
	//get facility by phone
	@Override
	public List<Facility> getFacilityByPhone(String facilityPhone) {
		return facilityRepository.getFacilityByPhone(facilityPhone);
	}
	
	//get the list of facility by manager
	@Override
	public List<Facility> getFacilityByFacilityManager(String facilityManager){
		return facilityRepository.getFacilityByManager(facilityManager);
	}
	
	//get list by category
	@Override
	public List<Facility> getFacilityByCategory(String facilityCategory){
		return facilityRepository.getFacilityByCategory(facilityCategory);
	}
}
