/**
 * DataService_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sm.webservice;

public interface DataService_Service extends javax.xml.rpc.Service {
    public java.lang.String getDataServicePortAddress();

    public com.sm.webservice.DataService_PortType getDataServicePort() throws javax.xml.rpc.ServiceException;

    public com.sm.webservice.DataService_PortType getDataServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
