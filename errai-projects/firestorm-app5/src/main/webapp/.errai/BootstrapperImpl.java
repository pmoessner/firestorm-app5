package org.jboss.errai.ioc.client;

import javax.enterprise.event.Event;
import javax.inject.Provider;
import org.company.firestorm.client.local.App;
import org.company.firestorm.client.local.CDITestHelper;
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
    CDIEventTypeLookup.get().addLookup("org.company.firestorm.client.shared.HelloMessage", "java.lang.Object");
    new CDI().initLookupTable(CDIEventTypeLookup.get());
  }
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<CallerProvider> inj1709_CallerProvider_creational = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      final CallerProvider inj1704_CallerProvider = new CallerProvider();
      context.addBean(context.getBeanReference(CallerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1704_CallerProvider);
      return inj1704_CallerProvider;
    }
  };
  private final CallerProvider inj1704_CallerProvider = inj1709_CallerProvider_creational.getInstance(context);
  private final CreationalCallback<RequestDispatcherProvider> inj1710_RequestDispatcherProvider_creational = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      final RequestDispatcherProvider inj1692_RequestDispatcherProvider = new RequestDispatcherProvider();
      context.addBean(context.getBeanReference(RequestDispatcherProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1692_RequestDispatcherProvider);
      return inj1692_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj1692_RequestDispatcherProvider = inj1710_RequestDispatcherProvider_creational.getInstance(context);
  private final CreationalCallback<EventProvider> inj1711_EventProvider_creational = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      final EventProvider inj1702_EventProvider = new EventProvider();
      context.addBean(context.getBeanReference(EventProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1702_EventProvider);
      return inj1702_EventProvider;
    }
  };
  private final EventProvider inj1702_EventProvider = inj1711_EventProvider_creational.getInstance(context);
  private InitializationCallback<App> init_inj1712_App = new InitializationCallback<App>() {
    public void init(final App obj) {
      obj.buildUI();
    }
  };
  private final CreationalCallback<App> inj1713_App_creational = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      final App inj1712_App = new App();
      context.addBean(context.getBeanReference(App.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1712_App);
      _$1142750703__1116818801_messageEvent(inj1712_App, inj1702_EventProvider.provide(new Class[] { HelloMessage.class }, null));
      final Subscription var1 = CDI.subscribe("org.company.firestorm.client.shared.Response", new AbstractCDIEventCallback<Response>() {
        public void fireEvent(final Response event) {
          inj1712_App.response(event);
        }
        public String toString() {
          return "Observer: org.company.firestorm.client.shared.Response []";
        }
      });
      final Subscription var2 = ErraiBus.get().subscribe("cdi.event:org.company.firestorm.client.shared.Response", CDI.ROUTING_CALLBACK);
      context.addDestructionCallback(inj1712_App, new DestructionCallback<App>() {
        public void destroy(final App obj) {
          var1.remove();
          var2.remove();
        }
      });
      context.addInitializationCallback(inj1712_App, init_inj1712_App);
      return inj1712_App;
    }
  };
  private final App inj1712_App = inj1713_App_creational.getInstance(context);
  private InitializationCallback<CDITestHelper> init_inj1714_CDITestHelper = new InitializationCallback<CDITestHelper>() {
    public void init(final CDITestHelper obj) {
      obj.saveStaticReference();
    }
  };
  private final CreationalCallback<CDITestHelper> inj1715_CDITestHelper_creational = new CreationalCallback<CDITestHelper>() {
    public CDITestHelper getInstance(final CreationalContext context) {
      final CDITestHelper inj1714_CDITestHelper = new CDITestHelper();
      context.addBean(context.getBeanReference(CDITestHelper.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1714_CDITestHelper);
      _$829379304__$1142750703_app(inj1714_CDITestHelper, inj1712_App);
      final Subscription var3 = CDI.subscribe("org.company.firestorm.client.shared.HelloMessage", new AbstractCDIEventCallback<HelloMessage>() {
        public void fireEvent(final HelloMessage event) {
          inj1714_CDITestHelper.countMessageEvent(event);
        }
        public String toString() {
          return "Observer: org.company.firestorm.client.shared.HelloMessage []";
        }
      });
      final Subscription var4 = ErraiBus.get().subscribe("cdi.event:org.company.firestorm.client.shared.HelloMessage", CDI.ROUTING_CALLBACK);
      context.addDestructionCallback(inj1714_CDITestHelper, new DestructionCallback<CDITestHelper>() {
        public void destroy(final CDITestHelper obj) {
          var3.remove();
          var4.remove();
        }
      });
      final Subscription var5 = CDI.subscribe("org.company.firestorm.client.shared.Response", new AbstractCDIEventCallback<Response>() {
        public void fireEvent(final Response event) {
          inj1714_CDITestHelper.countResponseEvent(event);
        }
        public String toString() {
          return "Observer: org.company.firestorm.client.shared.Response []";
        }
      });
      final Subscription var6 = ErraiBus.get().subscribe("cdi.event:org.company.firestorm.client.shared.Response", CDI.ROUTING_CALLBACK);
      context.addDestructionCallback(inj1714_CDITestHelper, new DestructionCallback<CDITestHelper>() {
        public void destroy(final CDITestHelper obj) {
          var5.remove();
          var6.remove();
        }
      });
      context.addInitializationCallback(inj1714_CDITestHelper, init_inj1714_CDITestHelper);
      return inj1714_CDITestHelper;
    }
  };
  private final CDITestHelper inj1714_CDITestHelper = inj1715_CDITestHelper_creational.getInstance(context);
  private final CreationalCallback<SenderProvider> inj1716_SenderProvider_creational = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      final SenderProvider inj1708_SenderProvider = new SenderProvider();
      context.addBean(context.getBeanReference(SenderProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1708_SenderProvider);
      return inj1708_SenderProvider;
    }
  };
  private final SenderProvider inj1708_SenderProvider = inj1716_SenderProvider_creational.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj1717_RootPanelProvider_creational = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      final RootPanelProvider inj1694_RootPanelProvider = new RootPanelProvider();
      context.addBean(context.getBeanReference(RootPanelProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1694_RootPanelProvider);
      return inj1694_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj1694_RootPanelProvider = inj1717_RootPanelProvider_creational.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj1718_InstanceProvider_creational = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      final InstanceProvider inj1698_InstanceProvider = new InstanceProvider();
      context.addBean(context.getBeanReference(InstanceProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1698_InstanceProvider);
      return inj1698_InstanceProvider;
    }
  };
  private final InstanceProvider inj1698_InstanceProvider = inj1718_InstanceProvider_creational.getInstance(context);
  private final CreationalCallback<IOCBeanManagerProvider> inj1719_IOCBeanManagerProvider_creational = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      final IOCBeanManagerProvider inj1696_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      context.addBean(context.getBeanReference(IOCBeanManagerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1696_IOCBeanManagerProvider);
      return inj1696_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj1696_IOCBeanManagerProvider = inj1719_IOCBeanManagerProvider_creational.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj1720_MessageBusProvider_creational = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      final MessageBusProvider inj1690_MessageBusProvider = new MessageBusProvider();
      context.addBean(context.getBeanReference(MessageBusProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1690_MessageBusProvider);
      return inj1690_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj1690_MessageBusProvider = inj1720_MessageBusProvider_creational.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj1721_InitBallotProvider_creational = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      final InitBallotProvider inj1700_InitBallotProvider = new InitBallotProvider();
      context.addBean(context.getBeanReference(InitBallotProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1700_InitBallotProvider);
      return inj1700_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj1700_InitBallotProvider = inj1721_InitBallotProvider_creational.getInstance(context);
  private final CreationalCallback<DisposerProvider> inj1722_DisposerProvider_creational = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      final DisposerProvider inj1706_DisposerProvider = new DisposerProvider();
      context.addBean(context.getBeanReference(DisposerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj1706_DisposerProvider);
      _$1300398733__$121625827_beanManager(inj1706_DisposerProvider, inj1696_IOCBeanManagerProvider.get());
      return inj1706_DisposerProvider;
    }
  };
  private final DisposerProvider inj1706_DisposerProvider = inj1722_DisposerProvider_creational.getInstance(context);
  private void declareBeans_0() {
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj1709_CallerProvider_creational, inj1704_CallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj1709_CallerProvider_creational, inj1704_CallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj1710_RequestDispatcherProvider_creational, inj1692_RequestDispatcherProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj1710_RequestDispatcherProvider_creational, inj1692_RequestDispatcherProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj1711_EventProvider_creational, inj1702_EventProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj1711_EventProvider_creational, inj1702_EventProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(App.class, App.class, inj1713_App_creational, inj1712_App, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(CDITestHelper.class, CDITestHelper.class, inj1715_CDITestHelper_creational, inj1714_CDITestHelper, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj1716_SenderProvider_creational, inj1708_SenderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj1716_SenderProvider_creational, inj1708_SenderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj1717_RootPanelProvider_creational, inj1694_RootPanelProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj1717_RootPanelProvider_creational, inj1694_RootPanelProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj1718_InstanceProvider_creational, inj1698_InstanceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj1718_InstanceProvider_creational, inj1698_InstanceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj1719_IOCBeanManagerProvider_creational, inj1696_IOCBeanManagerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj1719_IOCBeanManagerProvider_creational, inj1696_IOCBeanManagerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj1720_MessageBusProvider_creational, inj1690_MessageBusProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj1720_MessageBusProvider_creational, inj1690_MessageBusProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj1721_InitBallotProvider_creational, inj1700_InitBallotProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj1721_InitBallotProvider_creational, inj1700_InitBallotProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj1722_DisposerProvider_creational, inj1706_DisposerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj1722_DisposerProvider_creational, inj1706_DisposerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
  }

  private native static void _$1300398733__$121625827_beanManager(DisposerProvider instance, IOCBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static void _$829379304__$1142750703_app(CDITestHelper instance, App value) /*-{
    instance.@org.company.firestorm.client.local.CDITestHelper::app = value;
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