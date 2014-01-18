(ns shashy.sql.connections.connection)

(defprotocol IConnection
  "Protocol that database connections should extend"
  (connect [this]))
