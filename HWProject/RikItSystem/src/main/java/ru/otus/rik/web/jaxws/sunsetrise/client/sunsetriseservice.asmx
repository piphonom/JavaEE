<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://www.webserviceX.NET/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="http://www.webserviceX.NET/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://www.webserviceX.NET/">
      <s:element name="GetSunSetRiseTime">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="L" type="tns:LatLonDate" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="LatLonDate">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="Latitude" type="s:float" />
          <s:element minOccurs="1" maxOccurs="1" name="Longitude" type="s:float" />
          <s:element minOccurs="1" maxOccurs="1" name="SunSetTime" type="s:float" />
          <s:element minOccurs="1" maxOccurs="1" name="SunRiseTime" type="s:float" />
          <s:element minOccurs="1" maxOccurs="1" name="TimeZone" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="Day" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="Month" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="Year" type="s:int" />
        </s:sequence>
      </s:complexType>
      <s:element name="GetSunSetRiseTimeResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="GetSunSetRiseTimeResult" type="tns:LatLonDate" />
          </s:sequence>
        </s:complexType>
      </s:element>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="GetSunSetRiseTimeSoapIn">
    <wsdl:part name="parameters" element="tns:GetSunSetRiseTime" />
  </wsdl:message>
  <wsdl:message name="GetSunSetRiseTimeSoapOut">
    <wsdl:part name="parameters" element="tns:GetSunSetRiseTimeResponse" />
  </wsdl:message>
  <wsdl:portType name="SunSetRiseServiceSoap">
    <wsdl:operation name="GetSunSetRiseTime">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Get Sunset and Sunrise time for any location in the world&lt;br&gt;&lt;b&gt;Longitude:&lt;/b&gt;Positive in Western Hemisphere,Negative in Eastern Hemisphere&lt;br&gt;&lt;b&gt;Latitude:&lt;/b&gt;Positive in Northern Hemisphere,Negative in Southern Hemisphere</wsdl:documentation>
      <wsdl:input message="tns:GetSunSetRiseTimeSoapIn" />
      <wsdl:output message="tns:GetSunSetRiseTimeSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:portType name="SunSetRiseServiceHttpGet" />
  <wsdl:portType name="SunSetRiseServiceHttpPost" />
  <wsdl:binding name="SunSetRiseServiceSoap" type="tns:SunSetRiseServiceSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="GetSunSetRiseTime">
      <soap:operation soapAction="http://www.webserviceX.NET/GetSunSetRiseTime" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="SunSetRiseServiceSoap12" type="tns:SunSetRiseServiceSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="GetSunSetRiseTime">
      <soap12:operation soapAction="http://www.webserviceX.NET/GetSunSetRiseTime" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="SunSetRiseServiceHttpGet" type="tns:SunSetRiseServiceHttpGet">
    <http:binding verb="GET" />
  </wsdl:binding>
  <wsdl:binding name="SunSetRiseServiceHttpPost" type="tns:SunSetRiseServiceHttpPost">
    <http:binding verb="POST" />
  </wsdl:binding>
  <wsdl:service name="SunSetRiseService">
    <wsdl:port name="SunSetRiseServiceSoap" binding="tns:SunSetRiseServiceSoap">
      <soap:address location="http://www.webservicex.net/sunsetriseservice.asmx" />
    </wsdl:port>
    <wsdl:port name="SunSetRiseServiceSoap12" binding="tns:SunSetRiseServiceSoap12">
      <soap12:address location="http://www.webservicex.net/sunsetriseservice.asmx" />
    </wsdl:port>
    <wsdl:port name="SunSetRiseServiceHttpGet" binding="tns:SunSetRiseServiceHttpGet">
      <http:address location="http://www.webservicex.net/sunsetriseservice.asmx" />
    </wsdl:port>
    <wsdl:port name="SunSetRiseServiceHttpPost" binding="tns:SunSetRiseServiceHttpPost">
      <http:address location="http://www.webservicex.net/sunsetriseservice.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>