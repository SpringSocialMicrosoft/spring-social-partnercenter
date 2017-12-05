#!/usr/bin/env bash

if [[ "$TRAVIS_PULL_REQUEST" == "false" ]];
then
    echo "$key_password" | gpg -o .travis/id_rsa_partner_center.dec --passphrase-fd 0 .travis/id_rsa_partner_center.enc;
    eval "$(ssh-agent -s)"
    chmod 600 .travis/id_rsa_partner_center.dec
    ssh-add .travis/id_rsa_partner_center.dec
    git remote remove origin
    git remote add origin ${REPO}
    git fetch --tags
fi
