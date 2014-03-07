package org.jboss.errai.ioc.client;

import javax.enterprise.event.Event;
import javax.inject.Provider;
import org.company.firestorm.client.local.App;
import org.company.firestorm.client.shared.HelloMessage;
import org.company.firestorm.client.shared.Response;
import org.jboss.errai.bus.client.ErraiBus;
import org.jboss.errai.bus.client.framework.Subscription;
import org.jboss.errai.enterprise.client.cdi.AbstractCDIEventCallback;
import org.jboss.errai.enterprise.client.cdi.CDIEventTypeLookup;
import org.jboss.errai.enterprise.client.cdi.EventProvider;
import org.jboss.errai.enterprise.client.cdi.InstanceProvider;
import org.jboss.errai.enterprise.client.cdi.api.CDI;
import org.jboss.errai.ioc.client.api.ContextualTypeProvider;
import org.jboss.errai.ioc.client.api.builtin.CallerProvider;
import org.jboss.errai.ioc.client.api.builtin.DisposerProvider;
import org.jboss.errai.ioc.client.api.builtin.IOCBeanManagerProvider;
import org.jboss.errai.ioc.client.api.builtin.InitBallotProvider;
import org.jboss.errai.ioc.client.api.builtin.MessageBusProvider;
import org.jboss.errai.ioc.client.api.builtin.RequestDispatcherProvider;
import org.jboss.errai.ioc.client.api.builtin.RootPanelProvider;
import org.jboss.errai.ioc.client.api.builtin.SenderProvider;
import org.jboss.errai.ioc.client.container.CreationalCallback;
import org.jboss.errai.ioc.client.container.CreationalContext;
import org.jboss.errai.ioc.client.container.DestructionCallback;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.errai.ioc.client.container.InitializationCallback;

