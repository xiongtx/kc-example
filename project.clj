(defproject kc-example "0.1.0-SNAPSHOT"
  :description "Simple Kafka client app."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[aero "1.0.3"]
                 [environ "1.1.0"]
                 [fundingcircle/kafka.client "0.4.2"]
                 [fundingcircle/kafka.serdes "0.7.0"]
                 [kafka.admin "0.1.2"]
                 [org.clojure/clojure "1.8.0"]
                 [ch.qos.logback/logback-classic "1.1.7"]
                 [log4j/log4j "1.2.17" :exclusions [javax.mail/mail javax.jms/jms com.sun.jdmk/jmxtools com.sun.jmx/jmxri]]]
  :plugins [[lein-environ "1.1.0"]]
  :repositories [["confluent" "http://packages.confluent.io/maven/"]
                 ["releases"
                  {:url "https://fundingcircle.artifactoryonline.com/fundingcircle/libs-release-local"
                   :username [:gpg :env/artifactory_user]
                   :password [:gpg :env/artifactory_password]}]
                 ["snapshots"
                  {:url "https://fundingcircle.artifactoryonline.com/fundingcircle/libs-snapshot-local"
                   :username [:gpg :env/artifactory_user]
                   :password [:gpg :env/artifactory_password]}]]
  :profiles {:uberjar {:aot :all}}
  :local-repo ".repo"
  :main kc-example.core
  :target-path "target/%s")
