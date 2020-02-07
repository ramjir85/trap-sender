package com.au.nnmi.trapsender;

import com.au.nnmi.trapsender.snmp.SnmpTrapConstants;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SnmpTrapSender {

    private static final Logger LOG = LoggerFactory.getLogger(SnmpTrapSender.class);




    // Sending Trap for sysLocation of RFC1213
    public static final String Oid = ".1.3.6.1.2.1.1.8";

    //IP of Local Host
    //public static final String ipAddress = "127.0.0.1";

    //Ideally Port 162 should be used to send receive Trap, any other available Port can be used
    //public static final int port = 162;

    public  void sendSnmpTrap(TrapSenderConfiguration trapSenderConfiguration) {
        LOG.info("Into the TrapSender!!");
        SnmpTrapSender trapv3 = new SnmpTrapSender();

        int i = 1;
        while(i< trapSenderConfiguration.getSendCounter()){
            trapv3.sendTrap_Version3(trapSenderConfiguration);
            i++;
        }
    }
    /**
     * This methods sends the V2 trap to the Localhost in port 162
     * @param trapSenderConfiguration
     */
    public void sendTrap_Version3(TrapSenderConfiguration trapSenderConfiguration) {
        try {

            LOG.info("Creating Trapsender configuration!");


            // Create Transport Mapping
            TransportMapping transport = new DefaultUdpTransportMapping();
            transport.listen();

            /*USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3.createLocalEngineID(new OctetString("1"))), 0);
            SecurityModels.getInstance().addSecurityModel(usm);*/


            // Create Target
            CommunityTarget cTarget = new CommunityTarget();
            cTarget.setCommunity(new OctetString(trapSenderConfiguration.getCommunity()));
            cTarget.setVersion(trapSenderConfiguration.getSnmpVersion());
            cTarget.setAddress(new UdpAddress(trapSenderConfiguration.getIpAddress() + "/" + trapSenderConfiguration.getPort()));
            cTarget.setRetries(2);
            cTarget.setTimeout(10000);

            LOG.info("Setting up the Processing Data Unit");
            // Create PDU for V3
            PDU pdu = new PDU();

            String random = RandomStringUtils.randomNumeric(10);

            LOG.info("Sending Trap for ID: [{}]",random);

            pdu.add(new VariableBinding(SnmpTrapConstants.nbaiTrapId,
                    new OctetString(random)));

            pdu.add(new VariableBinding(SnmpTrapConstants.nbaiAlarmId,
                    new OctetString("44726737")));

            pdu.add(new VariableBinding(SnmpTrapConstants.nbaiManagedObject,
                    new OctetString("OPTUS_NETACT ne.RC13 RNC O6LX_MRNC03 WBTS 66AT_36093_Northbridge")));

            /*pdu.add(new VariableBinding(SnmpTrapConstants.nbaiTargetEntities,
                    new OctetString("M2000 OS_ns:.ne.HW4 ENODEB Nyngan-NSW-O-92NB-74414-L07-H-S123 EXT Alarm")));*/

            pdu.add(new VariableBinding(SnmpTrapConstants.nbaiAlarmType,
                    new OctetString("4")));

            pdu.add(new VariableBinding(SnmpTrapConstants.nbaiEventTime,
                    new OctetString("11/07/19 00:10:00")));

            pdu.add(new VariableBinding(SnmpTrapConstants.nbaiProbableCause,
                    new OctetString("testing2")));

            pdu.add(new VariableBinding(SnmpTrapConstants.nbaiSpecificProblems,
                    new OctetString("26234")));

            pdu.add(new VariableBinding(SnmpTrapConstants.nbaiPerceivedSeverity,
                    new OctetString("2")));

            pdu.add(new VariableBinding(SnmpTrapConstants.nbaiNotificationId,
                    new OctetString("18008754")));

            pdu.add(new VariableBinding(SnmpTrapConstants.nbaiCorrelationId,
                    new OctetString("18008754")));

            pdu.add(new VariableBinding(SnmpTrapConstants.nbaiSATotal,
                    new OctetString("0")));

            pdu.add(new VariableBinding(SnmpTrapConstants.nbaiAdditionalText,
                    new OctetString("EXTERNAL AL 18;User info ; Suppl Info Andrews 1 MHA Urgent; Diag Info 64 01 64 1417FSME 01 64,&lt;START&gt; TRAPDESC=EXTERNAL AL 18;User info ; Suppl Info Andrews 1 MHA Urgent; Diag Info 64 01 64 1417FSME 01 64,CI=NorthBridge-WA-O-66AT-36093-U09-N-S123,&lt;END&gt;P0068            WA_METRO_3GAlarm Number : 7418     PLMN-PLMN/RNC-3232/WBTS-36093                    Started    2019-8-20,14:13:16                                 -                         IntId      0                        NotifId       44435      Object     NorthBridge-WA-O-66AT-36093-U09-N-S123          ENVIRONMENTAL                           Misc : STATE:WACLA-D.  http://netops32:43231/NED/NED?service=library&amp;library=a25003a0000b5241376p1 Roaming Allowed = NSite Location = -31.947982, 115.858703JV Site Number = JP9093Site Address = 45 Francis Street, NORTHBRIDGE, WA, AuPost Code = 6003")));

            // need to specify the system up time
            pdu.add(new VariableBinding(SnmpConstants.sysUpTime,
                    new OctetString(new Date().toString())));
           /* pdu.add(new VariableBinding(SnmpConstants.snmpTrapOID, new OID(
                    Oid)));
            pdu.add(new VariableBinding(SnmpConstants.snmpTrapAddress,
                    new IpAddress(ipAddress)));

            pdu.add(new VariableBinding(new OID(Oid), new OctetString(
                    "\"Sector Degraded:ENODEB(140093):Engadine:7654 \n" +
                            "Post Code = 2233 \n" +
                            "Site Address = Boronia Avenue, ENGADINE, NSW, Au \n" +
                            "Roaming Allowed = N \n" +
                            "Site Location = -34.066278, 151.011277 \n" +
                            "JV Site Number = JS9132 \n" +
                            "Sub-Alarm details: \n" +
                            "7654 - CELL OPERATION DEGRADED;User info ; Suppl Info Increased BER detected on the \n" +
                            "optical connection to Radio Module; Diag Info 100 100 100 1955FZNI 1 0 \n" +
                            "path=/SMOD_R-1/rf_ext1/RMOD_R-2 additionalFaultId:1955;,CI=Engadine-O:S0401-OL23-B-1, \n" +
                            "OPTUS_NETACT ne.RC12 MRBTS O2EG_140093_Engadine LNBTS 140093 LNCEL 25 \n" +
                            " \n" +
                            "7654 - CELL OPERATION DEGRADED;User info ; Suppl Info Increased BER detected on the \n" +
                            "optical connection to Radio Module; Diag Info 100 100 100 1955FZNI 1 0 \n" +
                            "path=/SMOD_R-1/rf_ext1/RMOD_R-2 additionalFaultId:1955;,CI=Engadine-O:S0401-OL23-A-1, \n" +
                            "OPTUS_NETACT ne.RC12 MRBTS O2EG_140093_Engadine LNBTS 140093 LNCEL 17\"")));*/
            pdu.setType(PDU.SET);

            // Send the PDU
            Snmp snmp = new Snmp(transport);
            LOG.info("Sending V2 Trap... Check Wheather NMS is Listening or not? ");
            ResponseEvent send = snmp.send(pdu, cTarget);
            snmp.close();
            LOG.info("Sent the Trap successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            //LOG.error("There was an exception in sending the trap!!: [{}]", e.getMessage());
        }
    }
}
