#!/usr/bin/env bash

ssh-add .travis/id_rsa_partner_center.dec
git remote remove origin
git remote add origin ${REPO}
git fetch --tags
