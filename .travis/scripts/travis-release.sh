#!/usr/bin/env bash

if [[ $release_type == "major" ]];
then
	echo "Creating Major Release"
	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customUsername=$GITHUB_USER -Prelease.customPassword=$GITHUB_PASSWORD -Prelease.versionIncrementer=incrementMajor -s;
elif [[ $release_type == "minor" ]];
then
	echo "Creating Minor Release"
	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customUsername=$GITHUB_USER -Prelease.customPassword=$GITHUB_PASSWORD -Prelease.versionIncrementer=incrementMinor -s;
elif [[ $release_type == "patch" ]];
then
	echo "Creating Patch Release"
	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customUsername=$GITHUB_USER -Prelease.customPassword=$GITHUB_PASSWORD -Prelease.versionIncrementer=incrementPatch -s;
fi
