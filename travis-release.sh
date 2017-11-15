#!/usr/bin/env bash

if [ "$TRAVIS_PULL_REQUEST" == "false" ];
then
	if [ "$TRAVIS_PULL_REQUEST_BRANCH" =~ "^release/major/.*" ]
	then
		gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customUsername=$GITHUB_USER -Prelease.customPassword=$GITHUB_PASSWORD -Prelease.versionIncrementer=incrementMajor -s;
	elif [ "$TRAVIS_PULL_REQUEST_BRANCH" =~ "^release/minor/.*" ]
	then
		gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customUsername=$GITHUB_USER -Prelease.customPassword=$GITHUB_PASSWORD -Prelease.versionIncrementer=incrementMinor -s;
	elif [ "$TRAVIS_PULL_REQUEST_BRANCH" =~ "^release/.*" ]
	then
		gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customUsername=$GITHUB_USER -Prelease.customPassword=$GITHUB_PASSWORD -Prelease.versionIncrementer=incrementPatch -s;
	fi
fi
