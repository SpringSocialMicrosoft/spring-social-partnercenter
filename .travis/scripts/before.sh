#!/usr/bin/env bash

ssh-add .ssh/id_rsa_github
git remote add release ${REPO}
