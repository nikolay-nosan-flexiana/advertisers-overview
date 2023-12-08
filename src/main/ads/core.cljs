(ns ads.core
(:require [ads.events]
          [ads.router :as router]
          [ads.subs]
          [ads.views.main :as main]
          [re-frame.core :as rf]
          [reagent.dom :as rdom]
          [reitit.frontend.controllers :as rfc]
          [reitit.frontend.easy :as rfe]))

(defn on-navigate [new-match]
  (let [old-match (rf/subscribe [:current-route])]
    (when new-match
      (let [cs (rfc/apply-controllers (:controllers @old-match) new-match)
            m  (assoc new-match :controllers cs)]
        (rf/dispatch [:navigated m])))))

(defn init-routes! []
  (rfe/start!
   router/router
   on-navigate
   {:use-fragment true}))

(defn mount-ui
  []
  (rdom/render [main/ui]
               (js/document.getElementById "app")))

(defn ^:dev/after-load clear-cache-and-render!
  []
  (rf/clear-subscription-cache!)
  (init-routes!)
  (mount-ui))

(defn run
  []
  (rf/dispatch-sync [:initialize])
  (init-routes!)
  (mount-ui))