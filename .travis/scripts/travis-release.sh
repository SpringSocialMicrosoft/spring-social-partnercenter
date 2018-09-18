#!/usr/bin/env bash

function release() {
	local last_merged_pr_branch=$(git log --oneline --merges master -1 | sed 's/^[^/]*\///')
	echo ${last_merged_pr_branch}

	if [[ ${last_merged_pr_branch} =~ ^release/major/.* ]];
	then
		majorRelease
	elif [[ ${last_merged_pr_branch} =~ ^release/minor/.* ]];
	then
		minorRelease
	elif [[ ${last_merged_pr_branch} =~ ^release/.* ]];
	then
		patchRelease ${last_merged_pr_branch}
	else
		echo "Release Regex not matched. No release will be built."
	fi
}

function majorRelease(){
	echo "Creating Major Release"
	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customKeyFile=".travis/id_rsa_partner_center.dec" -Prelease.versionIncrementer=incrementMajor -s;
}

function minorRelease() {
	echo "Creating Minor Release"
	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customKeyFile=".travis/id_rsa_partner_center.dec" -Prelease.versionIncrementer=incrementMinor -s;
}

function patchRelease() {
	echo "Creating Patch Release"
	local last_merged_pr_branch=$1
	if [[ ${last_merged_pr_branch} =~ ^release/patch/[0-9]+.[0-9]+.[0-9]+ ]];
	then
		versionedRelease ${last_merged_pr_branch}
	else
		gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customKeyFile=".travis/id_rsa_partner_center.dec" -Prelease.versionIncrementer=incrementPatch -s;
	fi

}

function versionedRelease() {
	patch_pattern="release/patch/"
	local last_merged_pr_branch=$1
	new_release=${last_merged_pr_branch/release\/patch\/""}
	echo "Forced Version=${new_release}"
	gradle release -Prelease.disableChecks -Prelease.pushTagsOnly -Prelease.customKeyFile=".travis/id_rsa_partner_center.dec" -Prelease.version=${new_release} -s
}

release
