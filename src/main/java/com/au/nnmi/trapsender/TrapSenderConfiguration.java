package com.au.nnmi.trapsender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "trapsender")
public class TrapSenderConfiguration {


    @Value("${trapsender.ipAddress}")
    private String ipAddress;

    @Value("${trapsender.port}")
    private int port;

    @Value("${trapsender.community}")
    private String community;

    @Value("${trapsender.snmpVersion}")
    private int snmpVersion;

    @Value("${trapsender.sendCounter}")
    private int sendCounter;





    public TrapSenderConfiguration() {
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public int getSnmpVersion() {
        return snmpVersion;
    }

    public void setSnmpVersion(int snmpVersion) {
        this.snmpVersion = snmpVersion;
    }

    public int getSendCounter() {
        return sendCounter;
    }

    public void setSendCounter(int sendCounter) {
        this.sendCounter = sendCounter;
    }
}
