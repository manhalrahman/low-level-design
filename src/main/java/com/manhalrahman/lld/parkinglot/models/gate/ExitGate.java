package com.manhalrahman.lld.parkinglot.models.gate;

import com.manhalrahman.lld.parkinglot.enums.GateType;

public class ExitGate implements Gate {
    String gateNumber;

    public ExitGate(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    @Override
    public GateType getType() {
        return GateType.EXIT;
    }
}
