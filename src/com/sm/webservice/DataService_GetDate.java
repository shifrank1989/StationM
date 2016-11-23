/**
 * 网站获取数据 
 */
package com.sm.webservice;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class DataService_GetDate {
	
	DataService_PortType proxy;
	String icrString;
	String allString;
	//String lastVer;
	String syscode="afe1ab20e8934daa9d38bb258d2102d0";
	Long masterID=1047L;
	
	public DataService_GetDate(){
		DataService_ServiceLocator service = new DataService_ServiceLocator();
		try {
			proxy = service.getDataServicePort();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//得到增量数据
	public String getIcrData(String lastVer){
		
		try {
			icrString=proxy.getIncrMasterData(masterID,lastVer, syscode);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return icrString;
	}
	
	//得到所有数据
	public String getAllData(){
		try {
			allString=proxy.getMasterData(masterID,syscode, 1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allString;
	}

}
