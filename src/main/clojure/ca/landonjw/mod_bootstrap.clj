(ns ca.landonjw.mod-bootstrap)

(gen-class
  :name ^{net.minecraftforge.fml.common.Mod {:modid "clojuretestmod" :name "ClojureTestMod" :version "1.0.0"}}
  ca.landonjw.mod-bootstrap.Bootstrap
  :methods [[^{net.minecraftforge.fml.common.Mod$EventHandler {}}
             onPreInit [net.minecraftforge.fml.common.event.FMLPreInitializationEvent]
             void]])

(defn -onPreInit [this event]
  (println "Hello from clojure!"))