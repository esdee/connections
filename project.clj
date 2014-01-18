(defproject connections "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 ; Add the references depending on which libraries you need
                 ; Remove the others
                 ; Druid https://github.com/alibaba/druid
                 [com.alibaba/druid "1.0.1"]
                 ; DBCP https://github.com/http-kit/dbcp.clj
                 [commons-dbcp/commons-dbcp "20030825.184428"]
                 ; Bone http://jolbox.com/
                 [com.jolbox/bonecp "0.8.0.RELEASE"]
                 ])
