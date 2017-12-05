#!/usr/bin/env bash

last_merged_pr_branch=$(git log --oneline --merges master -1 | sed 's/.*\///')

if [[ ${last_merged_pr_branch} =~ .*/release/major/.* ]];
then
	echo "Creating Major Release"
	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customUsername=$GITHUB_USER -Prelease.customPassword=$GITHUB_PASSWORD -Prelease.versionIncrementer=incrementMajor -s;
elif [[ ${last_merged_pr_branch} =~ .*/release/minor/.* ]];
then
	echo "Creating Minor Release"
	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customUsername=$GITHUB_USER -Prelease.customPassword=$GITHUB_PASSWORD -Prelease.versionIncrementer=incrementMinor -s;
elif [[ ${last_merged_pr_branch} =~ .*/release/.* ]];
then
	echo "Creating Patch Release"
	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customUsername=$GITHUB_USER -Prelease.customPassword=$GITHUB_PASSWORD -Prelease.versionIncrementer=incrementPatch -s;
fi
