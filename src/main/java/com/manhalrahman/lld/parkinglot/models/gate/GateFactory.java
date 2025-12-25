package com.manhalrahman.lld.parkinglot.models.gate;

import com.manhalrahman.lld.parkinglot.enums.GateType;

public class GateFactory {
    public Gate createGate(String gateNumber, GateType gateType) {
        return switch (gateType) {
            case EXIT -> new ExitGate(gateNumber);
            case ENTRY -> new EntryGate(gateNumber);
        };
    }
}
