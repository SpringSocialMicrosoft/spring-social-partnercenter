#!/usr/bin/env bash

last_merged_pr_branch=$(git log --oneline --merges master -1 | sed 's/^[^/]*\///')

if [[ ${last_merged_pr_branch} =~ ^release/.* ]];
then
	gradle bintrayUpload -PbintrayUser=$BINTRAY_USER -PbintrayKey=$BINTRAY_KEY -PmavenCentralUser=$MAVEN_USER -PmavenCentralPassword=$MAVEN_PASSWORD;
else
	echo "Release pattern not matched. No release will be uploaded to Bintray"
fi
