#!/bin/sh

gpg --quiet --batch --yes --decrypt --passphrase="$GOOGLE_SERVICES_KEY" \
--output app/google-services.json app/google-services.json.gpg

gpg --quiet --batch --yes --decrypt --passphrase="$FIREBASE_SERVICE_ACC" \
--output app/firebase-service-account.json app/firebase-service-account.json.gpg