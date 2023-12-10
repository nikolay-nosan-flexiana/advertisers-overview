(ns ads.events
  (:require [ads.utils.data-source :as ds]
            [re-frame.core :as rf]
            [reitit.frontend.easy :as rfe]
            [day8.re-frame.http-fx]
            [ajax.core :as ajax]
            [tick.core :as t]))

(rf/reg-event-db
 :initialize
 (fn [_ _]
   {:advertisers []
    :advertisers-stats nil
    :in-progress false
    :error-message nil
    :current-route nil}))

(defn make-indexed-set [coll key]
  (reduce (fn [nm val] (assoc nm (-> key val) (dissoc val key))) {} coll))

(defn merge-ads-stats [db stats]
  (if stats
    (assoc db 
           :advertisers (map
                         #(merge % (get stats (-> :id %)))
                         (-> db :advertisers))
           :advertisers-stats stats)
    db))

(rf/reg-event-fx
 :navigate-to
 (fn [_ [_ name query]]
   (rfe/push-state name [] query)))

(defn set-sort-params-if-presented
  [route db]
  (let [sort-field (-> route :query-params :sort)
        sort-type (-> route :query-params :sortType)]
    (if (and sort-field sort-type)
      (assoc db :sort-params [sort-field sort-type])
      db)))

(rf/reg-event-db
 :navigated
 (fn [db [_ route]]
   (->> route
        (assoc db :current-route)
        (set-sort-params-if-presented route))))

(rf/reg-event-db
 :set-sort-params
 (fn [db [_ sort-params]]
   (assoc db :sort-params sort-params)))

(rf/reg-event-db
 :on-http-error
 (fn [db [_ error]]
   (assoc db :error-message (:status-text error) :in-progress false)))

(rf/reg-event-fx
 :request-advertisers-and-stats
(fn [_ _]
  {:fx  [[:dispatch [:request-advertisers]]
         [:dispatch [:request-advertisers-stats]]]}))

(rf/reg-event-fx
 :request-advertisers
 (fn [{:keys [db]} _]
   {:db       (assoc db :error-message nil :in-progress true)
    :http-xhrio {:method :get
                       :uri (str ds/base-path ds/advertisers)
                       :response-format (ajax/json-response-format {:keywords? true})
                       :on-success      [:set-advertisers]
                       :on-failure      [:on-http-error]}}))

(rf/reg-event-fx
 :request-advertisers-stats
 (fn [{:keys [db]} _]
   {:db       (assoc db :error-message nil :in-progress true)
    :http-xhrio {:method :get
                 :uri (str ds/base-path ds/advertisers-stats)
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success      [:set-advertisers-stats]
                 :on-failure      [:on-http-error]}}))


(rf/reg-event-db
 :set-advertisers-stats
 (fn [db [_ stats]]
    (-> db
        (assoc :in-progress false)
        (merge-ads-stats (make-indexed-set stats :advertiserId)))))

(rf/reg-event-db
 :set-advertisers
 (fn [db [_ new-value]]
   (let [a (->> new-value
               (map #(assoc % :campaignsCount (count (:campaignIds %))
                            :createdAt (-> % :createdAt t/instant))))
         stats (-> db :advertisers-stats)]
     (-> db 
         (assoc :advertisers a :in-progress false)
         (merge-ads-stats stats)))))

