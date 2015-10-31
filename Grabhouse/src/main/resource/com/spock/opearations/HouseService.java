package com.spock.opearations;

import java.util.Date;

import com.spock.exceptions.NoValidKeyFoundException;
import com.spock.modal.House;

@SuppressWarnings("deprecation")
public class HouseService {

	private House _house;

	public HouseService() {
		this._house = new House();
	}

	public String lockHouse() {
		if (_house.getlockState().equals("locked")) {
			return "House already locked";
		} else {
			_house.setlockState("locked");
		}
		return "locked Successfully";
	}

	Boolean isKeyUnused(String key){
		return House.lockKeys.get(key)[0].equals("false");
	}
	
	public String unlockHouse(String lockKey) {
		if (_house.getlockState().equals("unlocked")) {
			return "House already unlocked";
		}
		if (House.lockKeys.containsKey(lockKey)) {
			if(isKeyUnused(lockKey) && House.lockKeys.get(lockKey)[2]=="1"){
				Date currentTime = new Date();
				Date lastTime = new Date(House.lockKeys.get(lockKey)[1]);
				long secs = (currentTime.getTime() - lastTime.getTime()) / 1000;
				int hours = (int) (secs / 3600);
				if (hours > 2) {
					return "Your Key Expired";
				} else {
					_house.setlockState("unlocked");
					House.lockKeys.get(lockKey)[0]="true";
				}
			}else{
				return "Invalid Key try again";
			}
		} else {
			return "Invalid Key try again";
		}
		return "Unlocked Successfully";
	}

	public String getLockKey() throws NoValidKeyFoundException {
		return _house.getLockKey();
	}
	
	public void updateExpiredKeys(){
		for(String key : House.lockKeys.keySet()){
			if(House.lockKeys.get(key)[0]=="true"){
				House.lockKeys.get(key)[0] = "false";
				House.lockKeys.get(key)[1] = new Date().toLocaleString();
				House.lockKeys.get(key)[2] = "0";
			}
		}
	}

	public String pollRequest() {
		if(House.getRequestState()){
			return "yes";
		}
		else{
			return "no";
		}
	}
	
	public String putRequest() {
		if(House.getRequestState()){
			return "Requested Already";
		}else{
			House.setRequestState(true);
			return "Request Placed";
		}
	}
	
}
