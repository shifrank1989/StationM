package com.sm.webservice;
import javax.xml.*;

public class DataServiceProxy implements com.sm.webservice.DataService_PortType {
	  private String _endpoint = null;
	  private com.sm.webservice.DataService_PortType dataService_PortType = null;
	  
	  public DataServiceProxy() {
	    _initDataServiceProxy();
	  }
	  
	  public DataServiceProxy(String endpoint) {
	    _endpoint = endpoint;
	    _initDataServiceProxy();
	  }
	  
	  private void _initDataServiceProxy() {
	    try {
	      dataService_PortType = (new com.sm.webservice.DataService_ServiceLocator()).getDataServicePort();
	      if (dataService_PortType != null) {
	        if (_endpoint != null)
	          ((javax.xml.rpc.Stub)dataService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
	        else
	          _endpoint = (String)((javax.xml.rpc.Stub)dataService_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
	      }
	      
	    }
	    catch (javax.xml.rpc.ServiceException serviceException) {}
	  }
	  
	  public String getEndpoint() {
	    return _endpoint;
	  }
	  
	  public void setEndpoint(String endpoint) {
	    _endpoint = endpoint;
	    if (dataService_PortType != null)
	      ((javax.xml.rpc.Stub)dataService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
	    
	  }
	  
	  public com.sm.webservice.DataService_PortType getDataService_PortType() {
	    if (dataService_PortType == null)
	      _initDataServiceProxy();
	    return dataService_PortType;
	  }
	  
	  public java.lang.String getIncrMasterData(long ������ID, java.lang.String ���°汾, java.lang.String ϵͳ����) throws java.rmi.RemoteException{
	    if (dataService_PortType == null)
	      _initDataServiceProxy();
	    return dataService_PortType.getIncrMasterData(������ID, ���°汾, ϵͳ����);
	  }
	  
	  public java.lang.String testData(int ҳ��) throws java.rmi.RemoteException{
	    if (dataService_PortType == null)
	      _initDataServiceProxy();
	    return dataService_PortType.testData(ҳ��);
	  }
	  
	  public java.lang.String getMasterData(long ������ID, java.lang.String ϵͳ����, int ҳ��) throws java.rmi.RemoteException{
	    if (dataService_PortType == null)
	      _initDataServiceProxy();
	    return dataService_PortType.getMasterData(������ID, ϵͳ����, ҳ��);
	  }
	  
	  public java.lang.String noticeJCBM(java.lang.String ϵͳ����, java.lang.String ����������) throws java.rmi.RemoteException{
	    if (dataService_PortType == null)
	      _initDataServiceProxy();
	    return dataService_PortType.noticeJCBM(ϵͳ����, ����������);
	  }
	  
	  
	}