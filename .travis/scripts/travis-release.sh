#!/usr/bin/env bash

gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customUsername=$GITHUB_USER -Prelease.customPassword=$GITHUB_PASSWORD -Prelease.versionIncrementer=incrementMinor -s;
#if [[ $TRAVIS_PULL_REQUEST_BRANCH =~ ^release/major/.* ]];
#then
#	echo "Creating Major Release"
#	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customUsername=$GITHUB_USER -Prelease.customPassword=$GITHUB_PASSWORD -Prelease.versionIncrementer=incrementMajor -s;
#elif [[ $TRAVIS_PULL_REQUEST_BRANCH =~ ^release/minor/.* ]];
#then
#	echo "Creating Minor Release"
#	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customUsername=$GITHUB_USER -Prelease.customPassword=$GITHUB_PASSWORD -Prelease.versionIncrementer=incrementMinor -s;
#elif [[ $TRAVIS_PULL_REQUEST_BRANCH =~ ^release/.* ]];
#then
#	echo "Creating Patch Release"
#	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customUsername=$GITHUB_USER -Prelease.customPassword=$GITHUB_PASSWORD -Prelease.versionIncrementer=incrementPatch -s;
#fi
