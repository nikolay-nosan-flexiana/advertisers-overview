(ns ads.views.main
  (:require [ads.views.advertisers :as ads]))

(defn ui
  []
  [:div
   [ads/advertisers-ui]])