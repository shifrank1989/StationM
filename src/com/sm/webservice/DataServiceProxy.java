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
	  
	  public java.lang.String getIncrMasterData(long 主数据ID, java.lang.String 最新版本, java.lang.String 系统编码) throws java.rmi.RemoteException{
	    if (dataService_PortType == null)
	      _initDataServiceProxy();
	    return dataService_PortType.getIncrMasterData(主数据ID, 最新版本, 系统编码);
	  }
	  
	  public java.lang.String testData(int 页码) throws java.rmi.RemoteException{
	    if (dataService_PortType == null)
	      _initDataServiceProxy();
	    return dataService_PortType.testData(页码);
	  }
	  
	  public java.lang.String getMasterData(long 主数据ID, java.lang.String 系统编码, int 页码) throws java.rmi.RemoteException{
	    if (dataService_PortType == null)
	      _initDataServiceProxy();
	    return dataService_PortType.getMasterData(主数据ID, 系统编码, 页码);
	  }
	  
	  public java.lang.String noticeJCBM(java.lang.String 系统编码, java.lang.String 主数据名称) throws java.rmi.RemoteException{
	    if (dataService_PortType == null)
	      _initDataServiceProxy();
	    return dataService_PortType.noticeJCBM(系统编码, 主数据名称);
	  }
	  
	  
	}