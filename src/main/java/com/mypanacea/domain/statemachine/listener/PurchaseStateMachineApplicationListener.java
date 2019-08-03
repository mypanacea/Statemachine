package com.mypanacea.domain.statemachine.listener;

import com.mypanacea.domain.statemachine.event.PurchaseEvent;
import com.mypanacea.domain.statemachine.state.PurchaseState;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

public class PurchaseStateMachineApplicationListener implements StateMachineListener<PurchaseState, PurchaseEvent> {
    @Override
    public void stateChanged(final State<PurchaseState, PurchaseEvent> from, final State<PurchaseState, PurchaseEvent> to) {
        if (from.getId() != null) {
            System.out.println("Переход из статуса " + from.getId() + " в статус " + to.getId());
        }
    }

    @Override
    public void stateEntered(final State<PurchaseState, PurchaseEvent> state) {

    }

    @Override
    public void stateExited(final State<PurchaseState, PurchaseEvent> state) {

    }

    @Override
    public void eventNotAccepted(final Message<PurchaseEvent> event) {
        System.out.println("Евент не принят " + event);
    }

    @Override
    public void transition(final Transition<PurchaseState, PurchaseEvent> transition) {

    }

    @Override
    public void transitionStarted(final Transition<PurchaseState, PurchaseEvent> transition) {

    }

    @Override
    public void transitionEnded(final Transition<PurchaseState, PurchaseEvent> transition) {

    }

    @Override
    public void stateMachineStarted(final StateMachine<PurchaseState, PurchaseEvent> stateMachine) {
        System.out.println("Machine started");
    }

    @Override
    public void stateMachineStopped(final StateMachine<PurchaseState, PurchaseEvent> stateMachine) {

    }

    @Override
    public void stateMachineError(final StateMachine<PurchaseState, PurchaseEvent> stateMachine, Exception exception) {
    }

    @Override
    public void extendedStateChanged(final Object key, final Object value) {

    }

    @Override
    public void stateContext(final StateContext<PurchaseState, PurchaseEvent> stateContext) {

    }
}
