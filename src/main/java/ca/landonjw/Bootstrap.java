package ca.landonjw;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure.lang.Symbol;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(
        modid = "clojuretestmod",
        name = "ClojureTestMod",
        version = "1.0.0",
        acceptableRemoteVersions = "*"
)
public class Bootstrap
{

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        System.out.println("Bootstrapping clojure mod.");
        initializeREPL();
    }

    private void initializeREPL() {
        Clojure.var("clojure.core", "require").invoke(Symbol.intern("ca.landonjw.bootstrap"));
        IFn execute = Clojure.var("ca.landonjw.bootstrap", "start-nrepl-server");
        execute.invoke();
    }

    @SubscribeEvent
    public void onEvent(Event event)
    {
        Clojure.var("clojure.core", "require").invoke(Symbol.intern("ca.landonjw.api.events"));
        IFn execute = Clojure.var("ca.landonjw.api.events", "post");
        execute.invoke(event.getClass(), event);
    }

}
