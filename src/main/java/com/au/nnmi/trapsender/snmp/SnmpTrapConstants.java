package com.au.nnmi.trapsender.snmp;

import org.snmp4j.smi.OID;
import org.springframework.stereotype.Component;

@Component
public class SnmpTrapConstants {

    public static final OID nbaiTrapId =
            new OID(new int[] { 1,3,6,1,4,1,11,2,17,15,3,1,1 });
    public static final OID nbaiAlarmId =
            new OID(new int[] { 1,3,6,1,4,1,11,2,17,15,2,2,1,1 });
    public static final OID nbaiManagedObject =
            new OID(new int[] { 1,3,6,1,4,1,11,2,17,15,2,2,1,2 });
    public static final OID nbaiTargetEntities =
            new OID(new int[] { 1,3,6,1,4,1,11,2,17,15,2,2,1,3 });
    public static final OID nbaiAlarmType =
            new OID(new int[] {1,3,6,1,4,1,11,2,17,15,2,2,1,4 });
    public static final OID nbaiEventTime =
            new OID(new int[] { 1,3,6,1,4,1,11,2,17,15,2,2,1,5 });
    public static final OID nbaiProbableCause =
            new OID(new int[] { 1,3,6,1,4,1,11,2,17,15,2,2,1,6 });
    public static final OID nbaiSpecificProblems =
            new OID(new int[] { 1,3,6,1,4,1,11,2,17,15,2,2,1,7 });
    public static final OID nbaiPerceivedSeverity =
            new OID(new int[] { 1,3,6,1,4,1,11,2,17,15,2,2,1,8 });
    public static final OID nbaiNotificationId =
            new OID(new int[] { 1,3,6,1,4,1,11,2,17,15,2,2,1,9 });
    public static final OID nbaiCorrelationId =
            new OID(new int[] { 1,3,6,1,4,1,11,2,17,15,2,2,1,10 });
    public static final OID nbaiSATotal =
            new OID(new int[] { 1,3,6,1,4,1,11,2,17,15,2,2,1,12 });
    public static final OID nbaiAdditionalText =
            new OID(new int[] { 1,3,6,1,4,1,11,2,17,15,2,2,1,11 });
}
