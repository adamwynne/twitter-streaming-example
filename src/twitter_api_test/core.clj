(ns twitter-api-test.core
  (:use
   [twitter.oauth]
   [twitter.callbacks]
   [twitter.callbacks.handlers])
  (:require
   [twitter.api :as api]
   [twitter.api.streaming :as streaming])
  (:import
   (twitter.callbacks.protocols AsyncStreamingCallback)))

(defn test-filter []
  (let [creds (make-oauth-creds ""
                                ""
                                ""
                                "")
        callback (AsyncStreamingCallback. (comp println #(str %2))
                                          (comp println response-return-everything)
                                          exception-print)
        api-context (api/make-api-context "https" "stream.twitter.com" "1.1")]
    
    (streaming/statuses-filter :params {:track "Christmas"}
                               :oauth-creds creds
                               :api-context api-context
                               :callbacks callback)))