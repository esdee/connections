(ns shashy.sql.connections.bone
  (:require [shashy.sql.connection :as connection])
  (:import [com.jolbox.bonecp BoneCPConfig BoneCPDataSource]))

; See http://jolbox.com/
(defn get-datasource
  "Return a map with 1 key :datasource associated to a connection
  object that can be used by clojure.java.jdbc/with-connection"
  [{:keys [url min-connections max-connections partitions log-statements?
           username password class-for-name]
     :or {min-connections 1 max-connections 1 log-statements? false partitions 1}}]
    (when class-for-name (Class/forName class-for-name))
    (let [config (doto (BoneCPConfig.)
                       (.setJdbcUrl url)
                       (.setMinConnectionsPerPartition min-connections)
                       (.setMaxConnectionsPerPartition max-connections)
                       (.setLogStatementsEnabled log-statements?)
                       (.setPartitionCount partitions))]
      (when username (.setUsername config username))
      (when password (.setPassword config password))
      {:datasource (BoneCPDataSource. config)}))

(defrecord Bone [config]
  connection/IConnection
  (connect [this]
    (get-datasource config)))
