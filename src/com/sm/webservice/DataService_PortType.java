/**
 * DataService_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sm.webservice;

public interface DataService_PortType extends java.rmi.Remote {
    public java.lang.String getIncrMasterData(long 主数据ID, java.lang.String 最新版本, java.lang.String 系统编码) throws java.rmi.RemoteException;
    public java.lang.String testData(int 页码) throws java.rmi.RemoteException;
    public java.lang.String getMasterData(long 主数据ID, java.lang.String 系统编码, int 页码) throws java.rmi.RemoteException;
    public java.lang.String noticeJCBM(java.lang.String 系统编码, java.lang.String 主数据名称) throws java.rmi.RemoteException;
}