public class BootstrapperImpl implements Bootstrapper {
  {
    CDIEventTypeLookup.get().addLookup("org.company.firestorm.client.shared.Response", "java.lang.Object");
    new CDI().initLookupTable(CDIEventTypeLookup.get());
  }
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<CallerProvider> inj3427_CallerProvider_creational = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      final CallerProvider inj3422_CallerProvider = new CallerProvider();
      context.addBean(context.getBeanReference(CallerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj3422_CallerProvider);
      return inj3422_CallerProvider;
    }
  };
  private final CallerProvider inj3422_CallerProvider = inj3427_CallerProvider_creational.getInstance(context);
  private final CreationalCallback<RequestDispatcherProvider> inj3428_RequestDispatcherProvider_creational = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      final RequestDispatcherProvider inj3410_RequestDispatcherProvider = new RequestDispatcherProvider();
      context.addBean(context.getBeanReference(RequestDispatcherProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj3410_RequestDispatcherProvider);
      return inj3410_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj3410_RequestDispatcherProvider = inj3428_RequestDispatcherProvider_creational.getInstance(context);
  private final CreationalCallback<SenderProvider> inj3429_SenderProvider_creational = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      final SenderProvider inj3426_SenderProvider = new SenderProvider();
      context.addBean(context.getBeanReference(SenderProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj3426_SenderProvider);
      return inj3426_SenderProvider;
    }
  };
  private final SenderProvider inj3426_SenderProvider = inj3429_SenderProvider_creational.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj3430_RootPanelProvider_creational = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      final RootPanelProvider inj3412_RootPanelProvider = new RootPanelProvider();
      context.addBean(context.getBeanReference(RootPanelProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj3412_RootPanelProvider);
      return inj3412_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj3412_RootPanelProvider = inj3430_RootPanelProvider_creational.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj3431_InstanceProvider_creational = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      final InstanceProvider inj3416_InstanceProvider = new InstanceProvider();
      context.addBean(context.getBeanReference(InstanceProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj3416_InstanceProvider);
      return inj3416_InstanceProvider;
    }
  };
  private final InstanceProvider inj3416_InstanceProvider = inj3431_InstanceProvider_creational.getInstance(context);
  private final CreationalCallback<EventProvider> inj3432_EventProvider_creational = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      final EventProvider inj3420_EventProvider = new EventProvider();
      context.addBean(context.getBeanReference(EventProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj3420_EventProvider);
      return inj3420_EventProvider;
    }
  };
  private final EventProvider inj3420_EventProvider = inj3432_EventProvider_creational.getInstance(context);
  private InitializationCallback<App> init_inj3433_App = new InitializationCallback<App>() {
    public void init(final App obj) {
      obj.buildUI();
    }
  };
  private final CreationalCallback<App> inj3434_App_creational = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      final App inj3433_App = new App();
      context.addBean(context.getBeanReference(App.class, QualifierUtil.DEFAULT_QUALIFIERS), inj3433_App);
      _$1142750703__1116818801_messageEvent(inj3433_App, inj3420_EventProvider.provide(new Class[] { HelloMessage.class }, null));
      final Subscription var3 = CDI.subscribe("org.company.firestorm.client.shared.Response", new AbstractCDIEventCallback<Response>() {
        public void fireEvent(final Response event) {
          inj3433_App.response(event);
        }
        public String toString() {
          return "Observer: org.company.firestorm.client.shared.Response []";
        }
      });
      final Subscription var4 = ErraiBus.get().subscribe("cdi.event:org.company.firestorm.client.shared.Response", CDI.ROUTING_CALLBACK);
      context.addDestructionCallback(inj3433_App, new DestructionCallback<App>() {
        public void destroy(final App obj) {
          var3.remove();
          var4.remove();
        }
      });
      context.addInitializationCallback(inj3433_App, init_inj3433_App);
      return inj3433_App;
    }
  };
  private final App inj3433_App = inj3434_App_creational.getInstance(context);
  private final CreationalCallback<IOCBeanManagerProvider> inj3435_IOCBeanManagerProvider_creational = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      final IOCBeanManagerProvider inj3414_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      context.addBean(context.getBeanReference(IOCBeanManagerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj3414_IOCBeanManagerProvider);
      return inj3414_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj3414_IOCBeanManagerProvider = inj3435_IOCBeanManagerProvider_creational.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj3436_MessageBusProvider_creational = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      final MessageBusProvider inj3408_MessageBusProvider = new MessageBusProvider();
      context.addBean(context.getBeanReference(MessageBusProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj3408_MessageBusProvider);
      return inj3408_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj3408_MessageBusProvider = inj3436_MessageBusProvider_creational.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj3437_InitBallotProvider_creational = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      final InitBallotProvider inj3418_InitBallotProvider = new InitBallotProvider();
      context.addBean(context.getBeanReference(InitBallotProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj3418_InitBallotProvider);
      return inj3418_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj3418_InitBallotProvider = inj3437_InitBallotProvider_creational.getInstance(context);
  private final CreationalCallback<DisposerProvider> inj3438_DisposerProvider_creational = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      final DisposerProvider inj3424_DisposerProvider = new DisposerProvider();
      context.addBean(context.getBeanReference(DisposerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj3424_DisposerProvider);
      _$1300398733__$121625827_beanManager(inj3424_DisposerProvider, inj3414_IOCBeanManagerProvider.get());
      return inj3424_DisposerProvider;
    }
  };
  private final DisposerProvider inj3424_DisposerProvider = inj3438_DisposerProvider_creational.getInstance(context);
  private void declareBeans_0() {
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj3427_CallerProvider_creational, inj3422_CallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj3427_CallerProvider_creational, inj3422_CallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj3428_RequestDispatcherProvider_creational, inj3410_RequestDispatcherProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj3428_RequestDispatcherProvider_creational, inj3410_RequestDispatcherProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj3429_SenderProvider_creational, inj3426_SenderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj3429_SenderProvider_creational, inj3426_SenderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj3430_RootPanelProvider_creational, inj3412_RootPanelProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj3430_RootPanelProvider_creational, inj3412_RootPanelProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj3431_InstanceProvider_creational, inj3416_InstanceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj3431_InstanceProvider_creational, inj3416_InstanceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj3432_EventProvider_creational, inj3420_EventProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj3432_EventProvider_creational, inj3420_EventProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(App.class, App.class, inj3434_App_creational, inj3433_App, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj3435_IOCBeanManagerProvider_creational, inj3414_IOCBeanManagerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj3435_IOCBeanManagerProvider_creational, inj3414_IOCBeanManagerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj3436_MessageBusProvider_creational, inj3408_MessageBusProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj3436_MessageBusProvider_creational, inj3408_MessageBusProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj3437_InitBallotProvider_creational, inj3418_InitBallotProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj3437_InitBallotProvider_creational, inj3418_InitBallotProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj3438_DisposerProvider_creational, inj3424_DisposerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj3438_DisposerProvider_creational, inj3424_DisposerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
  }

  private native static void _$1300398733__$121625827_beanManager(DisposerProvider instance, IOCBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static void _$1142750703__1116818801_messageEvent(App instance, Event value) /*-{
    instance.@org.company.firestorm.client.local.App::messageEvent = value;
  }-*/;

  // The main IOC bootstrap method.
  public BootstrapperInjectionContext bootstrapContainer() {
    declareBeans_0();
    return injContext;
  }
}