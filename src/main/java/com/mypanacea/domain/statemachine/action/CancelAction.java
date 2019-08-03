package com.mypanacea.domain.statemachine.action;

import com.mypanacea.domain.statemachine.event.PurchaseEvent;
import com.mypanacea.domain.statemachine.state.PurchaseState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class CancelAction implements Action<PurchaseState, PurchaseEvent> {
    @Override
    public void execute(final StateContext<PurchaseState, PurchaseEvent> context) {
        final String productId = context.getExtendedState().get("PRODUCT_ID", String.class);
        System.out.println("Резервирование товара " + productId + " отменено");
    }
}
