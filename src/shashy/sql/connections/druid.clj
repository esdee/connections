(ns shashy.sql.connections.druid
  (:require [shashy.sql.connections.connection :as connection])
  (:import com.alibaba.druid.pool.DruidDataSource))

; See https://github.com/alibaba/druid

(defn get-datasource
  "Return a map with 1 key :datasource associated to a connection
   object that can be used by clojure.java.jdbc/with-connection"
  [{:keys [url initial-size max-active min-idle
           pool-prepared-statements?
           username password class-for-name
           validation-query test-on-borrow? test-while-idle?]
    :or {min-idle 1 initial-size 1 max-active 1
         pool-prepared-statements? true
         test-on-borrow? false
         test-while-idle? true}}]

  (let [datasource (doto (DruidDataSource.)
                     (.setUrl url)
                     (.setDriverClassName class-for-name)
                     (.setInitialSize initial-size)
                     (.setMaxActive max-active)
                     (.setMinIdle min-idle)
                     (.setPoolPreparedStatements pool-prepared-statements?)
                     (.setTestOnBorrow test-on-borrow?)
                     (.setTestWhileIdle test-while-idle?))]
    (when username (.setUsername datasource username))
    (when validation-query (.setValidationQuery datasource validation-query))
    (when password (.setPassword datasource password))
    {:datasource datasource}))

(defrecord Druid [config]
  connection/IConnection
  (connect [this]
    (get-datasource config)))
