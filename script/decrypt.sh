#!/bin/sh

gpg --pinentry loopback --quiet --batch --yes --decrypt --passphrase="$GOOGLE_SERVICES_KEY" \
--output app/google-services.json app/google-services.json.gpg

gpg --pinentry loopback --quiet --batch --yes --decrypt --passphrase="$FIREBASE_SERVICE_ACC" \
--output app/firebase-service-account.json app/firebase-service-account.json.gpg