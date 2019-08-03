package com.mypanacea.config;

import com.mypanacea.domain.statemachine.action.BuyAction;
import com.mypanacea.domain.statemachine.action.CancelAction;
import com.mypanacea.domain.statemachine.action.ErrorAction;
import com.mypanacea.domain.statemachine.action.ReservedAction;
import com.mypanacea.domain.statemachine.event.PurchaseEvent;
import com.mypanacea.domain.statemachine.guard.HideGuard;
import com.mypanacea.domain.statemachine.listener.PurchaseStateMachineApplicationListener;
import com.mypanacea.domain.statemachine.persist.PurchaseStateMachinePersister;
import com.mypanacea.domain.statemachine.state.PurchaseState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.EnumSet;

import static com.mypanacea.domain.statemachine.event.PurchaseEvent.*;
import static com.mypanacea.domain.statemachine.state.PurchaseState.*;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<PurchaseState, PurchaseEvent> {

    @Override
    public void configure(final StateMachineConfigurationConfigurer<PurchaseState, PurchaseEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(new PurchaseStateMachineApplicationListener());
    }

    @Override
    public void configure(final StateMachineStateConfigurer<PurchaseState, PurchaseEvent> states) throws Exception {
        states
                .withStates()
                .initial(NEW)
                .end(PURCHASE_COMPLETE)
                .states(EnumSet.allOf(PurchaseState.class));

    }

    @Override
    public void configure(final StateMachineTransitionConfigurer<PurchaseState, PurchaseEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(NEW)
                .target(RESERVED)
                .event(RESERVE)
                .action(reservedAction(), errorAction())

                .and()
                .withExternal()
                .source(RESERVED)
                .target(CANCEL_RESERVED)
                .event(RESERVE_DECLINE)
                .action(cancelAction(), errorAction())

                .and()
                .withExternal()
                .source(RESERVED)
                .target(PURCHASE_COMPLETE)
                .event(BUY)
                .guard(hideGuard())
                .action(buyAction(), errorAction());
    }

    @Bean
    public Action<PurchaseState, PurchaseEvent> reservedAction() {
        return new ReservedAction();
    }

    @Bean
    public Action<PurchaseState, PurchaseEvent> cancelAction() {
        return new CancelAction();
    }

    @Bean
    public Action<PurchaseState, PurchaseEvent> buyAction() {
        return new BuyAction();
    }

    @Bean
    public Action<PurchaseState, PurchaseEvent> errorAction() {
        return new ErrorAction();
    }

    @Bean
    public Guard<PurchaseState, PurchaseEvent> hideGuard() {
        return new HideGuard();
    }

    @Bean
    public StateMachinePersister<PurchaseState, PurchaseEvent, String> persister() {
        return new DefaultStateMachinePersister<>(new PurchaseStateMachinePersister());
    }
}
