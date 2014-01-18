(ns shashy.sql.connections.dbcp
  (:require [org.httpkit.dbcp :as dbcp]
            [shashy.sql.connection :as connection]))

; See https://github.com/http-kit/dbcp.clj

(defn- get-datasource
  [{:keys [url username password]}]
  (do (dbcp/use-database! url username password)
      @dbcp/db-factory))

(defrecord DBCP [config]
  connection/IConnection
  (connect [this]
    (get-datasource config)))
