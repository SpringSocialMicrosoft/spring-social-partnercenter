#!/usr/bin/env bash

last_merged_pr_branch=$(git log --oneline --merges master -1 | sed 's/^[^/]*\///')

echo ${last_merged_pr_branch}

if [[ ${last_merged_pr_branch} =~ ^release/major/.* ]];
then
	echo "Creating Major Release"
	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Dorg.ajoberstar.grgit.auth.ssh.private=${GITHUB_SSH_KEY} -Dorg.ajoberstar.grgit.auth.session.config.StrictHostKeyChecking=no -Prelease.versionIncrementer=incrementMajor -s;
elif [[ ${last_merged_pr_branch} =~ ^release/minor/.* ]];
then
	echo "Creating Minor Release"
	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Dorg.ajoberstar.grgit.auth.ssh.private=${GITHUB_SSH_KEY} -Dorg.ajoberstar.grgit.auth.session.config.StrictHostKeyChecking=no -Prelease.versionIncrementer=incrementMinor -s;
elif [[ ${last_merged_pr_branch} =~ ^release/.* ]];
then
	echo "Creating Patch Release"
	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Dorg.ajoberstar.grgit.auth.ssh.private=${GITHUB_SSH_KEY} -Dorg.ajoberstar.grgit.auth.session.config.StrictHostKeyChecking=no -Prelease.versionIncrementer=incrementPatch -s;
else
	echo "Release Regex not matched. No release will be built."
fi
