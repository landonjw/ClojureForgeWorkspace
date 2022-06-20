(ns ca.landonjw.api.player
  (:import (ca.landonjw.wrappers EntityPlayerMPWrapper)))

(defn get-player [player]
  (cond
    (instance? java.lang.String player) (EntityPlayerMPWrapper/fromString player)
    (instance? net.minecraft.entity.player.EntityPlayerMP player) (EntityPlayerMPWrapper/fromMP player)
    (instance? net.minecraft.entity.player.EntityPlayer player) (EntityPlayerMPWrapper/fromSP player)))

(defn message-player [player message]
  (. player sendMessage message))

(defn teleport-player [player location]
  (cond
    (vector? location)
    (let [[x y z] location]
      (. player setPositionAndUpdate x y z))
    (map? location)
    (. player setPositionAndUpdate (get location :x) (get location :y) (get location :z))))

(defn get-position [player] [(. player getX) (. player getY) (. player getZ)])

(defn translate [location translation]
  (if (map? location)
    (translate [(get location :x) (get location :y) (get location :z)] translation)
    (if (map? translation)
      (translate location [(get translation :x) (get translation :y) (get translation :z)])
      (into [] (map + location translation)))))

(defn move-player [player translation]
  (teleport-player player (translate (get-position player) translation)))