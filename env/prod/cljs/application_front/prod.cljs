(ns application-front.prod
  (:require
    [application-front.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
