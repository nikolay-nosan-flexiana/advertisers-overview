(ns ads.router 
  (:require [ads.views.advertisers :as ads]
            [reitit.coercion.spec :as rss]
            [reitit.frontend :as rfront]
            [clojure.spec.alpha :as s]
            [re-frame.core :as rf]
            [cemerick.url :as url]))

(s/def ::sort string?)
(s/def ::sortType string?)

(def routes
  ["/"
   [""
    {:name      ::advertisers
     :parameters {:query (s/keys :opt-un [::sort ::sortType])}
     :view      ads/advertisers-ui
     :link-text "Advertisers view"
     :controllers
     [{:start (fn [& _params] (rf/dispatch [:request-advertisers-and-stats])
                (rf/dispatch [:set-sort-params (:query (url/url (-> js/window .-location .-href)))]))}]}]])

(def router
  (rfront/router
   routes
   {:data {:coercion rss/coercion}}))