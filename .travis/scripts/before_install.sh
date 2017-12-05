#!/usr/bin/env bash


if [[ "$TRAVIS_PULL_REQUEST" == "false" ]];
then
	echo "$key_password" | gpg -o .travis/id_rsa_partner_center.dec --passphrase-fd 0 .travis/id_rsa_partner_center.enc;
fi
