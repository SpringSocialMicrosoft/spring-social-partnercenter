#!/usr/bin/env bash

ssh-add .travis/id_rsa_partner_center.dec
git remote add release ${REPO}
