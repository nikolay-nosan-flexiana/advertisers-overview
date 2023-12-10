(ns ads.router 
  (:require [ads.views.advertisers :as ads]
            [reitit.frontend :as rfront]
            [re-frame.core :as rf]))

(def routes
  ["/"
    {:name      :advertisers
     :view      ads/advertisers-ui
     :link-text "Advertisers view"
     :controllers
     [{:start (fn [& _params] (rf/dispatch [:request-advertisers-and-stats]))}]}])

(def router
  (rfront/router routes))