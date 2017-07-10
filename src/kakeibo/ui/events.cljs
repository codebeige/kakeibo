(ns kakeibo.ui.events
    (:require [kakeibo.ui.db :as db]
              [re-frame.core :as re-frame]))

(re-frame/reg-event-db
 ::db/reset
 (fn [_ _] db/default))
