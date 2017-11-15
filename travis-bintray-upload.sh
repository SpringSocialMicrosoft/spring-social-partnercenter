#!/usr/bin/env bash

gradle bintrayUpload -PbintrayUser=$BINTRAY_USER -PbintrayKey=$BINTRAY_KEY -PmavenCentralUser=$MAVEN_USER -PmavenCentralPassword=$MAVEN_PASSWORD;
