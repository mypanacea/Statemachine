package com.mypanacea.domain.statemachine.guard;

import com.mypanacea.domain.statemachine.event.PurchaseEvent;
import com.mypanacea.domain.statemachine.state.PurchaseState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;


public class HideGuard implements Guard<PurchaseState, PurchaseEvent> {

    @Override
    public boolean evaluate(StateContext<PurchaseState, PurchaseEvent> context) {
        return false;
    }
}
