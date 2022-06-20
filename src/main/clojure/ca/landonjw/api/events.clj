(ns ca.landonjw.api.events)

(def events { :player-logged-in net.minecraftforge.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
              :server-tick      net.minecraftforge.fml.common.gameevent.TickEvent$ServerTickEvent })
(def event-listeners {})

(defn type->event [event-type]
  (get events event-type))

(defn get-listeners [event-type]
  (get event-listeners (type->event event-type)))

(defn has-events? [event-type]
  (= (get-listeners event-type) nil))

(defn listen [event-type action]
  (if (has-events? event-type)
    (def event-listeners
      (assoc event-listeners (type->event event-type) (conj (get-listeners event-type) action)))
    (def event-listeners
      (assoc event-listeners (type->event event-type) [action]))))

(defn post [event-class event]
  (if (has-events? event-class)
    (doseq [listener (seq (get event-listeners event-class))]
      (listener event))))