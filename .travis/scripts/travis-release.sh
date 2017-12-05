#!/usr/bin/env bash

last_merged_pr_branch=$(git log --oneline --merges master -1 | sed 's/^[^/]*\///')

echo ${last_merged_pr_branch}

eval "$(ssh-agent -s)"
tee .travis/deploy_key <<< $GITHUB_SSH_KEY
chmod 600 .travis/deploy_key

tee .travis/deploy_key.pub <<< $GITHUB_SSH_PUB
chmod 600 .travis/deploy_key.pub

ssh-add .travis/deploy_key
git remote add release git@github.com:SpringSocialMicrosoft/spring-social-partnercenter.git

if [[ ${last_merged_pr_branch} =~ ^release/major/.* ]];
then
	echo "Creating Major Release"
	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customKeyFile=".travis/deploy_key" -Prelease.versionIncrementer=incrementMajor -s;
elif [[ ${last_merged_pr_branch} =~ ^release/minor/.* ]];
then
	echo "Creating Minor Release"
	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customKeyFile=".travis/deploy_key" -Prelease.versionIncrementer=incrementMinor -s;
elif [[ ${last_merged_pr_branch} =~ ^release/.* ]];
then
	echo "Creating Patch Release"
	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customKeyFile=".travis/deploy_key" -Prelease.versionIncrementer=incrementPatch -s;
else
	echo "Release Regex not matched. No release will be built."
fi
