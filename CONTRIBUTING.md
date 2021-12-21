# Contributing

## New Features
1. Developer and Team Lead discuss the scope of a feature
1. Developer opens an _issue_.
1. Developer assigns themself to the _issue_.
1. Team Lead assigns a feature _branch_ (`feature/...`) to the feature
1. Developer and Team Lead discuss the acceptance criteria for the feature
1. Team Lead starts writing acceptance tests
1. Developer starts writing the code
1. After completing the feature, Developer opens a _Pull Request_
1. Developer adds a [CHANGELOG] in the description of the _PR_ (only Added/Changed/Deprecated/Removed/Fixed/Security and a list of changes)
1. Developer assigns themself to the _PR_
1. Developer, with Lead's help, writes documentation and unit/integration tests
1. Team Lead accepts and merges the pull request to the _`develop`_ branch

### If the feature requires new REST endpoint
> * Developer and Team Lead discuss the acceptance criteria for the feature
* Developer and Team Lead prepare OpenAPI specs
* Team Lead writes Java and Postman tests


## (Bug) Fixes

* Developer (or Team Lead) opens an _issue_.
* Developer (or Team Lead) assigns themself to the _issue_.
* Team Lead assigns a fix or feature _branch_ (`hotfix/*`, `feature/...`) to the feature
* Pull Request is created (see _New Feature_ above)
* Once the fix has been made, Team Lead checks the PR and merges it

[CHANGELOG]: https://keepachangelog.com/en/1.0.0/