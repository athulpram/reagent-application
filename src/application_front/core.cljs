(ns application-front.core
  (:require
    [reagent.core :as r]))

;; -------------------------
;; Views

(def text-box {
               :height    40
               :width     350
               :font-size 20
               })

(defn fetch [url, method, headers, payload]
  (js/fetch url (clj->js {
                          :method  method
                          :headers headers
                          :body    (.stringify js/JSON (clj->js payload))
                          })))

(defn sign-up []
  (-> (fetch "http://localhost:8080/sign-up" "POST" {
                                                     :content-type "application/json"
                                                     } {:username (-> js/document
                                                                      (.getElementById "username")
                                                                      (.-value))
                                                        :password (-> js/document
                                                                      (.getElementById "password")
                                                                      (.-value))
                                                        })
      (.then (fn [response] (println "user is added to the server")))))

(defn signup-page []
  [:div [:h2 "This is a sample application"]
   [:input {:type "text" :id "username" :placeholder "Enter Username*" :style text-box}]
   [:input {:type "password" :id "password" :placeholder "Enter Username*" :style text-box}]
   [:button {:id "submit-button" :on-click sign-up :style text-box} "Sign Up"]
   ])

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [signup-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
